package by.nintendo.datajpa.resource;

import by.nintendo.datajpa.model.Order;
import by.nintendo.datajpa.model.PetStatus;
import by.nintendo.datajpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/store")
public class StoreResource {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/order")
    public ResponseEntity<Object> createOrderPet(@RequestBody Order order) {
        orderService.createOrder(order);
        return new ResponseEntity<>("Order create", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> allOrders() {
        List<Order> orders = orderService.getAllOrder();
        if(orders.size()!=0){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No orders.", HttpStatus.OK);
        }

    }

    @GetMapping(path = "/order/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable("id") long id) {
        return new ResponseEntity<>(orderService.findOrderById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/order/{id}")
    public ResponseEntity<Object> deleateOrderById(@PathVariable("id") long id) {
        orderService.deleateOrder(id);
        return new ResponseEntity<>("Order deleate.", HttpStatus.OK);
    }

    @GetMapping(path = "/inventory")
    public ResponseEntity<Map<PetStatus, Long>> getStatusList() {
        Map<PetStatus, Long> petStatusLongMap = orderService.getStatusList();
        return new ResponseEntity<>(petStatusLongMap, HttpStatus.OK);
    }


}
