package com.example.activity_trackerweek8;

import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Mapper.DtoToModel;
import com.example.activity_trackerweek8.Models.User;
import com.example.activity_trackerweek8.Repositories.UserRepo;
import com.example.activity_trackerweek8.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void toTestRegisterUsers(){
        UserDto userDto = new UserDto(2L, "Mike", "mike@yahoo.com", "1234");
        User user = DtoToModel.userDtoToUser(userDto);
        when(userRepo.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUsers(userDto);
        Assertions.assertEquals("Mike", savedUser.getUsername());
    }

    @Test
    public void ToFindUserById(){
        UserDto userDto = new UserDto(2L, "Mike", "mike@yahoo.com", "1234");
        User user = DtoToModel.userDtoToUser(userDto);
        user.setId(userDto.getUserId());
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));

        User userForFInd = userService.findUserById(userDto);
        Assertions.assertTrue(userForFInd != null);
    }

    @Test
    public void toTestLoginForUser(){
        UserDto userDto = new UserDto(2L, "Mike", "mike@yahoo.com", "1234");
        User user = DtoToModel.userDtoToUser(userDto);
        user.setId(userDto.getUserId());
        when(userRepo.findEmailAndPassWord(user.getEmail(), user.getPassword())).thenReturn(user);

        User userLoggedIn = userService.loginUser(userDto);
        Assertions.assertTrue(userLoggedIn.getEmail() != null && userLoggedIn.getPassword() != null);
    }
}
