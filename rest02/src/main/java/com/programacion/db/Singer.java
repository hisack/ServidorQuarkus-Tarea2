package com.programacion.db;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//import java.sql.Date;
import java.util.Date;

@ToString
public class Singer {
    @Getter @Setter private Integer id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private Date birthDate;

}
