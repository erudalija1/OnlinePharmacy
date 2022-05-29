package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Opinion;
import ba.unsa.etf.onlinepharmacy.Repository.OpinionRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addOpinionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpinionService {
    @Autowired
    private OpinionRepository opinionRepository;

    public Iterable<Opinion> getAllOpinions() {
        return opinionRepository.findAll();
    }

    public Optional<Opinion> getOpinionById(int id){
        return opinionRepository.findById(id);
    }

    public void addOpinion(addOpinionRequest addOpinion) {

        Opinion opinion=new Opinion();
        opinion.setEmail(addOpinion.getEmail());
        opinion.setName(addOpinion.getName());
        opinion.setSubject(addOpinion.getSubject());
        opinion.setComment(addOpinion.getComment());
        opinionRepository.save(opinion);
    }

    public void deleteOpinion(int id) {
        Optional<Opinion> opinion = opinionRepository.findById(id);
        if (opinion.isPresent())
            opinionRepository.delete(opinion.get());
    }
}
