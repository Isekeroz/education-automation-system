package com.education.common.enumeration.converter;

import com.education.common.enumeration.EducationEnumerationConverter;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class EducationEnumAttributeConverter<T extends Enum<T> & EducationEnumerationConverter<E>, E>
        implements AttributeConverter<T, E> {

    private final Class<T> clazz;

    protected EducationEnumAttributeConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return Objects.nonNull(attribute) ? attribute.toDbValue() : null;
    }

    @Override
    public T convertToEntityAttribute(E dbValue) {
        if (Objects.isNull(dbValue)) {
            return null;
        }

        return Stream.of(clazz.getEnumConstants())
                .filter(enumConstant -> Objects.equals(enumConstant.toDbValue(), dbValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
