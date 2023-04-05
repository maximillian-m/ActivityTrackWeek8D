package com.example.activity_trackerweek8.Repositories;

import com.example.activity_trackerweek8.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
