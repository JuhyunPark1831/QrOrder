package com.sideProject.qrOrder.service.order;

import com.sideProject.qrOrder.dto.client.CartInfoDto;
import com.sideProject.qrOrder.dto.order.OrderDto;
import com.sideProject.qrOrder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> getOrderListByCartInfo(List<CartInfoDto> requestDtoList) {

        return null;
    }
}
