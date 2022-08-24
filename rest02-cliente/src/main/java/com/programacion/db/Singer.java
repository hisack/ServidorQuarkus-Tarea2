package com.programacion.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Singer {
    @Getter @Setter private Integer id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter @DateTimeFormat(pattern = "yyyy-MM-dd") private Date birthDate;

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
