package ba.unsa.etf.onlinepharmacy.Controller;


import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentPicturePath;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentRequest;
import ba.unsa.etf.onlinepharmacy.Security.CurrentUser;
import ba.unsa.etf.onlinepharmacy.Security.UserPrincipal;
import ba.unsa.etf.onlinepharmacy.Service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class MedicamentController {

    @Autowired
    MedicamentService medicamentService;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @GetMapping(path = "/medicaments")
    public Iterable<Medicament> getAllMedicaments() {
        return medicamentService.getAllMedicaments();
    }

    @GetMapping(path = "/medicament/{id}")
    public Optional<Medicament> getMedicamenttById(@PathVariable int id, @CurrentUser UserPrincipal userPrincipal) {
        return medicamentService.getMedicamentById(id);
    }

    // @Secured("ROLE_ADMIN")
    @PostMapping(path = "/medicament")
    public void addMedicament(@RequestBody addMedicamentRequest addMedicamentRequest) {
        medicamentService.addMedicament(addMedicamentRequest);
    }

    @PutMapping(path="/medicament/{id}/timesBought")
    public void timesBoughtMedicament(@PathVariable int id){
        Medicament medicament=medicamentRepository.findById(id).orElse(null);
        medicament.setTimesBought(0);
        medicamentRepository.save(medicament);
    }

    //  @Secured("ROLE_ADMIN")
    @DeleteMapping(path = "/medicament/{id}")
    public void deleteMedicament(@PathVariable int id) {
        medicamentService.deleteMedicament(id);
    }

    @PostMapping("/medicament/{id}/photo/save")
    public String savePhoto(@PathVariable int id, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Medicament medicament = medicamentRepository.getById(id);
        medicament.setPhoto(fileName);

        medicamentRepository.save(medicament);
        String uploadDir = "./medicament-photos/" + medicament.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(filePath.toFile().getAbsolutePath());
            medicament.setFullPathPhoto(filePath.toFile().getAbsolutePath());
            medicamentRepository.save(medicament);
            return filePath.toFile().getAbsolutePath();
        } catch (Exception e) {
            return "redirect:/medicaments";
        }

    }

/*    @GetMapping("medicaments/pictures")
    public void seePics() {
        List<Medicament> lista = medicamentRepository.findAll();
        for (Medicament m : lista) {
            System.out.println(m.getMedicamentImagePath());
        }
    }*/

    @GetMapping("medicaments/pictures/{id}")
    public String seePics(@PathVariable int id) {
        Medicament medicament = medicamentRepository.findById(id).orElse(null);
        return medicament.getMedicamentImagePath();

    }

    @GetMapping("/medicaments/popular")
    public List<Medicament> popularMedicaments(){
        return medicamentService.popularMedicaments();
    }

    @PostMapping("/medicament/{id}/medicamentPicturePath")
    public void addMedicamentPicturePath(@PathVariable int id, @RequestBody addMedicamentPicturePath picturePath){
        medicamentService.addMedicamentPicturePath(id,picturePath);
    }

    @GetMapping("/medicament/{id}/getMedicamentPicturePath")
    public String getMedicamentPicturePath(@PathVariable int id){
        Medicament medicament=medicamentRepository.getById(id);
        return medicament.getPhoto();
    }
    @RequestMapping(value = "/medicaments/{name}", method = RequestMethod.GET)
    @ResponseBody
    public List<Medicament> getMedicamentByName(@PathVariable("name") String name) {
        return medicamentService.getAllMedicamentsByName(name);
    }

}