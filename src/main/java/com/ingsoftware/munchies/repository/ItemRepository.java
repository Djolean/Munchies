package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.GroupOrder;
import com.ingsoftware.munchies.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findAllByGroupOrder(GroupOrder groupOrder);
}
