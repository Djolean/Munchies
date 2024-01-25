package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder, String> {
    List<GroupOrder> findGroupOrdersByRestaurant(Restaurant restaurant);
}
