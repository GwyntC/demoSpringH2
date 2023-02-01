package com.example.demo.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
    private String name;
    private String surname;
    private int age;
}
