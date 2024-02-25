package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.mapper.RestaurantMapper;
import com.ingsoftware.munchies.model.Restaurant;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class RestaurantServiceImplTest {

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantMapper mapper;


    @Test
    void findAll() {

        int page = 0;
        int size = 10;
        String sortBy = "asc";
        String sortOrder = "restaurantName";
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortBy), sortOrder);

        List<Restaurant> restaurantList = Arrays.asList(new Restaurant(), new Restaurant());
        Page<Restaurant> restaurantPage = new PageImpl<>(restaurantList, pageable, restaurantList.size());

        when(restaurantRepository.findAll(pageable)).thenReturn(restaurantPage);
        when(mapper.mapToDTO(any(Restaurant.class))).thenReturn(new RestaurantResponseDTO());

        Page<RestaurantResponseDTO> result = restaurantService.findAll(page, size, sortBy, sortOrder);

        assertEquals(restaurantList.size(), result.getContent().size());
    }

    @Test
    void generateShortName() {

        String name = "Restaurant Name";

        String result = restaurantService.generateShortName(name);

        assertEquals("Restaurant_Name", result);
    }
}
