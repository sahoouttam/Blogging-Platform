package com.blog.platform.repository;

import com.blog.platform.entity.Pin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    Page<Pin> findByBoardId(Long boardId, Pageable pageable);
}
