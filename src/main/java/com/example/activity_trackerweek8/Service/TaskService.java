package com.example.activity_trackerweek8.Service;

import com.example.activity_trackerweek8.Dto.TaskDto;
import com.example.activity_trackerweek8.Exceptions.CustomExceptions;
import com.example.activity_trackerweek8.Models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    List<TaskDto> listOfTaskDto(Long id);
    List<TaskDto> listOfTaskDtoByStatus(String status, Long id);

    Task createNewTask(TaskDto taskDto);

    void deleteTask(TaskDto taskDto);

    void editTask(TaskDto taskDto);

    int Start(Long id) throws CustomExceptions;

    int endTask(Long id) throws CustomExceptions;

    List<TaskDto> findAllPending(Long id);

    List<TaskDto> findAllCompleted(Long id);

    List<TaskDto> findAllInProgress(Long id);

    List<TaskDto> searchedItem(Long id, String title) throws CustomExceptions;

    Page<TaskDto> findTasksByUserIdPaginated(List<TaskDto> tasks, Pageable pageable);
}
