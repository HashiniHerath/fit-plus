package com.pafassigment.fitplus.service;


import com.pafassigment.fitplus.entity.Like;

public interface LikeService {
    void addLike(Like like);

    void removeLikeById(String likeId);
}
