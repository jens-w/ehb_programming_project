package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("unused")
@Entity
@Table(name = "studentenvakken")
public class StudentVak {
    @NotBlank
    private long userId;
    @NotBlank
    private long vakId;
    // TODELETE
    private String id;

    protected StudentVak() {
    }

    public StudentVak(@NotBlank long userId,
                      @NotBlank long vakId) {
        this.userId = userId;
        this.vakId = vakId;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
