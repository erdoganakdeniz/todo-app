package com.techer.todoapp.repository;

import com.techer.todoapp.entity.ToDoItem;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToDoRepository extends CouchbaseRepository<ToDoItem, String> {
    List<ToDoItem> findByUserId(String userId);
}
