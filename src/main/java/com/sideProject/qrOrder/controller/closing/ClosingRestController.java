package com.sideProject.qrOrder.controller.closing;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.category.CategoryRequestDto;
import com.sideProject.qrOrder.dto.closing.ClosingRequestDto;
import com.sideProject.qrOrder.service.category.CategoryService;
import com.sideProject.qrOrder.service.closing.ClosingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/closing")
@RequiredArgsConstructor
public class ClosingRestController {

    private final ClosingService closingService;

    @PostMapping("/create")
    public ApiResponse<String> createClosing(@RequestBody ClosingRequestDto requestDto) {

        closingService.createClosing(requestDto);

        return ApiResponse.ok("임시휴무가 등록 되었습니다");
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteClosing(@RequestBody List<Long> clIds) {

        closingService.deleteClosing(clIds);

        return ApiResponse.ok("임시휴무가 삭제되었습니다");
    }
}
