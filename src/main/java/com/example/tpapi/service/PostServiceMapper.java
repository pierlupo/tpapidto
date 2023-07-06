package com.example.tpapi.service;

import com.example.tpapi.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PostServiceMapper {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById( int id);

    PostDto updatePost(PostDto postDto, int id);

    void deletePostById(int id);

}
