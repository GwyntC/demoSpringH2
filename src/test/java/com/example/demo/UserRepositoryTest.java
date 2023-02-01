package com.example.demo;

import com.example.demo.model.Test;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;
//Here no need in negative test because CRUDRepository handle it
@SpringBootTest
class UserRepositoryTest {
@Autowired
    UserRepository userRepository;
    @org.junit.jupiter.api.Test
    void RepositoryPositiveTest() {
      Optional<Test> user= userRepository.findById(2);
      Test res=user.get();
      Date date=res.getBirthday();
     String string="1950-02-03 00:00:00.0";
        assertThat(date.toString()).isEqualTo(string);
    }
}
