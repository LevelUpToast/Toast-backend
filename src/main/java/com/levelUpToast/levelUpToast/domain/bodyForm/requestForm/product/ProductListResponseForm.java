package com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product;

import com.levelUpToast.levelUpToast.domain.data.product.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ProductListResponseForm {
    public ProductListResponseForm(Long productSeq, String title, List<String> initialImgUrl, Tag tag, FundingInfo funding) {
        this.productSeq = productSeq;
        this.title = title;
        this.initialImgUrl = initialImgUrl;
        this.tag = tag;
        this.funding = funding;

    }
        private Long productSeq; // 제품 관리 번호

        private List<String> initialImgUrl; // 제품 초기 이미지 URL

        private String title; // 제품 제목

        private Tag tag; // 제품 태그 정보

        private FundingInfo funding; // 펀딩 정보 ( 현재 펀딩 금액, 최종 펀딩 금액, 마감 기일 )

}
