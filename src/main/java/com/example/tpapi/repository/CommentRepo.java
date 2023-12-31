package com.example.tpapi.repository;

import com.example.tpapi.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer> {
   List<Comment> findByPostId(int postId);
}