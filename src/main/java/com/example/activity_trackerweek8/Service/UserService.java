package com.example.activity_trackerweek8.Service;

import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Models.User;

public interface UserService {
    User findUserById(UserDto userDto);
    User registerUsers(UserDto userdto);
    boolean validateEmail(User user);
    User getUserByEmail(String email);
    User loginUser(UserDto userdto);

}
