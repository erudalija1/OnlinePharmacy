package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Review;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Repository.ReviewRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addReviewRequest;
import ba.unsa.etf.onlinepharmacy.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

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

    @PostMapping("/reviewForMedicament/{id}")
    public void addReviewForMedicament(@PathVariable int id,@RequestBody addReviewRequest addReviewRequest){
        reviewService.addReviewForMedicament(id,addReviewRequest);
    }

    @GetMapping("getReviewsForMedicament/{id}")
    public List<Review> getReviewsForMedicament(@PathVariable int id){
        return reviewService.getReviews(id);
    }

    @GetMapping("getCommentsForMedicament/{id}")
    public List<String> getCommentsForMedicament(@PathVariable int id){
        return reviewService.getComments(id);
    }

    @GetMapping("getStarsForMedicament/{id}")
    public List<Integer> getStarsForMedicament(@PathVariable int id){
        return reviewService.getStars(id);
    }

    @GetMapping("getUser/{id}")
    public String getUser(@PathVariable int id){
        return reviewService.getUser(id);
    }

}
