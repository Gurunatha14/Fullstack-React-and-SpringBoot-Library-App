package com.library.spring_boot_library.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.spring_boot_library.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByBookId(@RequestParam("book_id") Long bookId, Pageable pageable);

    Review findByUserEmailAndBookId(String userEmail, Long bookId);

    @Modifying

    @Query("DELETE FROM Review r WHERE r.bookId = :bookId")
    void deleteAllByBookId(@Param("bookId") Long bookId);

}
