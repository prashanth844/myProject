package com.dev.order.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.order.service.model.Order;

@Repository
public interface OrderRepsitory extends JpaRepository<Order, String> {

	List<Order> findByUserId(String userId);

}
