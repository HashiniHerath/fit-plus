package com.pafassigment.fitplus.service;

import com.pafassigment.fitplus.entity.Comment;
import com.pafassigment.fitplus.exception.DataNotFoundException;
import com.pafassigment.fitplus.dao.CommentDao;
import com.pafassigment.fitplus.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserDao userDao;
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.userDao = userDao;
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        return commentDao.findByPostId(postId);
    }

    @Override
    public List<Comment> getCommentsByUserId(String userId) {
        return commentDao.findByUserId(userId);
    }

    @Override
    @Transactional
    public Comment addComment(Comment comment) {
        comment.setCommentedTime(LocalDateTime.now());
        Comment savedComment = commentDao.save(comment);
        setUserForComment(savedComment);
        return savedComment;
    }

    @Override
    @Transactional
    public Comment updateComment(Comment comment) {
        return commentDao.findById(comment.getId())
                .map(existingComment -> updateExistingComment(existingComment, comment))
                .orElseThrow(() -> new DataNotFoundException("Comment not found with ID: " + comment.getId()));
    }

    private Comment updateExistingComment(Comment existingComment, Comment updatedComment) {
        existingComment.setText(updatedComment.getText());
        Comment savedComment = commentDao.save(existingComment);
        setUserForComment(savedComment);
        return savedComment;
    }

    @Override
    @Transactional
    public void deleteComment(String id, String userId) {
        Comment comment = commentDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment not found with ID: " + id));

        if (!comment.getUserId().equals(userId)) {
            throw new DataNotFoundException("You don't have permission to delete this comment.");
        }

        commentDao.deleteById(id);
    }

    private void setUserForComment(Comment comment) {
        userDao.findById(comment.getUserId())
                .ifPresent(comment::setCommentedUser);
    }
}
