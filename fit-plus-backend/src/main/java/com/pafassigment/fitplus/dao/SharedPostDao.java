package com.pafassigment.fitplus.dao;


import com.pafassigment.fitplus.entity.SharedPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedPostDao extends MongoRepository<SharedPost, String> {
}
