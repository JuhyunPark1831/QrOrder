package com.sideProject.qrOrder.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("/manage")
    public String manageOrderPage() {
        return "/manager/pages/order/manageOrder";
    }
}