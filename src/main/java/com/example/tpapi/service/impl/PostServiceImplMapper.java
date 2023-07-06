package com.example.tpapi.service.impl;


import com.example.tpapi.dto.PostDto;
import com.example.tpapi.entity.Post;
import com.example.tpapi.exception.ResourceNotFoundException;
import com.example.tpapi.repository.PostRepo;
import com.example.tpapi.service.PostServiceMapper;
import com.example.tpapi.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplMapper implements PostServiceMapper {

    @Autowired
    private Mapper mapper;

    @Autowired
    private PostRepo postRepo;


    @Override
    public PostDto createPost(PostDto postDto) {
        //convert dto to entity
        Post post = mapper.mapToEntity(postDto);

        Post newPost = postRepo.save(post);

        //convert entity to dto
        PostDto postResponse = mapper.mapToDto(newPost);

        return postResponse;
        }

        @Override
        public List<PostDto> getAllPosts () {

            List<Post> posts = (List<Post>) postRepo.findAll();

            List<PostDto> postDtoList = posts.stream().map(post -> mapper.mapToDto(post)).collect(Collectors.toList());


            return postDtoList;
        }

        @Override
        public PostDto getPostById ( int id){
            //get post by id from the database
            Post post = getPostByIdFromDatabase(id);

            return mapper.mapToDto(post);
        }

        @Override
        public PostDto updatePost (PostDto postDto,int id){
            //get post by id from the database
            Post post = getPostByIdFromDatabase(id);

            //update post
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setContent(postDto.getContent());

            //return and save updated post in the database
            Post updatePost = postRepo.save(post);
            return mapper.mapToDto(updatePost);
        }

        @Override
        public void deletePostById ( int id){
            //get post by id from the database
            Post post = getPostByIdFromDatabase(id);

            //delete post from database
            postRepo.deleteById(id);
        }

        //get post by id from the database
        private Post getPostByIdFromDatabase(int id) {

            return postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        }

    }
