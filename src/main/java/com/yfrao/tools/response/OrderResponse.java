package com.yfrao.tools.response;

import java.util.List;
import java.util.Set;

public class OrderResponse {
    private String itemNo;
    private String mainNo;
    private String dealerCode;
    private Set activityIds;
    private String orgName;

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getMainNo() {
        return mainNo;
    }

    public void setMainNo(String mainNo) {
        this.mainNo = mainNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Set getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(Set activityIds) {
        this.activityIds = activityIds;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "itemNo='" + itemNo + '\'' +
                ", mainNo='" + mainNo + '\'' +
                ", dealerCode='" + dealerCode + '\'' +
                ", activityIds=" + activityIds +
                ", orgName='" + orgName + '\'' +
                '}';
    }
}
