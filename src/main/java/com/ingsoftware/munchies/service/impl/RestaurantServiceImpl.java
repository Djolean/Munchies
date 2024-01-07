package com.ingsoftware.munchies.service.impl;

import com.ingsoftware.munchies.model.entity.Restaurant;
import com.ingsoftware.munchies.repository.RestaurantRepository;
import com.ingsoftware.munchies.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
     return restaurantRepository.findAll();

    }

    @Override
    public Restaurant findById(String id) {
       return restaurantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Restaurant doesn't exist!"));
    }

    @Override
    public Restaurant save(final Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant  update(final Restaurant restaurant){
       return restaurantRepository.save(restaurant);
    }

    @Override
    public void delete(String restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }


}
