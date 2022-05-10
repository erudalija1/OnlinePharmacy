package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Review;
import ba.unsa.etf.onlinepharmacy.Repository.ReviewRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Iterable<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(int id){
        return reviewRepository.findById(id);
    }

    public void addReview(addReviewRequest addReview) {
        Review review = new Review();
        review.setComment(addReview.getComment());
        review.setStars(addReview.getStars());
        reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent())
            reviewRepository.delete(review.get());
    }
}
