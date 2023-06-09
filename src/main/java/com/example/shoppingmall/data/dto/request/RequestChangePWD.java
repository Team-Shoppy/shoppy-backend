package com.example.shoppingmall.data.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestChangePWD {
    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min =  8, max = 16, message = "비밀번호는 최소 8자이상 16자 이하입니다.")
    private String origin_password;

    @NotEmpty(message = "비밀번호 입력은 필수 입니다.")
    @Size(min =  8, max = 16, message = "비밀번호는 최소 8자이상 16자 이하입니다.")
    private String new_password;
}
