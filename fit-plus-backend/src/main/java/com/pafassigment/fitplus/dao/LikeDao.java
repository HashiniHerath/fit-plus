package com.pafassigment.fitplus.dao;


import com.pafassigment.fitplus.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeDao extends MongoRepository<Like, String> {
    List<Like> findByPostId(String id);
}
