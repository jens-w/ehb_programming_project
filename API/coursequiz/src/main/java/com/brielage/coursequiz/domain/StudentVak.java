package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@IdClass(StudentVak.class)
@Table(name = "studentenvakken")
public class StudentVak
        implements Serializable {
    @Id
    private long userId;
    @Id
    private long vakId;

    protected StudentVak() {
    }

    public StudentVak(long userId,
                      long vakId) {
        this.userId = userId;
        this.vakId = vakId;
    }

    public long getUserId() {
        return userId;
    }

    public long getVakId() {
        return vakId;
    }
}
