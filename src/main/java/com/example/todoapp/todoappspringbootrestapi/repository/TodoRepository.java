package com.example.todoapp.todoappspringbootrestapi.repository;


import com.example.todoapp.todoappspringbootrestapi.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
