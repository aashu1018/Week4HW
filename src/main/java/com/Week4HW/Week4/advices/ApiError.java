package com.Week4HW.Week4.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatusCode httpStatusCode;
}
