package com.mybatis.springminiproject.model.responseApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponses <T> {
    private String message;
    private T payload;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}
