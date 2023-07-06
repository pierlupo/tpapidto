package com.example.tpapi.service;

import com.example.tpapi.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentServiceMapper {

        CommentDto createComment (int postId, CommentDto commentDto);

        List<CommentDto> getCommentsByPostId(int postId);

        CommentDto getCommentById(int postId , int commentId);

        CommentDto updateComment(int postId , int commentId , CommentDto commentRequest);

        void deleteComment( int postId, int commentId);

}
