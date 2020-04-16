package com.project.finance.repository;

import com.project.finance.TestData;
import com.project.finance.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @InjectMocks
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;


//    @Test
//    public void it_should_save_user() {
//        userRepository.save();
//        User user = new User();
//    user.setName("test user");
//    user = entityManager.persistAndFlush(user);
//    assertThat(sut.findById(user.getId()).get()).isEqualTo(user);    }


//    @Test
//    public void it_should_find_user_byEmail() {    U
//    ser user = new User();
//    user.setEmail("testmail@test.com");
//    user = entityManager.persistAndFlush(user);
//    assertThat(sut.findByEmail(user.getEmail()).get()).isEqualTo(user);}

    @Test
    public void findById_ReturnsTheCorrectUser() {


    }

    @Test
    public void save() {
    }

    @Test
    public void findByFirstName() {
    }
}