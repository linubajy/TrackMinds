package com.example.Campus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Campus.Entity.Mind;

@Repository
public interface MindRepo extends JpaRepository<Mind, Integer>{

	public  Mind findById(int id);
}
