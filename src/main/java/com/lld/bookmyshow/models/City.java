package com.lld.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City extends BaseModel{
    @OneToMany(mappedBy = "city") // name of the attribute in the other class that is rep the relation
    private List<Theater> theaters;
    private String name;
}
