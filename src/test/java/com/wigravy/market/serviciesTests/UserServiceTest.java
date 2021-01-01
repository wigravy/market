package com.wigravy.market.serviciesTests;


import com.wigravy.market.entities.User;
import com.wigravy.market.repositories.UsersRepository;
import com.wigravy.market.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UsersRepository usersRepository;

    @Test
    public void findOneUserTest() {
        User userFromDb = new User();
        userFromDb.setFirstName("Zina");
        userFromDb.setLastName("Petrovich");
        userFromDb.setEmail("zin@mail.ru");
        userFromDb.setPhone("89999999999");

        Mockito.doReturn(Optional.of(userFromDb))
                .when(usersRepository)
                .findOneByPhone("89999999999");

        User userFromService = userService.findByPhone("89999999999").get();
        Assertions.assertNotNull(userFromService);
        Mockito.verify(usersRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.eq("89999999999"));
    }
}
