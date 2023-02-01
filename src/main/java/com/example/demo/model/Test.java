package com.example.demo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Builder
@Data
public class Test {
    @Id
    private long id;
    private String name;
    private String surname;
    private Date birthday;
}
