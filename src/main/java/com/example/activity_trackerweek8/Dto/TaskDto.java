package com.example.activity_trackerweek8.Dto;

import com.example.activity_trackerweek8.Enums.Status;
import com.example.activity_trackerweek8.Models.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long taskId;
    private String title;
    private String description;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    private LocalDateTime completed;
    private User user;
}
