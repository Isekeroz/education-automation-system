package com.education.module.student.model.query;

import com.education.common.model.query.EducationQueryModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static com.education.module.student.constraint.StudentConstraint.*;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StudentQueryModel extends EducationQueryModel {

    private Long number;

    @Size(max = NAME_MAX_SIZE)
    private String name;

    @Size(max = SURNAME_MAX_SIZE)
    private String surname;

    @Size(max = EMAIL_MAX_SIZE)
    @Email
    private String email;

}
