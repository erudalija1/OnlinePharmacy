package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.Review;
import ba.unsa.etf.onlinepharmacy.Requests.addReviewRequest;
import ba.unsa.etf.onlinepharmacy.Requests.addUserRequest;
import ba.unsa.etf.onlinepharmacy.Service.MedicamentService;
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
    public Iterable<Review> getAllMedicaments() {
        return reviewService.getAllReviews();
    }

    @GetMapping(path = "/review/{id}")
    public Optional<Review> getMedicamenttById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping(path = "/review")
    public void addMedicament(@RequestBody addReviewRequest addReview) {
        reviewService.addReview(addReview);
    }

    @DeleteMapping(path = "/review/{id}")
    public void deleteMedicament(@PathVariable int id) {
        reviewService.deleteReview(id);
    }
}
