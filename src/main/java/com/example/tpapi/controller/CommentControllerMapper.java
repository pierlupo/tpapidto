package com.example.tpapi.controller;

import com.example.tpapi.dto.CommentDto;
import com.example.tpapi.service.CommentServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentControllerMapper {

    @Autowired
    private CommentServiceMapper commentServiceImplMapper;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") int postId, @RequestBody CommentDto commentDto) {
        System.out.println(commentDto);

        return new ResponseEntity<>(commentServiceImplMapper.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") int postId) {

        return commentServiceImplMapper.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") int postId,
                                                     @PathVariable(value = "commentId") int commentId) {
        CommentDto commentDto = commentServiceImplMapper.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") int postId,
                                                    @PathVariable(value = "commentId") int commentId,
                                                    @RequestBody CommentDto commentDto) {
        CommentDto updatedCommentDto = commentServiceImplMapper.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") int postId, @PathVariable(value = "commentId") int commentId) {

        commentServiceImplMapper.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted succcessfully", HttpStatus.OK);
    }
}
