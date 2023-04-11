package com.example.activity_trackerweek8;

import com.example.activity_trackerweek8.Dto.TaskDto;
import com.example.activity_trackerweek8.Dto.UserDto;
import com.example.activity_trackerweek8.Enums.Status;
import com.example.activity_trackerweek8.Mapper.DtoToModel;
import com.example.activity_trackerweek8.Mapper.ModelToDto;
import com.example.activity_trackerweek8.Models.Task;
import com.example.activity_trackerweek8.Models.User;
import com.example.activity_trackerweek8.Repositories.TaskRepository;
import com.example.activity_trackerweek8.serviceImpl.TaskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {
        @Mock
    TaskRepository taskRepository;

        @InjectMocks
    TaskServiceImpl taskService;

    UserDto userDto = new UserDto(2L, "Mike", "mike@yahoo.com", "1234");
    User user = DtoToModel.userDtoToUser(userDto);
    Task task = Task.builder().description("We are moving too fast").id(12L).status(Status.PENDING).createdAt(LocalDateTime.now()).user(user).build();
        @Test
    public void toCheckForTheListOfTasks(){
            List<Task> taskList = new ArrayList<>();
            taskList.add(task);

            when(taskRepository.listOfTask(12L)).thenReturn(taskList);

            List<TaskDto> taskDtoList = taskService.listOfTaskDto(12L);

            Assertions.assertTrue(taskDtoList.size() > 0);
        }

        @Test
    public void testToCreateTaskDto(){
            List<Task> tasking = new ArrayList<>();
            TaskDto taskDto = ModelToDto.taskDto(task);
            when(taskRepository.save(any(Task.class))).thenReturn(task);
            Task tasker = taskService.createNewTask(taskDto);
            Assertions.assertTrue(tasker.getStatus() == Status.PENDING );
        }

        @Test
    public void toTestForListByStatus(){
            List<Task> list = new ArrayList<>();
            list.add(task);

            when(taskRepository.listOfTaskByStatus(String.valueOf(task.getStatus()), task.getId())).thenReturn(list);

            List<TaskDto> taskDto = taskService.listOfTaskDtoByStatus(String.valueOf(Status.PENDING), 12L);

            Assertions.assertEquals(1, taskDto.size());
        }

    @Test
    public void toTestEditMethod(){
        TaskDto taskdto = ModelToDto.taskDto(task);
        taskService.editTask(taskdto);
        verify(taskRepository).updateTask(taskdto.getDescription(), taskdto.getTitle(), taskdto.getTaskId());
    }
}
