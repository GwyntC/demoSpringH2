package com.example.demo.service;

import com.example.demo.model.Test;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    @Autowired
private final UserRepository userRepository;

    private Test getInfo(int id) {
        try {
            Optional<Test> user = userRepository.findById(id);
            return user.get();
        }
        catch (Exception ex){
            throw new  IllegalArgumentException("Error message: "+ex);
        }
    }

    @Override
    public User getUser(int id) {
      Test test=getInfo(id);
         Date birth=test.getBirthday();
          LocalDate birthLocal=LocalDate.parse(birth.toString());
         int age= Period.between(birthLocal, LocalDate.now()).getYears();
      User user=new User(test.getName(),test.getSurname(),age);
      return user;
    }
}
