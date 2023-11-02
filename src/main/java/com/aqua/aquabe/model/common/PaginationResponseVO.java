package com.aqua.aquabe.model.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@SuperBuilder
public class PaginationResponseVO<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalCount;

    private List<T> list;

    public PaginationResponseVO(List<T> list) {
        this.list = list;
    }
}
