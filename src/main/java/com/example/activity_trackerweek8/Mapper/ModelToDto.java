package com.example.activity_trackerweek8.Mapper;

import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Models.User;

public class ModelToDto {
    //MapperClass to map user  to userDto
    public static UserDto UserToDto(User user){
        UserDto userDto = UserDto.builder()
                .userId(user.getId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        return userDto;
    }
}
