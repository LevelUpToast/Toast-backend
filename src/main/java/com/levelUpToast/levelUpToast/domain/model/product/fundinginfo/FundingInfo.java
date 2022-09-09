package com.levelUpToast.levelUpToast.domain.model.product.fundinginfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FundingInfo {
    private int currentAmount;
    private int finalAmount;
    private String deadline;
}
