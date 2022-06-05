package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Controller.OrderController;
import ba.unsa.etf.onlinepharmacy.Model.*;
import ba.unsa.etf.onlinepharmacy.Repository.*;
import ba.unsa.etf.onlinepharmacy.Requests.MakeDecisionRequest;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentCartRequest;
import ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    MedicamentRepository medicamentRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MedicamentCartRepository medicamentCartRepository;

    ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest addToBasketRequest;

    @Autowired
    OrderController orderController;

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    OrderService orderService;



    public String addMedicamentCart(int id, addMedicamentCartRequest addMedicamentCartRequest){
        Cart cart=new Cart(LocalDate.now(),0.0);
        Patient patient=patientRepository.findById(id).orElse(null);
        Cart cartp=cartRepository.findByDateOfShopping(LocalDate.now());
        MedicamentCart medicamentCart=new MedicamentCart();
        Medicament medicament=medicamentRepository.findById(addMedicamentCartRequest.getMedicamentId()).orElse(null);
        medicamentCart.setMedicament(medicament);
        medicamentCart.setDateOfShopping(LocalDate.now());
        if(cartp==null) {
            cart.setDateOfShopping(LocalDate.now());
            cart.setInitialPrice(medicament.getPrice());
            cartRepository.save(cart);
            medicamentCart.setCart(cart);
        }
        else {
            medicamentCart.setCart(cartp);
            cartp.setInitialPrice(cartp.getInitialPrice()+medicament.getPrice());
            cartRepository.save(cartp);
        }
        medicamentCart.setPatient(patient);
        medicamentCartRepository.save(medicamentCart);
        return "added to db";
    }

    public String submitCart(int id) throws Exception {
        List<MedicamentCart> ListMedicamentCart=medicamentCartRepository.findByPatient_Id(id);
        List<Integer> addToBasket=new ArrayList<>();
        for (MedicamentCart m: ListMedicamentCart) {
            addToBasket.add(m.getMedicament().getId());
        }
        ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest addToBasketRequest=new addToBasketRequest(id,2,addToBasket);
        orderController.goShopping(addToBasketRequest);
        UserOrder userOrder=userOrderRepository.getAllByPatient_Id(id);
        MakeDecisionRequest makeDecisionRequest=new MakeDecisionRequest(userOrder.getId());
        orderController.orderMakeDecision(makeDecisionRequest);
        if(userOrder.getStatus()==1){
        orderService.submitOrderPayment(id,userOrder.getId());
        }
        return "added";
    }


}