package com.avinash.kumar.module2.advices;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private ApiError error;
    private LocalDateTime timeStamp;
}
