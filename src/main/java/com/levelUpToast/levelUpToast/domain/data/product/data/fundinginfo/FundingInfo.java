package com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundingInfo {
    private int currentAmount;
    private int finalAmount;
    // deadline 은 date 형식으로 YYYY-MM-DD 형식 유지
    private String deadline;
}
