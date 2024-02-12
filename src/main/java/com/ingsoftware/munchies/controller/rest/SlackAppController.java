package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Item;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.ItemService;
import com.ingsoftware.munchies.service.RestaurantService;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jakarta_servlet.SlackAppServlet;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/slack/events")
@CrossOrigin
public class SlackAppController extends SlackAppServlet {

    private final RestaurantService restaurantService;
    private final GroupOrderService groupOrderService;
    private final RestaurantRepository restaurantRepository;
    private final GroupOrderRepository groupOrderRepository;
    private final ItemService itemService;

    public SlackAppController(App app, RestaurantService restaurantService, GroupOrderService groupOrderService,
                              RestaurantRepository restaurantRepository,
                              GroupOrderRepository groupOrderRepository, ItemService itemService) {
        super(app);
        this.restaurantService = restaurantService;
        this.groupOrderService = groupOrderService;
        this.restaurantRepository = restaurantRepository;
        this.groupOrderRepository = groupOrderRepository;
        this.itemService = itemService;
    }

    @PostMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello, Welcome to Munchies!", HttpStatus.OK);
    }

    @PostMapping("/restaurants")
    public ResponseEntity<String> restaurants() {
        List<RestaurantResponseDTO> restaurants = restaurantService.findAllSlack();
        StringBuilder sb = new StringBuilder();
        for (RestaurantResponseDTO r : restaurants) {
            sb.append("Short name: ").append(r.getShortName()).append("\n")
                    .append("Address: ").append(r.getAddress()).append("\n")
                    .append("Phone number: ").append(r.getPhoneNumber()).append("\n")
                    .append("URL: ").append(r.getMenuUrl()).append("\n")
                    .append("----------------------------------------------").append("\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }

    @PostMapping("/groupOrder")
    public ResponseEntity<String> createNewOrder(@RequestParam("text") String commandText,
                                                 @RequestParam("user_name") String userName) {

        String[] commandParts = commandText.split("\\s+");

            String shortName = commandParts[0];
            int timeout = Integer.parseInt(commandParts[1]);

        Restaurant restaurant = restaurantRepository.findByShortName(shortName);
        String id = restaurant.getRestaurantId();
        GroupOrderResponseDTO order = groupOrderService.addGroupOrder(id, new GroupOrderRequestDTO(UUID.randomUUID().toString(), userName, timeout));

        return new ResponseEntity<>("Group order has been created!\nLink:   https://lightly-evolved-cattle.ngrok-free.app/restaurants/group-order/"  + order.getGroupOrderId(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<String> addToOrder(@RequestParam("text") String commandText,
                                             @RequestParam("user_name") String userName) {

        String[] commandParts = commandText.split("\\s+");

        String orderId = commandParts[0];
        String itemName = commandParts[1];
        Double price = Double.valueOf((commandParts[2]));

        GroupOrder groupOrder = groupOrderRepository.findById(orderId).orElseThrow(Exception.GroupOrderNotFoundException::new);
        String id = groupOrder.getGroupOrderId();
        itemService.addItemToGroupOrder(id , new ItemRequestDTO(UUID.randomUUID().toString(), itemName, userName, price));

        return new ResponseEntity<>("Item has been successfully added to the group order!", HttpStatus.OK);
    }
}