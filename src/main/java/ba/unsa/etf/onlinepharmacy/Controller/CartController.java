package ba.unsa.etf.onlinepharmacy.Controller;


import ba.unsa.etf.onlinepharmacy.Model.Cart;
import ba.unsa.etf.onlinepharmacy.Repository.CartRepository;
import ba.unsa.etf.onlinepharmacy.Repository.MedicamentRepository;
import ba.unsa.etf.onlinepharmacy.Requests.addMedicamentCartRequest;
import ba.unsa.etf.onlinepharmacy.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    private MedicamentRepository medicamentRepository;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping(path="/carts")
    public Iterable<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    @PostMapping(path="/addToCart/{id}")
    public String addToCart(@PathVariable int id,@RequestBody addMedicamentCartRequest addMedicamentCart){
        return cartService.addMedicamentCart(id,addMedicamentCart);
    }

    @PostMapping(path="/submitCart/{id}")
    public String submitCart(@PathVariable int id) throws Exception {
        return cartService.submitCart(id);
    }

    @PutMapping(path="/deleteItemFromCart/{idCart}/{idItem}")
    public String deleteItemFromCart(@PathVariable int idCart,@PathVariable int idItem){
        return cartService.deleteItemFromCart(idCart,idItem);
    }

    @DeleteMapping(path="/removeCart/{id}")
    public String deleteWholeCart(@PathVariable int id){
        return cartService.removeWholeCart(id);
    }

    @PostMapping(path="/makeDecisionForCart/{id}")
    public String makeDecisionForCart(@PathVariable int id){
        return cartService.makeDecisionForSubmitedCart(id);
    }
}
