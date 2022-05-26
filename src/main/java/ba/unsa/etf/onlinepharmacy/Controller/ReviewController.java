package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Review;
import ba.unsa.etf.onlinepharmacy.Requests.addReviewRequest;
import ba.unsa.etf.onlinepharmacy.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping(path = "/reviews")
    public Iterable<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping(path = "/review/{id}")
    public Optional<Review> getReviewtById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping(path = "/review")
    public void addReview(@RequestBody addReviewRequest addReview) {
        reviewService.addReview(addReview);
    }

    @DeleteMapping(path = "/review/{id}")
    public void deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
    }
}
