package com.levelUpToast.levelUpToast.domain.product;

import com.levelUpToast.levelUpToast.domain.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.product.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;
import lombok.Data;
import java.util.List;

@Data
public class Product {
    public Product(String title, List<String> imgList, Tag tag, FundingInfo funding, int like, Long vendorSeq, ProductInfo productInfo, List<BuyOption> buyOption, Review review) {
        this.title = title;
        this.imgList =  imgList;
        this.tag = tag;
        this.funding = funding;
        this.like = like;
        this.vendorSeq = vendorSeq;
        this.productInfo = productInfo;
        this.buyOption = buyOption;
        this.review = review;
    }
    private Long productSeq; // 제품 관리 번호

    private List<String> imgList; // 제품 초기 이미지 URL

    private String title; // 제품 제목

    private Tag tag; // 제품 태그 정보

    private int like; // 좋아요

    private FundingInfo funding; // 펀딩 정보 ( 현재 펀딩 금액, 최종 펀딩 금액, 마감 기일 )

    private Long vendorSeq; // 판매자관리 번호

    private ProductInfo productInfo; // 제품 정보 ( 제품 내용, 이미지 URL 리스트 )

    private List<BuyOption> buyOption; // 제품 구매 옵션 ( 옵션 정보 , 옵션 가격 )

    private Review review; // 제품 리뷰 별점
}
