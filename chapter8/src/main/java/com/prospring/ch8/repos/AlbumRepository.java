package com.prospring.ch8.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


import com.prospring.ch8.entities.Album;
import com.prospring.ch8.entities.Singer;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	 List<Album> findBySinger(Singer singer);
	 @Query("select a from Album a where a.title like %:title%")
	 List<Album> findByTitle(@Param("title") String t);
}
