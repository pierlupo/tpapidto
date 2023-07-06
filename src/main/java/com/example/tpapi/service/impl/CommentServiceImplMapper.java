package com.example.tpapi.service.impl;

import com.example.tpapi.dto.CommentDto;
import com.example.tpapi.entity.Comment;
import com.example.tpapi.entity.Post;
import com.example.tpapi.exception.BlogApiException;
import com.example.tpapi.exception.ResourceNotFoundException;
import com.example.tpapi.repository.CommentRepo;
import com.example.tpapi.repository.PostRepo;
import com.example.tpapi.service.CommentServiceMapper;
import com.example.tpapi.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImplMapper implements CommentServiceMapper {

    @Autowired
    private Mapper mapper;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;


    @Override
    public CommentDto createComment(int postId, CommentDto commentDto) {
        Comment comment = mapper.mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = retrievePostEntityById(postId);

        //set post to comment entity
        comment.setPost(post);

        //save comment entity to db
        Comment newComment =  commentRepo.save(comment);

        return mapper.mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(int postId) {

        //retrieve comments by postId
        List<Comment> comments = commentRepo.findByPostId(postId);

        //convert list of comment entities to list of comment
        return comments.stream().map(comment -> mapper.mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(int postId, int commentId) {
        //comment by id
        Comment comment = commentById(postId, commentId);

        return mapper.mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(int postId, int commentId, CommentDto commentRequest) {
        //retrieve post entity by id
        Post post = retrievePostEntityById(postId);

        //retrieve comment by id
        Comment comment = retrieveCommentById(commentId);

        //badRequestException IF ELSE
        badRequestException(comment, post);

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepo.save(comment);

        return mapper.mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(int postId, int commentId) {
        //comment by id
        Comment comment = commentById(postId, commentId);

        commentRepo.delete(comment);
    }

    //comment by id
    private Comment commentById(int postId, int commentId) {

        //retrieve post entity by id
        Post post = retrievePostEntityById(postId);

        //retrieve comment by id
        Comment comment = retrieveCommentById(commentId);

        //badRequestException IF ELSE
        badRequestException(comment, post);

        return comment;
    }


    //badRequestException IF ELSE
    private void badRequestException(Comment comment, Post post) {

        if (((comment.getPost().getId()) != (post.getId()))) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

    }

    //retrieve post entity by id
    private Post retrievePostEntityById(int postId) {

        return postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
    }

    //retrieve comment by id
    private Comment retrieveCommentById(int commentId) {

        return commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "commentId", commentId));
    }

}
