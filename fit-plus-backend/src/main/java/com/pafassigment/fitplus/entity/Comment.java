package com.pafassigment.fitplus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents a comment on a post in the system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @NotBlank(message = "Post ID must not be blank")
    private String postId;

    @NotBlank(message = "User ID must not be blank")
    private String userId;

    private LocalDateTime commentedTime = LocalDateTime.now();

    private User commentedUser;

    @NotBlank(message = "Text must not be empty")
    private String text;

}
