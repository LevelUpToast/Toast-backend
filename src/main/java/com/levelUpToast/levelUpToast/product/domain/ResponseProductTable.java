package com.levelUpToast.levelUpToast.product.domain;

import com.levelUpToast.levelUpToast.product.domain.data.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.product.domain.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.product.domain.data.tag.Tag;
import com.levelUpToast.levelUpToast.product.domain.data.reviwe.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Product 데이터를 데이터베이스에서 꺼내고 클라이언트에게 보낼때 사용하는 형식
 */

@Getter
@Setter
public class ResponseProductTable extends BaseProduct {
    public ResponseProductTable(String title, List<String> initialImgUrl, Tag tag, FundingInfo funding, int like, Long vendorSeq, ResponseProductInfo productInfoResponse, ArrayList<BuyOption> buyOption, Review review) {
        this.title = title;
        this.initialImgUrl = initialImgUrl;
        this.tag = tag;
        this.funding = funding;
        this.like = like;
        this.vendorSeq = vendorSeq;
        this.productInfo = productInfoResponse;
        this.buyOption = buyOption;
        this.review = review;
    }
    List<String> initialImgUrl; // 제품 초기 이미지 URL

    ResponseProductInfo productInfo; // 제품 정보 ( 제품 내용, 이미지 URL 리스트 )

}
