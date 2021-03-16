package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Order;
import by.nintendo.datajpa.model.OrderStatus;
import by.nintendo.datajpa.storage.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderServiceTest {
    @Autowired
    private final OrderRepository orderRepositoryMoc = Mockito.mock(OrderRepository.class);

    @BeforeEach
    void initBdOrder() {
        orderRepositoryMoc.deleteAll();
        orderRepositoryMoc.save(new Order(1, 2, 3, LocalDateTime.now(), OrderStatus.PLACED, true));
        orderRepositoryMoc.save(new Order(2, 23, 2, LocalDateTime.now(), OrderStatus.APPROVED, false));
        orderRepositoryMoc.save(new Order(3, 21, 3, LocalDateTime.now(), OrderStatus.PLACED, true));
    }
    @Test
    void getAllOrder() {
        assertNotNull(orderRepositoryMoc.findAll());
        orderRepositoryMoc.deleteAll();
        assertEquals(orderRepositoryMoc.findAll().size(),0);
    }
    @Test
    void createOrder() {
        Order order = orderRepositoryMoc.save(new Order(4, 2, 3, LocalDateTime.now(), OrderStatus.PLACED, true));
        List<Order> orders = orderRepositoryMoc.findAll();
        assertEquals(orderRepositoryMoc.getOne(4L), order);
        assertEquals(orders.size(), 4);
    }

    @Test
    void findOrderById() {
        Order order = orderRepositoryMoc.save(new Order(4, 2, 3, LocalDateTime.now(), OrderStatus.PLACED, true));
        assertEquals(orderRepositoryMoc.findAll().get(3),order);
        orderRepositoryMoc.delete(order);
        assertEquals(orderRepositoryMoc.findAll().size(),3);

    }

    @Test
    void deleateOrder() {
        assertNotNull(orderRepositoryMoc.findAll());
        orderRepositoryMoc.deleteAll();
        assertEquals(orderRepositoryMoc.findAll().size(),0);
    }

    @Test
    void getStatusList() {
    }
}