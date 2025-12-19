package com.example.booking_tour.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.booking_tour.service.FileStorageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/upload")
@Tag(name = "Upload", description = "Các API liên quan đến tải lên tệp tin")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Operation(summary = "Tải lên hình ảnh", description = "Chấp nhận các tệp tin hình ảnh (jpg, png...), kích thước tối đa 5MB")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Tải lên thành công"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Tệp tin không hợp lệ hoặc quá lớn")
    })
    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<com.example.booking_tour.dto.ApiResponse<Map<String, String>>> uploadImage(
            @Parameter(description = "Tệp tin hình ảnh cần tải lên") @RequestParam("file") MultipartFile file) {

        try {
            // Validate file
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Please select a file to upload",
                                null));
            }

            // Check if file is an image
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest()
                        .body(new com.example.booking_tour.dto.ApiResponse<>(false, "Only image files are allowed",
                                null));
            }

            // Check file size (max 5MB)
            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest()
                        .body(new com.example.booking_tour.dto.ApiResponse<>(false, "File size must not exceed 5MB",
                                null));
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
                    new com.example.booking_tour.dto.ApiResponse<>(true, "File uploaded successfully", response));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new com.example.booking_tour.dto.ApiResponse<>(false,
                            "Could not upload file: " + e.getMessage(), null));
        }
    }
}
