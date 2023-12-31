package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String>{
}
