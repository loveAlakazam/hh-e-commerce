package io.hh_plus.ecommerce.ecommerce.domain.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content; // 데이터목록
    private long totalElements; // 총 데이터 개수
    private int totalPages; // 총 페이지 수
    private int offset; // 요청 offset
    private int limit; // 요청 limit
}
