package com.pafassigment.fitplus.service;

import com.pafassigment.fitplus.dao.CommentDao;
import com.pafassigment.fitplus.dao.LikeDao;
import com.pafassigment.fitplus.dao.SharedPostDao;
import com.pafassigment.fitplus.dao.UserDao;
import com.pafassigment.fitplus.entity.Post;
import com.pafassigment.fitplus.entity.SharedPost;
import com.pafassigment.fitplus.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SharedPostServiceImpl implements SharedPostService {

    private final SharedPostDao sharedPostDao;
    private final CommentDao commentDao;
    private final LikeDao likeDao;
    private final UserDao userDao;
    private final PostServiceImpl postService;

    public SharedPostServiceImpl(SharedPostDao sharedPostDao, CommentDao commentDao,
                                 LikeDao likeDao, UserDao userDao, PostServiceImpl postService) {
        this.sharedPostDao = sharedPostDao;
        this.commentDao = commentDao;
        this.likeDao = likeDao;
        this.userDao = userDao;
        this.postService = postService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SharedPost> getAllSharedPosts() {
        List<SharedPost> sharedPosts = sharedPostDao.findAll();
        sharedPosts.forEach(this::mapValuesToSharedPost);
        return sharedPosts;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SharedPost> getSharedPostById(String id) {
        Optional<SharedPost> sharedPost = sharedPostDao.findById(id);
        sharedPost.ifPresent(this::mapValuesToSharedPost);
        return sharedPost;
    }

    @Override
    public SharedPost createSharedPost(SharedPost sharedPost) {
        if (sharedPost == null) {
            throw new IllegalArgumentException("Shared post cannot be null");
        }
        return sharedPostDao.save(sharedPost);
    }

    @Override
    public SharedPost updateSharedPost(String id, SharedPost sharedPost) {
        if (sharedPost == null) {
            throw new IllegalArgumentException("Shared post cannot be null");
        }
        Optional<SharedPost> optionalSharedPost = sharedPostDao.findById(id);
        if (optionalSharedPost.isEmpty()) {
            throw new IllegalArgumentException("Shared post with ID " + id + " not found");
        }
        return sharedPostDao.save(sharedPost);
    }


    @Override
    public void deleteSharedPost(String id) {
        sharedPostDao.deleteById(id);
    }

    private void mapValuesToSharedPost(SharedPost sharedPost) {
        sharedPost.setComments(commentDao.findByPostId(sharedPost.getId()));
        sharedPost.setLikes(likeDao.findByPostId(sharedPost.getId()));

        Optional<User> user = userDao.findById(sharedPost.getUserId());
        user.ifPresent(sharedPost::setPostedUser);

        Optional<Post> post = postService.getPostById(sharedPost.getParentPostId());
        post.ifPresent(sharedPost::setSharedPost);
    }
}
