package com.sideProject.qrOrder.service.order;

import com.sideProject.qrOrder.dto.client.CartInfoDto;
import com.sideProject.qrOrder.dto.closing.ClosingDto;
import com.sideProject.qrOrder.dto.order.OrderDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    public List<OrderDto> getOrderListByCartInfo(List<CartInfoDto> requestDtoList);
}
