package com.salesianostriana.edu.trillium.comment.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;

    private long totalElements;

    private int totalPages;

    private int page;

    public PageResponse(Page<T> page){
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.totalElements = page.getTotalElements();
    }
}
