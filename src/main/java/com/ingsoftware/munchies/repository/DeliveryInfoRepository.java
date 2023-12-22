package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInfoRepository extends JpaRepository<DeliveryInfo, Integer> {
}
