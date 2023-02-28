package com.example.demo.Services.Repositery;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Services.Model.Services;

public interface ServicesRepo extends JpaRepository<Services,String >{
//	@Query("select u from Services u where u.category=:c")
//	public List<Services> findBycategory(@Param("c") String category);
	public List<Services> findByCategory(String category);
}
