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
    private long userid;
    @Id
    private long vakid;

    protected DocentVak() {
    }

    public DocentVak(long userId,
                     long vakId) {
        this.userid = userId;
        this.vakid = vakId;
    }

    public long getUserId() {
        return userid;
    }

    public long getVakId() {
        return vakid;
    }
}
