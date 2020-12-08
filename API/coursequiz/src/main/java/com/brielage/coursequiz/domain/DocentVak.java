package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@SuppressWarnings("unused")
@Entity
@IdClass(DocentVak.class)
@Table(name = "docentenvakken")
public class DocentVak
        implements Serializable {
    @Id
    private long userId;
    @Id
    private long vakId;

    protected DocentVak() {
    }

    public DocentVak(long userId,
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
