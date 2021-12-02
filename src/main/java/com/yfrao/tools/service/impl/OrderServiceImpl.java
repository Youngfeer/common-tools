package com.yfrao.tools.service.impl;

import com.yfrao.tools.response.OrderResponse;
import com.yfrao.tools.service.OrderService;
import com.yfrao.tools.util.OrderResult;
import com.yfrao.tools.util.ShardingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    @Qualifier("orderJdbcTemplate")
    private JdbcTemplate orderJdbcTemplate;

    @Autowired
    @Qualifier("mesfJdbcTemplate")
    private JdbcTemplate mesfJdbcTemplate;

    @Override
    public OrderResult getOrderInfo(String orderNo) throws Exception {
        long item_slot =  ShardingUtil.indexSlotWithHash(orderNo);
        String table = "business_order_item_" + item_slot;
        String sql = "select sale_to_user_code from " + table + " where order_no = " + "\"" + orderNo + "\"";
        String dealerCode = orderJdbcTemplate.queryForList(sql, String.class).get(0);
        long item = ShardingUtil.indexSlot(dealerCode);
        String sql1 = "select activity_id from " + table + " where order_no = " + "\"" + orderNo + "\"";
        List actIds = orderJdbcTemplate.queryForList(sql1, String.class);
        Set activityIds = new HashSet();
        for(int i = 0; i < actIds.size(); i++){
            if(!(actIds.get(i) == null)){
                activityIds.add(actIds.get(i));
            }
        }
        String sql2 = "select org_office_name from sf_verify_basic_info where verify_cust_code = " +  "\"" + dealerCode + "\"";
        String orgName = mesfJdbcTemplate.queryForList(sql2, String.class).get(0);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setActivityIds(activityIds);
        orderResponse.setDealerCode(dealerCode);
        orderResponse.setItemNo(String.valueOf(item_slot));
        orderResponse.setMainNo(String.valueOf(item));
        orderResponse.setOrgName(orgName);
        OrderResult orderResult = new OrderResult();
        orderResult.setData(orderResponse);
        orderResult.setSuccess(true);
        return orderResult;
    }
}
