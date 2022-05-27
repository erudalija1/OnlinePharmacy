package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Model.UserOrder;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentOrderRepository;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Repository.UserOrderRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private MedicamentOrderRepository medicamentOrderRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private PatientRepository patientRepository;

    public int goShopping(addToBasketRequest addToBasket) throws Exception {
        UserOrder userOrder=new UserOrder();
        Patient patient=patientRepository.findById(addToBasket.getPatientId()).orElse(null);
        if (patient==null){
            throw new Exception();
        }
        userOrder.setPatient(patient);
        userOrder.setStatus(2);
        return userOrderRepository.save(userOrder).getId();
    }

    public List<UserOrder> makeDecisionOrder(){
        List<UserOrder> userOrders=userOrderRepository.getAllByStatusEquals(2);
        return userOrders;
    }

    public boolean approve(Integer id,Integer status){
        UserOrder userOrder=userOrderRepository.getById(id);
        if(status==1) {
            userOrder.setStatus(1);
        }
        else
            userOrder.setStatus(0);
        userOrderRepository.save(userOrder);
        return true;
    }

}
