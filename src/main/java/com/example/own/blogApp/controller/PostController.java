package com.example.own.blogApp.controller;

import com.example.own.blogApp.Repository.BlogPostRepository;
import com.example.own.blogApp.Repository.CommentRepository;

import com.example.own.blogApp.model.Comment;
import com.example.own.blogApp.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/blogposts")
public class PostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public Post createBlogPost(@RequestBody Post post) {
        return blogPostRepository.save(post);
    }

    @GetMapping
    public List<Post> getAll() {
        return blogPostRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Post> getBlogPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updateBlog(@PathVariable Long id, @RequestBody Post updatedPostData) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    if (updatedPostData.getTitle() != null) {
                        post.setTitle(updatedPostData.getTitle());
                    }

                    if (updatedPostData.getContent() != null) {
                        post.setContent(updatedPostData.getContent());
                    }


                    Post updatedPost = blogPostRepository.save(post);
                    return ResponseEntity.ok(updatedPost);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    blogPostRepository.delete(post);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    comment.setPost(post);
                    Comment savedComment = commentRepository.save(comment);
                    return ResponseEntity.ok(savedComment);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Post> likeBlogPost(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    post.setLikes(post.getLikes() + 1);
                    Post updatedPost = blogPostRepository.save(post);
                    return ResponseEntity.ok(updatedPost);
                }).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/comments")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comm) {
        return commentRepository.findById(id).map(
                comment ->
                {
                    if (comm.getContent() != null) {
                        comment.setContent(comm.getContent());

                    }
                    Comment updatedComment = commentRepository.save(comment);
                    return ResponseEntity.ok(updatedComment);

                }
        ).orElse(ResponseEntity.notFound().build());

    }
}
