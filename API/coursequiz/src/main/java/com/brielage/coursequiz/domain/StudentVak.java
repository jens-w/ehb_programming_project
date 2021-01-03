package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@IdClass(StudentVak.class)
@Table(name = "studentenvakken")
public class StudentVak
        implements Serializable {
    @Id
    private long userid;
    @Id
    private long vakid;

    protected StudentVak() {
    }

    public StudentVak(long userId,
                      long vakId) {
        this.userid = userId;
        this.vakid = vakId;
    }

    public long getUserid() {
        return userid;
    }

    public long getVakId() {
        return vakid;
    }
}
