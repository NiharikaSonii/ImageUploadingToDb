package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pojo.Images;

public interface ImageRepo extends JpaRepository<Images, Integer>{

	
}
