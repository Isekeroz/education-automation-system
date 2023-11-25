package com.education.common.entity;

import com.education.common.entity.constraint.EducationDomainConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@MappedSuperclass
public abstract class EducationEntity implements BaseEducationEntity<Long> {

    public static final String SEQUENCE_GENERATOR_NAME = "educationSeqGen";

    private static final String ID_FIELD_COLUMN = "ID";

    private static final String VERSION_FIELD_COLUMN = "OBJ_VERSION";

    private static final String CREATED_DATE_FIELD_COLUMN = "CREATED_DATE";

    private static final String UPDATED_DATE_FIELD_COLUMN = "UPDATED_DATE";

    private static final String CREATED_BY_FIELD_COLUMN = "CREATED_BY";

    private static final String UPDATED_BY_FIELD_COLUMN = "UPDATED_BY";

    private static final String UNKNOWN_USER_NAME = "unknown";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Column(name = ID_FIELD_COLUMN, updatable = false, nullable = false)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = VERSION_FIELD_COLUMN)
    @Version
    private Integer version;

    @Column(name = CREATED_DATE_FIELD_COLUMN, updatable = false, nullable = false)
    @CreationTimestamp
    @CreatedDate
    private OffsetDateTime createdDate;

    @Column(name = UPDATED_DATE_FIELD_COLUMN, nullable = false)
    @UpdateTimestamp
    @LastModifiedDate
    private OffsetDateTime updatedDate;

    @Column(name = CREATED_BY_FIELD_COLUMN, updatable = false, nullable = false)
    @Size(max = EducationDomainConstraint.CREATED_BY_SIZE)
    @CreatedBy
    private String createdBy;

    @Column(name = UPDATED_BY_FIELD_COLUMN, nullable = false)
    @Size(max = EducationDomainConstraint.UPDATED_BY_SIZE)
    @LastModifiedBy
    private String updatedBy;

    @PrePersist
    protected void prePersist() {
        this.createdBy = UNKNOWN_USER_NAME;
        this.updatedBy = UNKNOWN_USER_NAME;
    }

    @PreUpdate
    protected void preUpdate() {
        this.createdBy = UNKNOWN_USER_NAME;
        this.updatedBy = UNKNOWN_USER_NAME;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
