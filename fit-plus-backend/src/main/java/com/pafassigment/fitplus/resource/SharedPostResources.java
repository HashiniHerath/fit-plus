package com.pafassigment.fitplus.resource;

import com.pafassigment.fitplus.entity.SharedPost;
import com.pafassigment.fitplus.service.SharedPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shared-posts")
public class SharedPostResources {

    private final SharedPostService sharedPostService;

    public SharedPostResources(SharedPostService sharedPostService) {
        this.sharedPostService = sharedPostService;
    }

    @GetMapping
    public ResponseEntity<List<SharedPost>> getAllSharedPosts() {
        return ResponseEntity.ok(this.sharedPostService.getAllSharedPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedPost> getSharedPostById(@PathVariable String id) {
        Optional<SharedPost> optionalSharedPost = this.sharedPostService.getSharedPostById(id);
        return optionalSharedPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SharedPost> createSharedPost(@RequestBody SharedPost sharedPost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.sharedPostService.createSharedPost(sharedPost));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SharedPost> updateSharedPost(@PathVariable String id, @RequestBody SharedPost sharedPost) {
        SharedPost updatedSharedPost = this.sharedPostService.updateSharedPost(id, sharedPost);
        if (updatedSharedPost != null) {
            return ResponseEntity.ok(updatedSharedPost);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteSharedPost(@PathVariable String id) {
        sharedPostService.deleteSharedPost(id);
        Map<String, String> response = new HashMap<>();
        response.put("response", "success");
        return ResponseEntity.ok(response);
    }
}
