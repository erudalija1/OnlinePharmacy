package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Model.Review;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Repository.ReviewRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private PatientRepository patientRepository;

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
        Patient patient=patientRepository.findByUsername(addReview.getUsername()).orElse(null);
        review.setPatient(patient);
        reviewRepository.save(review);
    }

    public void deleteReview(int id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent())
            reviewRepository.delete(review.get());
    }

    public void addReviewForMedicament(int id,addReviewRequest addReview) {
        Medicament medicament=medicamentRepository.findById(id).orElse(null);
        Review review = new Review();
        review.setComment(addReview.getComment());
        review.setStars(addReview.getStars());
        review.setMedicament(medicament);
        Patient patient=patientRepository.findByUsername(addReview.getUsername()).orElse(null);
        review.setPatient(patient);
        reviewRepository.save(review);
    }

    public List<Review> getReviews(int id){
        Medicament medicament=medicamentRepository.findById(id).orElse(null);
        List<Review> lista=reviewRepository.findAll();
        List<Review> vracam=new ArrayList<>();
        for (Review r:lista) {
            if(r.getMedicament().getId()==id){
                vracam.add(r);
            }

        }
        return vracam;
    }

    public List<String> getComments(int id){
        Medicament medicament=medicamentRepository.findById(id).orElse(null);
        List<Review> lista=reviewRepository.findAll();
        List<String> vracam=new ArrayList<>();
        for (Review r:lista) {
            if(r.getMedicament().getId()==id){
                vracam.add(r.getComment());
            }

        }
        return vracam;
    }

    public List<Integer> getStars(int id){
        Medicament medicament=medicamentRepository.findById(id).orElse(null);
        List<Review> lista=reviewRepository.findAll();
        List<Integer> vracam=new ArrayList<>();
        for (Review r:lista) {
            if(r.getMedicament().getId()==id){
                vracam.add(r.getStars());
            }

        }
        return vracam;
    }

    public String getUser(int id){
        Review review=reviewRepository.findById(id).orElse(null);
        return review.getPatient().getUsername();
    }

    public List<Review> getAllReviewsByStars(Integer stars) {
        return reviewRepository.findByStars(stars);
    }
}
