package com.levelUpToast.levelUpToast.domain.data.product.data.buyoption;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyOption {
    private String optionInfo;
    private int optionPrice;

    public BuyOption(String optionInfo, int optionPrice) {
        this.optionInfo = optionInfo;
        this.optionPrice = optionPrice;
    }
}
