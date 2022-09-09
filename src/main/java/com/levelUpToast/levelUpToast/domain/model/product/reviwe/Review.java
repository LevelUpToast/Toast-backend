package com.levelUpToast.levelUpToast.domain.model.product.reviwe;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Review {
    int totalRating;
    private int star5;
    private int star4;
    private int star3;
    private int star2;
    private int star1;

    public Review(int star5, int star4, int star3, int star2, int star1) {
        this.star5 = star5;
        this.star4 = star4;
        this.star3 = star3;
        this.star2 = star2;
        this.star1 = star1;
        totalRating = (getStar1() + getStar2() + getStar3() + getStar4() + getStar5()) / 5;
    }
}
