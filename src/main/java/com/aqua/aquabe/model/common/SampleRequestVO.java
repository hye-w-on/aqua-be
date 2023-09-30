package com.aqua.aquabe.model.common;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleRequestVO {
    private String successOrNot;
}
