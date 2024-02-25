package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.GroupOrder;
import com.ingsoftware.munchies.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder, String> {
    List<GroupOrder> findGroupOrdersByRestaurant(Restaurant restaurant);
}
