package com.programacion.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@JsonFormat
@ToString
public class Album {
    @Getter @Setter private Integer id;
    @Getter @Setter private Integer singer_id;
    @Getter @Setter private String title;
    @Getter @Setter @DateTimeFormat(pattern = "yyyy-MM-dd") private Date release_date;
    //@Getter @Setter private Date release_date;
}
