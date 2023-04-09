package com.example.activity_trackerweek8.serviceImpl;

import com.example.activity_trackerweek8.Dto.TaskDto;
import com.example.activity_trackerweek8.Enums.Status;
import com.example.activity_trackerweek8.Exceptions.CustomExceptions;
import com.example.activity_trackerweek8.Mapper.DtoToModel;
import com.example.activity_trackerweek8.Mapper.ModelToDto;
import com.example.activity_trackerweek8.Models.Task;
import com.example.activity_trackerweek8.Repositories.TaskRepository;
import com.example.activity_trackerweek8.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<TaskDto> listOfTaskDto(Long user_id) {
        List<Task> listOfTask = taskRepository.listOfTask(user_id);
        List<TaskDto> dtoList = ModelToDto.taskDtoList(listOfTask);
        return dtoList;
    }

    @Override
    public List<TaskDto> listOfTaskDtoByStatus(String status, Long id) {
        List<Task> listOfTask = taskRepository.listOfTaskByStatus(status, id);
        List<TaskDto> dtoLists = ModelToDto.taskDtoList(listOfTask);
        return dtoLists;
    }


    //Method to save task
    @Override
    public Task createNewTask(TaskDto taskDto) {
        Task task = DtoToModel.taskDtoToTask(taskDto);
       return taskRepository.save(task);
    }


    @Override
    public void deleteTask(TaskDto taskDto){
       Task task = taskRepository.findTaskById(taskDto.getTaskId()).get();
       taskRepository.delete(task);
    }

    @Override
    public void editTask(TaskDto taskDto) {
        taskRepository.updateTask(taskDto.getDescription(), taskDto.getTitle(), taskDto.getTaskId());
        taskRepository.updateUpdateAt(LocalDateTime.now(), taskDto.getTaskId());
    }


    @Override
    public int Start(Long id) throws CustomExceptions {
        Task task = taskRepository.findTaskById(id).get();
        if(task.getStatus() == Status.PENDING){
           taskRepository.startingTask(String.valueOf(Status.IN_PROGRESS), id);
           taskRepository.updateUpdateAt(LocalDateTime.now(), id);
           return 1;
        }
        throw new CustomExceptions("You cannot start an already started task, Consider deleting and starting again");
    }
    @Override
    public int endTask(Long id) throws CustomExceptions {
        Task task = taskRepository.findTaskById(id).get();
        if(task.getStatus() == Status.IN_PROGRESS || task.getStatus() == Status.PENDING){
            taskRepository.startingTask(String.valueOf(Status.COMPLETED), id);
            taskRepository.updateCompletedAt(LocalDateTime.now(), id);
            return 1;
        }
        throw  new CustomExceptions("Task has been Completed and cannot be moved further");
    }
    @Override
    public List<TaskDto> findAllPending(Long id){
        List<Task> tasks = taskRepository.findAllPending(id).stream().collect(Collectors.toList());
        return ModelToDto.taskDtoList(tasks);
    }
    @Override
    public List<TaskDto> findAllCompleted(Long id){
        List<Task> tasks = taskRepository.findAllCompleted(id).stream().collect(Collectors.toList());
        return ModelToDto.taskDtoList(tasks);
    }

    @Override
    public List<TaskDto> findAllInProgress(Long id){
        List<Task> tasks = taskRepository.findAllInProgress(id).stream().collect(Collectors.toList());
        return ModelToDto.taskDtoList(tasks);
    }

    public List<TaskDto> searchedItem(Long id, String title) throws CustomExceptions{
        List<Task> tasks = taskRepository.findBySearched(id, title);
        if(!tasks.isEmpty()){
            return ModelToDto.taskDtoList(tasks);
        }
        throw new CustomExceptions("No Task match your entry. Please check");
    }
}
