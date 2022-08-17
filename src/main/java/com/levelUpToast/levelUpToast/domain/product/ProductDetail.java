package com.levelUpToast.levelUpToast.domain.product;

import com.levelUpToast.levelUpToast.domain.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.product.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.product.vendor.Vendor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ProductDetail {
    public ProductDetail(String title, ArrayList<String> imgList, Tag tag, FundingInfo funding, int like, Vendor vendor, ProductInfo productInfo, ArrayList<BuyOption> buyOption, Review review) {
        this.title = title;
        this.imgList =  imgList;
        this.tag = tag;
        this.funding = funding;
        this.like = like;
        this.vendor = vendor;
        this.productInfo = productInfo;
        this.buyOption = buyOption;
        this.review = review;
    }
    private Long productSeq; //제품 고유 번호
    private ArrayList<String> imgList; //이미지 정보 리스트
    private String title; //제품 제목
    private Tag tag; //제품 태그 정보
    private int like; //제품 like
    private FundingInfo funding; //펀딩 정보
    private Vendor vendor; //판매자 정보
    private ProductInfo productInfo; //제품 정보
    private ArrayList<BuyOption> buyOption; //제품 구매 옵션
    private Review review; // 제품 리뷰 별점

}
