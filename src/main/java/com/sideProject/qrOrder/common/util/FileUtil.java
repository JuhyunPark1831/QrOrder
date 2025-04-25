package com.sideProject.qrOrder.common.util;

import com.sideProject.qrOrder.common.error.ApiCustomException;
import com.sideProject.qrOrder.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
@RequiredArgsConstructor
public class FileUtil {

    public void fileCheck(String fileFullPath) {
        File file = new File(fileFullPath);

        if (!file.exists()) {
            throw new ApiCustomException(ErrorCode.NOT_FOUND_FILE);
        }
    }

    public String saveImage(MultipartFile image, String path) {

        try {

            File dir = new File(path);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String filePath = path + File.separator + image.getOriginalFilename();

            File file = new File(filePath);
            image.transferTo(file);

            return filePath;
        } catch (IOException e) {
            throw  new ApiCustomException(ErrorCode.IMAGE_SAVE_FAILED);
        }
    }

    public ResponseEntity<Resource> getImage(String imagePath) {

        try {
            File file = new File(imagePath);
            fileCheck(imagePath);

            Resource resource = new FileSystemResource(file);

            HttpHeaders headers = new HttpHeaders();

            String contentType = Files.probeContentType(file.toPath());
            headers.add(HttpHeaders.CONTENT_TYPE, contentType != null ? contentType : "application/octet-stream");

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new ApiCustomException(ErrorCode.IMAGE_DOWNLOAD_FAILED);
        }
    }

    public String extractExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
