package com.pafassigment.fitplus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a shared post in a social media context.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sharedPosts")
public class SharedPost {

    @Id
    private String id;

    @NotBlank(message = "Parent post ID must not be empty")
    private String parentPostId;

    @NotBlank(message = "User ID must not be empty")
    private String userId;

    private LocalDateTime sharedTime = LocalDateTime.now();

    private User postedUser;

    private Post sharedPost;

    private List<@NotBlank(message = "Image URL must not be empty") String> imageUrl;

    @Size(max = 1500, message = "Description must not exceed 500 characters")
    private String description;

    private List<Comment> comments;

    private List<Like> likes;

}
