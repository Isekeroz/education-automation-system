package com.education.module.student.model;

import com.education.common.model.EducationModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.education.module.student.constraint.StudentConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentModel extends EducationModel {

    @NotBlank
    @Size(max = NAME_MAX_SIZE)
    private String name;

    @NotBlank
    @Size(max = SURNAME_MAX_SIZE)
    private String surname;

    @NotBlank
    @Size(max = EMAIL_MAX_SIZE)
    @Email
    private String email;

}
