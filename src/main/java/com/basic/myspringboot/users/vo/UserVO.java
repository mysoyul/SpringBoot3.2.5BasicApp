package com.basic.myspringboot.users.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserVO {
    @NotEmpty(message = "Name은 필수 입력항목입니다!")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;
}
