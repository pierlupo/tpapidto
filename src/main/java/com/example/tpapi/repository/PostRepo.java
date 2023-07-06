package com.example.tpapi.repository;

import com.example.tpapi.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostRepo extends CrudRepository<Post, Integer> {
    Post findPostByTitle(String title);
}
