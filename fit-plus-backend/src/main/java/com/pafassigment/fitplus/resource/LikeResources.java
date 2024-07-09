package com.pafassigment.fitplus.resource;

import com.pafassigment.fitplus.entity.Like;
import com.pafassigment.fitplus.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class LikeResources {
    private final LikeService likeService;

    @Autowired
    public LikeResources(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<Map<String, String>> addLikeToPost(@PathVariable String postId, @RequestBody Like like) {
        like.setPostId(postId);
        likeService.addLike(like);
        Map<String, String> response = new HashMap<>();
        response.put("response", "success");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("likes/{likeId}")
    public ResponseEntity<Map<String, String>> removeLikeFromLikeId(@PathVariable String likeId) {
        likeService.removeLikeById(likeId);
        Map<String, String> response = new HashMap<>();
        response.put("response", "success");
        return ResponseEntity.ok(response);
    }
}
