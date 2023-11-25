package com.education.common.data;

import com.education.common.enumeration.EducationEnumerationConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Answer implements EducationEnumerationConverter<Integer> {

    FIRST_OPTION(1), SECOND_OPTION(2), THIRD_OPTION(3);

    @Getter
    private final int code;

    @Override
    public Integer toDbValue() {
        return code;
    }

}
