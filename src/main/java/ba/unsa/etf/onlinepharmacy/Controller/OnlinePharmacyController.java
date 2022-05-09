package ba.unsa.etf.onlinepharmacy.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnlinePharmacyController {

    @RequestMapping
    public String onlinePharmacy() {
        return "Welcome to our online Pharmacy!";
    }
}
