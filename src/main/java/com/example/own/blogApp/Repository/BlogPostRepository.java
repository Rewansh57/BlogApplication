package com.example.own.blogApp.Repository;

import com.example.own.blogApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<Post,Long>
{

}
