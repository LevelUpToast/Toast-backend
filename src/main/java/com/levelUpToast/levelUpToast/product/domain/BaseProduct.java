package com.levelUpToast.levelUpToast.product.domain;

import com.levelUpToast.levelUpToast.product.domain.data.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.product.domain.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.product.domain.data.reviwe.Review;
import com.levelUpToast.levelUpToast.product.domain.data.tag.Tag;
import lombok.Data;


import java.util.ArrayList;

@Data
public abstract class BaseProduct {

    Long productSeq; // 제품 관리 번호

    protected String title; // 제품 제목

    protected Tag tag; // 제품 태그 정보

    protected int like; // 좋아요

    protected FundingInfo funding; // 펀딩 정보 ( 현재 펀딩 금액, 최종 펀딩 금액, 마감 기일 )

    protected Long vendorSeq; // 판매자관리 번호

    protected ArrayList<BuyOption> buyOption; // 제품 구매 옵션 ( 옵션 정보 , 옵션 가격 )

    protected Review review; // 제품 리뷰 별점
}
