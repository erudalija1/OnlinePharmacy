package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.UserOrder;
import ba.unsa.etf.onlinepharmacy.Requests.MakeDecisionRequest;
import ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest;
import ba.unsa.etf.onlinepharmacy.Service.MedicamentOrderService;
import ba.unsa.etf.onlinepharmacy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class OrderController {


    @Autowired
    OrderService orderService;

    @Autowired
    MedicamentOrderService medicamentOrderService;


    @PostMapping(path="/order")
    public ResponseEntity<String> goShopping(@RequestBody addToBasketRequest addToBasket) throws Exception {
        int idOrder=orderService.goShopping(addToBasket);
        medicamentOrderService.goShopping(idOrder,addToBasket);
        return ResponseEntity.ok("done");
    }

    @GetMapping(path="/orderDecision")
    public ResponseEntity<List<UserOrder>> makeDecisionOrder(){
        List<UserOrder> us=orderService.makeDecisionOrder();
        return ResponseEntity.ok(us);

    }

    @PostMapping("/orderMakeDecision")
    public ResponseEntity<String> orderMakeDecision(@RequestBody MakeDecisionRequest makeDecisionRequest){
        medicamentOrderService.makeDecision(makeDecisionRequest);
        return ResponseEntity.ok("status updated");
    }

    @PostMapping("/orderMakeDecisionForce")
    public ResponseEntity<String> orderMakeDecisionForce(@RequestBody MakeDecisionRequest makeDecisionRequest){
        medicamentOrderService.makeDecisionForce(makeDecisionRequest);
        return ResponseEntity.ok("status updated");
    }


}
