package com.lld.bookmyshow.models;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Auditorium auditorium;
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Movie movie;
    @Enumerated(EnumType.ORDINAL)
    private Language language;
}
