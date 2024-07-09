package com.pafassigment.fitplus.service;


import com.pafassigment.fitplus.entity.Comment;

import java.util.List;


public interface CommentService {

    List<Comment> getCommentsByPostId(String postId);

    List<Comment> getCommentsByUserId(String userId);

    Comment addComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(String id, String userId);
}
