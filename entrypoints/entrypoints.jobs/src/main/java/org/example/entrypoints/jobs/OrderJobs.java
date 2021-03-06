package org.example.entrypoints.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.usecases.OrderService;
import org.example.usecases.dto.OrderCreateDto;
import org.example.usecases.dto.OrderGetDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderJobs {

  private final OrderService orderService;

  @Scheduled(fixedDelayString = "${jobs.automatic-order-creation:PT30S}")
  public void automaticOrderCreation() {
    OrderCreateDto order = new OrderCreateDto().setName("Automatically created order");
    OrderGetDto createdOrder = orderService.update(order);
    log.info("Order created automatically {}", createdOrder);
  }
}
