package com.example.activity_trackerweek8.Mapper;

import com.example.activity_trackerweek8.Dto.TaskDto;
import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Models.Task;
import com.example.activity_trackerweek8.Models.User;

public class DtoToModel {
    public static User userDtoToUser(UserDto userDto){
        User user = User.builder()
                    .username(userDto.getUserName())
                    .email(userDto.getEmail())
                    .password(userDto.getPassword())
                    .build();
        return user;
    }


    //TaskDto to task Mapper
    public static Task taskDtoToTask(TaskDto taskDto){
        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setTitle(taskDto.getTitle());
        task.setUser(taskDto.getUser());
        return task;
    }
}
