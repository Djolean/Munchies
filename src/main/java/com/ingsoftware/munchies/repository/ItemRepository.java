package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.GroupOrder;
import com.ingsoftware.munchies.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{
    List<Item> findAllByGroupOrder(GroupOrder groupOrder);
}
