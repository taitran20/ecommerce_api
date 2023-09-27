package com.tai.repository;

import com.tai.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.id = :userId and (o.status = 'PLACED' OR o.status = 'CONFIRMED' or o.status = 'SHIPPED' or o.status = 'DELIVERED')")
    List<Order> getUsersOrder(@Param("userId") Long userId);
}
