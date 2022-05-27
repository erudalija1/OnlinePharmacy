package ba.unsa.etf.onlinepharmacy.Service;

import ba.unsa.etf.onlinepharmacy.Model.Medicament;
import ba.unsa.etf.onlinepharmacy.Model.MedicamentOrder;
import ba.unsa.etf.onlinepharmacy.Model.Patient;
import ba.unsa.etf.onlinepharmacy.Model.UserOrder;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentOrderRepository;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Repository.UserOrderRepository;
import ba.unsa.etf.onlinepharmacy.Requests.MakeDecisionRequest;
import ba.unsa.etf.onlinepharmacy.Requests.addToBasketRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MedicamentOrderService {
    @Autowired
    private MedicamentOrderRepository medicamentOrderRepository;

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Autowired
    private MedicamentRepository medicamentRepository;


    @Autowired
    private OrderService orderService;

    public void goShopping(int idOrder, addToBasketRequest addToBasket) throws Exception {
        for (Medicament m:addToBasket.getMedicamentOrderList()) {
        UserOrder userOrder=userOrderRepository.findById(idOrder).orElse(null);
        MedicamentOrder medicamentOrder=new MedicamentOrder(m,userOrder);
        if(userOrder==null){
            throw new Exception();
        }
                medicamentOrderRepository.save(medicamentOrder);
        }

    }

    public void makeDecision(MakeDecisionRequest makeDecisionRequest){
        int id=makeDecisionRequest.getId();
        List<MedicamentOrder> medicamentOrderList=medicamentOrderRepository.findByUserOrder_Id(makeDecisionRequest.getId()).orElse(null);
        boolean kontrola=false;
        HashMap<Integer,Integer> mapa=new HashMap<>();
        for (MedicamentOrder mo:medicamentOrderList) {
            if(!mapa.containsKey(mo.getMedicament().getId())){
                Integer broj=mo.getMedicament().getId();
                mapa.put(broj,1);
            }
            else{
                Integer broj=mo.getMedicament().getId();
                mapa.put(broj,mapa.get(broj)+1);
            }

        }

       for(MedicamentOrder mo:medicamentOrderList){
           int id2=mo.getMedicament().getId();
           int kolicina=medicamentRepository.getById(id2).getInStock();
           System.out.println("evo "+mapa.get(mo.getMedicament().getInStock()));
           if(mo.getMedicament().getInStock()>=mapa.get(mo.getMedicament().getId())){
              // medicamentRepository.getById(id2).setInStock(kolicina-mapa.get(mo.getMedicament().getId()));
               kontrola=true;
           }
           else{
               kontrola=false;
           }
       }
       if(kontrola==true){
           for(MedicamentOrder mo:medicamentOrderList){
               int id2=mo.getMedicament().getId();
               int kolicina=medicamentRepository.getById(id2).getInStock();
               System.out.println("evo "+mapa.get(mo.getMedicament().getInStock()));
                   medicamentRepository.getById(id2).setInStock(kolicina-mapa.get(mo.getMedicament().getId()));
           }
           System.out.println("usao");
            userOrderRepository.getById(id).setStatus(1);
            boolean a=orderService.approve(makeDecisionRequest.getId(),1);
            Patient patient=userOrderRepository.getById(makeDecisionRequest.getId()).getPatient();
            String email=patient.getEmail();
            orderService.sendOrderEmail(email,"prihvacena","narudzba");
            if(a==true){
                System.out.println("update");
            }
       }
       else{
           orderService.approve(makeDecisionRequest.getId(),0);
           Patient patient=userOrderRepository.getById(makeDecisionRequest.getId()).getPatient();
           String email=patient.getEmail();
           orderService.sendOrderEmail(email,"odbijena","narudzba");
       }
    }
}
