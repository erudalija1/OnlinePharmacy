package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.Opinion;
import ba.unsa.etf.onlinepharmacy.Requests.addOpinionRequest;
import ba.unsa.etf.onlinepharmacy.Service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class OpinionController {
    @Autowired
    OpinionService opinionService;

    @GetMapping(path = "/opinions")
    public Iterable<Opinion> getAllOpinions() {
        return opinionService.getAllOpinions();
    }

    @GetMapping(path = "/opinion/{id}")
    public Optional<Opinion> getOpinionById(@PathVariable int id) {
        return opinionService.getOpinionById(id);
    }

    @PostMapping(path = "/opinion")
    public void addOpinion(@RequestBody addOpinionRequest addOpinion) {
        opinionService.addOpinion(addOpinion);
    }

    @DeleteMapping(path = "/opinion/{id}")
    public void deleteOpinion(@PathVariable int id) {
        opinionService.deleteOpinion(id);
    }
}
