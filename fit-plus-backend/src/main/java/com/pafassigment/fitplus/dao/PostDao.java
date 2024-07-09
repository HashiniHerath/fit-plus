package com.pafassigment.fitplus.dao;


import com.pafassigment.fitplus.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends MongoRepository<Post, String> {
}
