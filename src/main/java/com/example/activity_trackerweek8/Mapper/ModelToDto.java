package com.example.activity_trackerweek8.Mapper;

import com.example.activity_trackerweek8.Dto.TaskDto;
import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Models.Task;
import com.example.activity_trackerweek8.Models.User;

import java.util.ArrayList;
import java.util.List;

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

    public static TaskDto taskDto (Task task){
        TaskDto taskDto = TaskDto.builder()
                .taskId(task.getId())
                .status(task.getStatus())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .description(task.getDescription())
                .title(task.getTitle())
                .updateAt(task.getUpdateAt())
                .build();
        return taskDto;
    }


    //Mapper from taskList to taskDtoList
    public static List<TaskDto> taskDtoList (List<Task> taskList){
        List<TaskDto> taskDtos = new ArrayList<>();
        for(Task t : taskList){
            taskDtos.add(ModelToDto.taskDto(t));
        }
        return taskDtos;
    }
}
