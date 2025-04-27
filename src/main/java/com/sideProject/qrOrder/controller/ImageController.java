package com.sideProject.qrOrder.controller;

import com.sideProject.qrOrder.common.response.ApiResponse;
import com.sideProject.qrOrder.common.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final FileUtil fileUtil;

    @Value("${spring.dir.menuImagePath}")
    public String path;

    @GetMapping
    public ResponseEntity<Resource> getImage(@RequestParam(name = "imagePath") String imagePath) {
        return fileUtil.getImage(imagePath);
    }

    @PostMapping
    public ApiResponse<String> uploadImage(@RequestParam("file") MultipartFile file) {
        return ApiResponse.ok(fileUtil.saveImage(file, path));
    }
}
