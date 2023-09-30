package com.aqua.aquabe.model.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FileVO {
    private String imageFile;
    private String imageTitle;
    private String imageAlt;
    private String imageKind;
}
