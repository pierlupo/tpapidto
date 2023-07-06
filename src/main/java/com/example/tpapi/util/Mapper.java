package com.example.tpapi.util;

import com.example.tpapi.dto.CommentDto;
import com.example.tpapi.dto.PostDto;
import com.example.tpapi.entity.Comment;
import com.example.tpapi.entity.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

//        public PostDto convertToPostDto(Post post, PostDto postDto) {
//            return new ModelMapper().map(post, postDto.getClass());
//        }
//
//        public Post convertToEntityPost(Post post,  PostDto postDto) {
//            return new ModelMapper().map(postDto, post.getClass());
//        }
//
//        public CommentDto convertToCommentDto(Comment comment, CommentDto commentDto) {
//        return new ModelMapper().map(comment, commentDto.getClass());
//        }
//
//        public Comment convertToEntityComment(Comment comment,  CommentDto commentDto) {
//        return new ModelMapper().map(commentDto, comment.getClass());
//        }

    //convert entity to dto
    public PostDto mapToDto(Post post) {

        ModelMapper mapper = new ModelMapper();
        //map with modelMapper lib.
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;

    }

    //convert dto to entity
    public Post mapToEntity(PostDto postDto) {
        ModelMapper mapper = new ModelMapper();
        //map with modelMapper lib.
        Post post = mapper.map(postDto, Post.class);

        return post;
    }

    public CommentDto mapToDto(Comment comment) {

        ModelMapper mapper = new ModelMapper();
        CommentDto commentDto = mapper.map(comment, CommentDto.class);

        return commentDto;

    }

    //convert dto to entity
    public Comment mapToEntity(CommentDto commentDto) {

        ModelMapper mapper = new ModelMapper();
        Comment comment = mapper.map(commentDto, Comment.class);

        return  comment;


    }
    }

