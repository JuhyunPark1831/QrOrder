package com.sideProject.qrOrder.common.interceptor;

import com.sideProject.qrOrder.service.closing.ClosingService;
import com.sideProject.qrOrder.service.closingFixed.ClosingFixedService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ClosingInterceptor implements HandlerInterceptor {

    private final ClosingService closingService;
    private final ClosingFixedService closingFixedService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (closingService.checkNowIsClosing() || closingFixedService.checkNowIsClosingFixed()) {
            response.sendRedirect("/account/manage");
            return false;
        }

        return true;
    }
}
