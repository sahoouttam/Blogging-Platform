package com.blog.platform.repository;

import com.blog.platform.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    @Query(value = "SELECT * FROM favourites f WHERE f.name= :name", nativeQuery = true)
    List<Favourite> findByName(String name);
}
