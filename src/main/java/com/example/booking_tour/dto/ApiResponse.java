package com.example.booking_tour.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Cấu trúc phản hồi chung của API")
public class ApiResponse<T> {
    @Schema(description = "Trạng thái thành công", example = "true")
    private boolean success;

    @Schema(description = "Thông báo phản hồi", example = "Thao tác thành công")
    private String message;

    @Schema(description = "Dữ liệu trả về (có thể null nếu không có dữ liệu)")
    private T data;
}
