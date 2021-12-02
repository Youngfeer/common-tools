package com.yfrao.tools.service;

import com.yfrao.tools.util.OrderResult;
import org.springframework.stereotype.Service;


public interface OrderService {
     OrderResult getOrderInfo(String orderNo) throws Exception;
}
