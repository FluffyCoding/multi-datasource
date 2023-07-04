package com.unity.multi.sources.stores.service;


import com.unity.multi.sources.stores.dtos.OrderDto;
import com.unity.multi.sources.stores.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper orderMapper;


    public Page<OrderDto> getOrderByPage(int page, int size) {

        Pageable pageRequest = PageRequest.of(page, size);
        return orderRepository.findAll(pageRequest)
                .map(element -> orderMapper.map(element, OrderDto.class));
    }


    public OrderDto getOrderById(Integer id) {
        return orderRepository.findById(id)
                .map(element -> orderMapper.map(element, OrderDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Invalid Order Id"));
    }

}