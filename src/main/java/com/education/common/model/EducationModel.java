package com.education.common.model;

import com.education.common.model.constraint.EducationModelConstraint;
import com.education.common.model.constraint.validation.EducationConstraintValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class EducationModel implements Serializable {

    private Long id;

    private Integer version;

    @NotNull(groups = {EducationConstraintValidationGroups.ModifyingOperation.class})
    private OffsetDateTime createdDate;

    @NotNull(groups = {EducationConstraintValidationGroups.ModifyingOperation.class})
    private OffsetDateTime updatedDate;

    @Size(max = EducationModelConstraint.CREATED_BY_SIZE)
    @NotBlank(groups = {EducationConstraintValidationGroups.ModifyingOperation.class})
    private String createdBy;

    @Size(max = EducationModelConstraint.UPDATED_BY_SIZE)
    private String updatedBy;

}
