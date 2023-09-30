package com.aqua.aquabe.model.common;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponseVO<T> {
    private String successOrNot;
    private String statusCode;
    private T data;
}
