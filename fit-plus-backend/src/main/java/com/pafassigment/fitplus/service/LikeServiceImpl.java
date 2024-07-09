package com.pafassigment.fitplus.service;

import com.pafassigment.fitplus.dao.LikeDao;
import com.pafassigment.fitplus.entity.Like;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeDao likeDao;

    public LikeServiceImpl(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    @Override
    @Transactional
    public void addLike(Like like) {
        if (like == null) {
            throw new IllegalArgumentException("Cannot add a null like");
        }
        likeDao.save(like);
    }

    @Override
    @Transactional
    public void removeLikeById(String likeId) {
        if (likeId == null || likeId.isEmpty()) {
            throw new IllegalArgumentException("Like ID must not be null or empty");
        }
        likeDao.deleteById(likeId);
    }
}
