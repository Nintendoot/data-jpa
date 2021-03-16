package by.nintendo.datajpa.service;


import by.nintendo.datajpa.exception.NoOrdersException;
import by.nintendo.datajpa.exception.NoSuchOrderException;
import by.nintendo.datajpa.exception.PetNotFoundException;
import by.nintendo.datajpa.model.Order;
import by.nintendo.datajpa.model.OrderStatus;
import by.nintendo.datajpa.model.Pet;
import by.nintendo.datajpa.model.PetStatus;
import by.nintendo.datajpa.storage.OrderRepository;
import by.nintendo.datajpa.storage.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PetRepository petRepository;

    @Autowired
    private PetService petService;

    public OrderService(OrderRepository orderRepository, PetRepository petRepository) {
        this.orderRepository = orderRepository;

        this.petRepository = petRepository;
    }

    public void createOrder(Order order) {
        if (petRepository.existsById(order.getPetId())) {
            Pet pet = petRepository.getOne(order.getPetId());
            if (petService.checkStatusForOrder(pet)) {
                order.setStatus(OrderStatus.APPROVED);
                order.setComplete(true);
            } else {
                order.setStatus(OrderStatus.PLACED);
                order.setComplete(false);
            }
            order.setShipDate(LocalDateTime.now());
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
