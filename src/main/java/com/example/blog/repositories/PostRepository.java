package com.example.blog.repositories;

import com.example.blog.domain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
}