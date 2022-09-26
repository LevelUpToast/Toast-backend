package com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundingInfo {
    private int currentAmount;
    private int finalAmount;
    private String deadline;
}
