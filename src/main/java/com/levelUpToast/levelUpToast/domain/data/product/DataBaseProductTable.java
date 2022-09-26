package com.levelUpToast.levelUpToast.domain.data.product;

import com.levelUpToast.levelUpToast.domain.data.product.data.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.DataBaseProductInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Product 데이터를 데이터베이스에 저장할때 사용하는 데이터 형식
 */
@Setter
@Getter
public class DataBaseProductTable extends BaseProduct {

    public DataBaseProductTable(String title, List<Long> initialImgUrl, Tag tag, FundingInfo funding, int like, Long vendorSeq, DataBaseProductInfo productInfo, ArrayList<BuyOption> buyOption, Review review) {
        this.title = title;
        this.initialImgUrl = initialImgUrl;
        this.tag = tag;
        this.funding = funding;
        this.like = like;
        this.vendorSeq = vendorSeq;
        this.productInfo = productInfo;
        this.buyOption = buyOption;
        this.review = review;
    }

    private List<Long> initialImgUrl; // 제품 초기 이미지 URL

    private DataBaseProductInfo productInfo; // 제품 정보 ( 제품 내용, 이미지 URL 리스트 )


}
