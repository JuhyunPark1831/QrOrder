package com.sideProject.qrOrder.controller.closingFixed;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.dto.closingFixed.ClosingFixedDto;
import com.sideProject.qrOrder.service.closingFixed.ClosingFixedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/closing-fixed")
@RequiredArgsConstructor
public class ClosingFixedRestController {

    private final ClosingFixedService closingFixedService;

    @PostMapping("/create")
    public ApiResponse<String> createClosingFixed(@RequestBody ClosingFixedDto requestDto) {

        closingFixedService.createClosingFixed(requestDto);

        return ApiResponse.ok("정기휴무가 등록 되었습니다");
    }

    @PutMapping("/modify/toggle/use-status")
    public ApiResponse<String> modifyClosingFixedUseStatus(@RequestBody ClosingFixedDto requestDto) {

        closingFixedService.modifyClosingFixedUseStatus(requestDto);

        return ApiResponse.ok("정기휴무 사용여부가 변경되었습니다");
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteClosingFixed(@RequestBody List<Long> cfIds) {

        closingFixedService.deleteClosingFixed(cfIds);

        return ApiResponse.ok("정기휴무가 삭제되었습니다");
    }
}
