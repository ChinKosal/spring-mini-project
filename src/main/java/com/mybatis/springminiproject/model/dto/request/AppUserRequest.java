package com.mybatis.springminiproject.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String profileImage;
}
