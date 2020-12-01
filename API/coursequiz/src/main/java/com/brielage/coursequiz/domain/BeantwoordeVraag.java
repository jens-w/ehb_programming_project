package com.brielage.coursequiz.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
@Entity
@Table(name = "beantwoordevragen")
public class BeantwoordeVraag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private long vraagId;
    @NotBlank
    private boolean juistBeantwoord;
    @NotBlank
    private LocalDateTime jaarMaand;
    @NotBlank
    private boolean isBeantwoord;

    protected BeantwoordeVraag() {
    }

    public BeantwoordeVraag(long id,
                            @NotBlank long vraagId,
                            @NotBlank boolean juistBeantwoord,
                            @NotBlank LocalDateTime jaarMaand,
                            @NotBlank boolean isBeantwoord) {
        this.id = id;
        this.vraagId = vraagId;
        this.juistBeantwoord = juistBeantwoord;
        this.jaarMaand = jaarMaand;
        this.isBeantwoord = isBeantwoord;
    }
}
