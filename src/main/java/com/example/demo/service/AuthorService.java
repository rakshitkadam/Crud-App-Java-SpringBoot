package com.example.demo.service;

import com.example.demo.domain.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    List<AuthorEntity> findAll();

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
