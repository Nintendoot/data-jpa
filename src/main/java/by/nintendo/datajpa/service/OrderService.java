package by.nintendo.datajpa.service;


import by.nintendo.datajpa.exception.NoOrdersException;
import by.nintendo.datajpa.exception.NoSuchOrderException;
import by.nintendo.datajpa.exception.PetNotFoundException;
import by.nintendo.datajpa.model.Order;
import by.nintendo.datajpa.model.PetStatus;
import by.nintendo.datajpa.storage.OrderRepository;
import by.nintendo.datajpa.storage.PetRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PetRepository petRepository;

    public OrderService(OrderRepository orderRepository, PetRepository petRepository) {
        this.orderRepository = orderRepository;

        this.petRepository = petRepository;
    }




    public void createOrder(Order order) {
        if (petRepository.existsById(order.getPetId())) {
            orderRepository.save(order);
        } else {
            throw new PetNotFoundException("Pet not found");
        }
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(long id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id);
        } else {
            throw new NoSuchOrderException("There is no such order.");
        }

    }

    public void deleateOrder(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new NoOrdersException("No order");
        }
    }

    public Map<PetStatus, Long> getStatusList() {
        Map<PetStatus, Long> statusList = new HashMap<>();
        for (PetStatus petStatus : PetStatus.values()) {
            long count = petRepository.findAll().stream().filter(x -> x.getStatus().equals(petStatus)).count();
            statusList.put(petStatus, count);
        }
        return statusList;
    }

}
