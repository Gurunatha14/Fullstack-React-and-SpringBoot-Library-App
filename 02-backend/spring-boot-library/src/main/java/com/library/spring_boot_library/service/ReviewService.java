package com.library.spring_boot_library.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.spring_boot_library.dao.ReviewRepository;
import com.library.spring_boot_library.entity.Review;
import com.library.spring_boot_library.requestmodels.ReviewRequest;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());
        if (validateReview != null) {
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(reviewRequest.getRating());
        review.setUserEmail(userEmail);
        if (reviewRequest.getReviewDescription().isPresent()) {
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString).orElse(null));
        }

        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long bookId) {
        Review validatReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);
        if (validatReview != null) {
            return true;
        } else {
            return false;
        }
    }

}
