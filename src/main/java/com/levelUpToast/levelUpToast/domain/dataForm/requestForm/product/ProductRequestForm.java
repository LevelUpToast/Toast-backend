package com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product;

import com.levelUpToast.levelUpToast.domain.model.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequestForm {

    private String vendorToken; // 판매자 토큰

    private List<String> initialImgUrl; // 제품 초기 이미지 URL

    private String title; // 제품 제목

    private Tag tag; // 제품 태그 정보

    private int finalAmount; // 최종 펀딩 금액

    private String deadline; // 마감 기일

    private String text; // 제품 내용

    private List<String> productImgUrl; // 제품 이미지 URL 리스트

    private List<BuyOption> buyOption; // 제품 구매 옵션 ( 옵션 정보 , 옵션 가격 )


}
