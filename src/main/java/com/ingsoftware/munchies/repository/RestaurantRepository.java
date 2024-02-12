package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.controller.request.RestaurantRequestDTO;
import com.ingsoftware.munchies.controller.response.RestaurantResponseDTO;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, String>  {

    Page<Restaurant> findAll(Pageable pageable);
    Restaurant findByShortName(String shortName);

}
