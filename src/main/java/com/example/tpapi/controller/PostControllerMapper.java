package com.example.tpapi.controller;

import com.example.tpapi.dto.PostDto;
import com.example.tpapi.service.PostServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class PostControllerMapper {

    @Autowired
    private PostServiceMapper postServiceImplMapper;



    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postServiceImplMapper.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
    @GetMapping("/api/v1/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return new ResponseEntity(postServiceImplMapper.getAllPosts(), HttpStatus.OK);
    }

    // get post by id
    @GetMapping(value = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(postServiceImplMapper.getPostById(id));
    }

    // update post by id rest api
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") int id){

        PostDto postResponse = postServiceImplMapper.updatePost(postDto, id);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") int id){

        postServiceImplMapper.deletePostById(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}