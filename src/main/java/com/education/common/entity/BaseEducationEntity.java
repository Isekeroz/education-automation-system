package com.education.common.entity;

import java.io.Serializable;

public interface BaseEducationEntity<ID extends Number> extends Serializable {

    ID getId();

    void setId(ID id);

}
