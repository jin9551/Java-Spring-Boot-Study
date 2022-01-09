package com.example.study2.demo.repository;

import com.example.study2.demo.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
}
