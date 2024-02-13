package com.ingsoftware.munchies.controller.rest;

import com.ingsoftware.munchies.controller.request.GroupOrderRequestDTO;
import com.ingsoftware.munchies.controller.request.ItemRequestDTO;
import com.ingsoftware.munchies.controller.response.GroupOrderResponseDTO;
import com.ingsoftware.munchies.controller.response.ItemResponseDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.exception.Exception;
import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.GroupOrderRepository;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.GroupOrderService;
import com.ingsoftware.munchies.service.ItemService;
import com.ingsoftware.munchies.service.RestaurantService;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jakarta_servlet.SlackAppServlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/slack/events")
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

        if (commandText.split(" ").length != 2) {
            return new ResponseEntity<>("Wrong input, please try again! [shortname timeout]", HttpStatus.OK);
        }
        String[] commandParts = commandText.split("\\s+");

        String shortName = commandParts[0];
        int timeout = Integer.parseInt(commandParts[1]);

        Restaurant restaurant = restaurantRepository.findByShortName(shortName);
        if (restaurant == null) {
            return new ResponseEntity<>("Restaurant doesn't exist", HttpStatus.OK);
        }
        String id = restaurant.getRestaurantId();
        GroupOrderResponseDTO groupOrder = groupOrderService.addGroupOrder(id, new GroupOrderRequestDTO(UUID.randomUUID().toString(), userName, timeout));

        return new ResponseEntity<>("Group order has been created!\nLink:   https://lightly-evolved-cattle.ngrok-free.app/restaurants/group-order/" + groupOrder.getGroupOrderId(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<String> addToOrder(@RequestParam("text") String commandText,
                                             @RequestParam("user_name") String userName) {

        if (commandText.split(" ").length != 3) {
            return new ResponseEntity<>("Wrong input, please try again! [orderId itemName price]", HttpStatus.OK);
        }

        String[] commandParts = commandText.split("\\s+");

        String orderId = commandParts[0];
        String itemName = commandParts[1];
        Double price = Double.valueOf((commandParts[2]));

        if (!groupOrderRepository.existsById(orderId)) {
            return new ResponseEntity<>("Group Order doesn't exist", HttpStatus.OK);
        }
        GroupOrder groupOrder = groupOrderRepository.findById(orderId).orElseThrow(Exception.GroupOrderNotFoundException::new);

        String id = groupOrder.getGroupOrderId();
        itemService.addItemToGroupOrder(id, new ItemRequestDTO(UUID.randomUUID().toString(), itemName, userName, price));

        return new ResponseEntity<>("Item has been successfully added to the group order!", HttpStatus.OK);
    }


    @PostMapping("/orderDetails")
    public ResponseEntity<String> orderDetails(@RequestParam("text") String orderId) {

        if (orderId.split(" ").length != 1) {
            return new ResponseEntity<>("Wrong input, please try again! [orderId]", HttpStatus.OK);
        }
        if (!groupOrderRepository.existsById(orderId)) {
            return new ResponseEntity<>("Group Order doesn't exist", HttpStatus.OK);
        }

        GroupOrderResponseDTO groupOrder = groupOrderService.findGroupOrderById(orderId);
        String restaurantId = groupOrder.getRestaurant().getRestaurantId();
        RestaurantResponseDTO restaurant = restaurantService.findById(restaurantId);
        List<ItemResponseDTO> items = itemService.findAllByGroupOrder(groupOrder.getGroupOrderId());

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        int time = groupOrderService.calculateTimeRemaining(groupOrder);
        String timeRemaining;
        if (time > 0) {
            timeRemaining = groupOrderService.formatTime(time);
        } else {
            timeRemaining = "Order has expired!";
        }

        sb.append("Order creator: ").append(groupOrder.getCreatorName()).append("\n")
                .append("----------------------------------------------").append("\n")
                .append("Order Id: ").append(groupOrder.getGroupOrderId()).append("\n")
                .append("Order URL: ").append("https://lightly-evolved-cattle.ngrok-free.app/restaurants/group-order/").append(groupOrder.getGroupOrderId()).append("\n")
                .append("Order timeout: ").append(groupOrder.getGroupOrderTimeout()).append("   Remaining: ").append(timeRemaining).append("\n")
                .append("----------------------------------------------").append("\n")
                .append("Restaurant name: ").append(restaurant.getRestaurantName()).append("\n")
                .append("Restaurant phone: ").append(restaurant.getPhoneNumber()).append("\n")
                .append("Restaurant url: ").append(restaurant.getMenuUrl()).append("\n")
                .append("----------------------------------------------").append("\n")
                .append("Items:").append("\n");
        for (ItemResponseDTO i : items) {
            sb.append(counter).append("\n")
                    .append("Item name: ").append(i.getItemName()).append("\n")
                    .append("Item price: ").append(i.getPrice()).append("\n")
                    .append("Added by: ").append(i.getEmployeeName()).append("\n\n");
            counter++;
        }
        sb.append("----------------------------------------------").append("\n")
                .append("Delivery charges: ").append(restaurant.getDeliveryInfo().getAdditionalCharges()).append("\n")
                .append("Total price: ").append(groupOrder.getTotalPrice());
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}