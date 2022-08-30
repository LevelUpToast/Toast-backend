package com.levelUpToast.levelUpToast.domain.product.fundinginfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
@AllArgsConstructor
public class FundingInfo {
    private int currentAmount;
    private int finalAmount;
    private Calendar deadline;
}
