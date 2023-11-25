package com.education.common.model.query;

import com.education.common.model.EducationPageable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class EducationQueryModel implements Serializable {

    private final EducationPageable pageable = new EducationPageable();

}
