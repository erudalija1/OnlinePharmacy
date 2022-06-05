package ba.unsa.etf.onlinepharmacy.Controller;

import ba.unsa.etf.onlinepharmacy.Model.OrderPayment;
import ba.unsa.etf.onlinepharmacy.Repository.OrderPaymentRepository;
import ba.unsa.etf.onlinepharmacy.Service.MedicamentOrderService;
import ba.unsa.etf.onlinepharmacy.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class OrderPaymentController {
    @Autowired
    OrderService orderService;

    @Autowired
    MedicamentOrderService medicamentOrderService;

    @Autowired
    OrderPaymentRepository orderPaymentRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderPayment>> getAllOrderPayments(){
        return (ResponseEntity<List<OrderPayment>>) orderPaymentRepository.findAll();
    }

    @PostMapping("/submitPayment/{id}/{idOrder}")
    public String submitPayment(@PathVariable int id,@PathVariable int idOrder){
        return orderService.submitOrderPayment(id,idOrder);
    }
}
