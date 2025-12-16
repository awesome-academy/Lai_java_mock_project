package com.example.booking_tour.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.booking_tour.dto.ApiResponse;
import com.example.booking_tour.service.FileStorageService;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/image")
    public ResponseEntity<ApiResponse<Map<String, String>>> uploadImage(
            @RequestParam("file") MultipartFile file) {

        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(false, "Please select a file to upload", null));
            }

            // Check if file is an image
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(false, "Only image files are allowed", null));
            }

            // Check file size (max 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse<>(false, "File size must not exceed 5MB", null));
            }

            // Store file
            String fileName = fileStorageService.storeFile(file);

            // Generate file URL
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

            Map<String, String> response = new HashMap<>();
            response.put("fileName", fileName);
            response.put("url", fileDownloadUri);
            response.put("fileType", file.getContentType());
            response.put("size", String.valueOf(file.getSize()));

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "File uploaded successfully", response));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>(false, "Could not upload file: " + e.getMessage(), null));
        }
    }
}
