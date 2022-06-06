package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Exception.NotFoundException;
import ba.unsa.etf.onlinepharmacy.Model.MedicamentOrder;
import ba.unsa.etf.onlinepharmacy.Model.OrderPayment;
import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Model.UserOrder;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentOrderRepository;
import ba.unsa.etf.onlinepharmacy.Repository.OrderPaymentRepository;
import ba.unsa.etf.onlinepharmacy.Repository.PatientRepository;
import ba.unsa.etf.onlinepharmacy.Repository.UserOrderRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private MedicamentOrderRepository medicamentOrderRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    OrderPaymentRepository orderPaymentRepository;

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

   /* public void sendOrderEmail(String toEmail,String body,String subject){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("projekatppis@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender .send(message);
    }
*/
    public void deleteOrder(Integer id) {
        if (userOrderRepository.findById(id).isPresent()){
            Optional<UserOrder> userOrder = userOrderRepository.findById(id);
            if (userOrder.get().getStatus() == 2){
                userOrderRepository.deleteById(id);
            }
        }
        //else return;
        else throw new NotFoundException("Ne postoji order sa poslanim id!");
    }
    public String submitOrderPayment(int id,int idOrder){
        UserOrder userOrder=userOrderRepository.getById(idOrder);
        Patient patient=patientRepository.findById(id).orElse(null);
        Double price=0.0;
        List<MedicamentOrder> medicamentOrder=medicamentOrderRepository.findAll();
        for(MedicamentOrder m:medicamentOrder){
            if(m.getUserOrder().getId()==userOrder.getId()){
                price+=m.getMedicament().getPrice();
            }
        }
        if(patient.isDiscount()){
            price-=0.2*price;
        }
        OrderPayment orderPayment=new OrderPayment();
        orderPayment.setUserOrder(userOrder);
        orderPayment.setPatient(patient);
        orderPayment.setPrice(price);
        orderPaymentRepository.save(orderPayment);
        return "payment done";
    }

    public List<UserOrder> getAllOrders() {
        return userOrderRepository.findAll();
    }

    public List<UserOrder> getOrdersWithStus(int status) {
        List<UserOrder> allUserOrders= userOrderRepository.findAll();
        List<UserOrder> userOrdersWithStatus = new ArrayList<>();
        for (var userOrder: allUserOrders) {
            if (userOrder.getStatus() == status)
                userOrdersWithStatus.add(userOrder);
        }
        return userOrdersWithStatus;
    }
}
