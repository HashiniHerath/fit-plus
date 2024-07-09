package com.pafassigment.fitplus.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Represents a like made by a user on a post within the system.
 */
@Data
@Builder
@Document(collection = "likes")
public class Like {
    @Id
    private String id;

    @NotBlank(message = "User ID must not be blank")
    private String userId;

    @NotBlank(message = "Post ID must not be blank")
    private String postId;

    private LocalDateTime likedTime = LocalDateTime.now();

    private User likedUser;

}
