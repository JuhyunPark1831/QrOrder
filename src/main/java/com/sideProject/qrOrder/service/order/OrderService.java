package com.sideProject.qrOrder.service.order;

import com.sideProject.qrOrder.dto.client.CartInfoRequestDto;
import com.sideProject.qrOrder.dto.order.OrderDto;

import java.util.List;

public interface OrderService {

    public List<OrderDto> getOrderListByCartInfo(List<CartInfoRequestDto> requestDtoList);
}
