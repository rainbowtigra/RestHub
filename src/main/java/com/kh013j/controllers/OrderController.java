package com.kh013j.controllers;

import com.kh013j.model.domain.Dish;
import com.kh013j.model.domain.Order;
import com.kh013j.model.domain.OrderedDish;
import com.kh013j.model.domain.Status;
import com.kh013j.model.service.interfaces.DishService;
import com.kh013j.model.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

@Controller
@SessionAttributes("orderMap")
public class OrderController {

    @Autowired
    DishService dishService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/addToOrder/{id}", method = RequestMethod.GET)
    public RedirectView addToOrder(@PathVariable(value = "id") long id,
                                     @ModelAttribute("orderMap") Map<Dish, Integer> orderMap,
                                     HttpServletRequest request) {
        Dish dish = dishService.findById(id);
        if (orderMap.containsKey(dish)) {
            orderMap.put(dish, orderMap.get(dish) + 1);
        } else {
            orderMap.put(dish, 1);
        }
        return new RedirectView(request.getHeader("referer"));
    }

    @RequestMapping(value="/removeFromOrder/{id}",method = RequestMethod.GET)
    public RedirectView removeFromOrder(@PathVariable(value = "id") long id,
                                        @ModelAttribute("orderMap") Map<Dish, Integer> orderMap,
                                        HttpServletRequest request) {
        Dish dish = dishService.findById(id);

        orderMap.remove(dish);
        return new RedirectView(request.getHeader("referer"));
    }

    @RequestMapping(value = "/submitOrder")
    public RedirectView submitOrder(@ModelAttribute("orderMap") Map<Dish, Integer> orderMap){
        orderService.create(createOrderFromMap(orderMap));
        return new RedirectView("/welcome");
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(){
        return "Cart";
    }


    @ModelAttribute("orderMap")
    public Map<Dish, Integer> getVisitor () {
        return new HashMap<>();
    }

    private Order createOrderFromMap(Map<Dish, Integer> orderMap){
        Order order = new Order();
        order.setTime(new Timestamp(new Date().getTime()));
        order.setTableNumber(1);
        order.setOrderedFood(new ArrayList<>( ));
        for(Map.Entry<Dish, Integer> entry : orderMap.entrySet()) {
            OrderedDish orderedDish = new OrderedDish();
            orderedDish.setDish(entry.getKey());
            orderedDish.setQuantity(entry.getValue());
            Status status = new Status();
            status.setName("preparing");
            orderedDish.setStatus(status);
            order.getOrderedFood().add(new OrderedDish());
        }
        return order;
    }
}
