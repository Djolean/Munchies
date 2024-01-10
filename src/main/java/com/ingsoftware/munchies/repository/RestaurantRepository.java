package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

}
