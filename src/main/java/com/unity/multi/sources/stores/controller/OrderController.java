package com.unity.multi.sources.stores.controller;

import com.unity.multi.sources.invoicing.dtos.ClientDto;
import com.unity.multi.sources.stores.dtos.OrderDto;
import com.unity.multi.sources.stores.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;


    @GetMapping(path = "/all")
    public Page<OrderDto> getClintesByPage(@RequestParam Integer page, @RequestParam Integer size) {
        return orderService.getOrderByPage(page, size);
    }

    @GetMapping
    public ResponseEntity<OrderDto> getClientById(@RequestParam Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


}
