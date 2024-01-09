package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.GroupOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder, String> {
}
