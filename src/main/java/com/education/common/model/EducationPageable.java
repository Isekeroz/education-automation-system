package com.education.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(value = "sort")
public class EducationPageable extends PageRequest implements Serializable {

    private static final int DEFAULT_PAGE_NUMBER = 0;

    private static final int DEFAULT_PAGE_SIZE = 20;

    public EducationPageable() {
        super(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE, Sort.unsorted());
    }

    public EducationPageable(int pageNumber, int pageSize) {
        super(pageNumber, pageSize, Sort.unsorted());
    }

}
