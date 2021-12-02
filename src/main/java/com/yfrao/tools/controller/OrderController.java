package com.yfrao.tools.controller;


import com.yfrao.tools.service.OrderService;
import com.yfrao.tools.util.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @CrossOrigin
    @PostMapping("api/getOrderInfo")
    public OrderResult getOrderInfo(@RequestParam(value = "orderNo") String orderNo) throws Exception {
        return orderService.getOrderInfo(orderNo);
    }
}
