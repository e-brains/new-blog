package com.example.newblog.repository;

import com.example.newblog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Integer>{


}
