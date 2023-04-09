package com.example.activity_trackerweek8.Repositories;

import com.example.activity_trackerweek8.Enums.Status;
import com.example.activity_trackerweek8.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findTaskById(Long Id);
    //Native query to search list of task by userid;
    @Query(value = "SELECT * From tasks_table t WHERE t.user_id = ?1", nativeQuery = true)
    List<Task> listOfTask(Long id);

    //Native query to get the list of task by userid by status and by id
    @Query(value = "SELECT * FROM tasks_table t WHERE t.user_id = ?1 AND t.status = ?2", nativeQuery = true)
    List<Task> listOfTaskByStatus(String status, Long id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE tasks_table  SET description = ?1, title = ?2  WHERE id =?3 ", nativeQuery = true)
    void updateTask(String description, String title, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tasks_table SET update_at = ?1 WHERE id = ?2", nativeQuery = true)
    void updateUpdateAt(LocalDateTime time, Long id);


    @Transactional
    @Modifying
    @Query(value="UPDATE tasks_table  SET status = ?1 WHERE id = ?2", nativeQuery = true)
    void startingTask(String status, Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tasks_table SET completed = ?1 WHERE id = ?2", nativeQuery = true)
    void updateCompletedAt(LocalDateTime time, Long id);

    @Query(value = "SELECT * FROM tasks_table WHERE status = 'PENDING' AND user_id = ?1", nativeQuery = true)
    Optional<Task> findAllPending(Long id);

    @Query(value = "SELECT * FROM tasks_table WHERE status = 'IN_PROGRESS' AND user_id = ?1", nativeQuery = true)
    Optional<Task> findAllInProgress(Long id);

    @Query(value = "SELECT * FROM tasks_table WHERE status = 'COMPLETED' AND user_id = ?1", nativeQuery = true)
    Optional<Task> findAllCompleted(Long id);

    @Query(value = "SELECT * FROM tasks_table WHERE user_id = ?1 AND title LIKE %?2%", nativeQuery = true)
    List<Task> findBySearched(Long id, String searched);

}
