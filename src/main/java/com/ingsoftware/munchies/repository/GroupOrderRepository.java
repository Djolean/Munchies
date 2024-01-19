package com.ingsoftware.munchies.repository;

import com.ingsoftware.munchies.model.entity.GroupOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder, String> {
}
