package com.levelUpToast.levelUpToast.product.service;


import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductDeleteRequestForm;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.member.domain.Member;
import com.levelUpToast.levelUpToast.member.service.memberServiceInf.check.UserIntegrityVerification;
import com.levelUpToast.levelUpToast.product.domain.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.product.domain.data.reviwe.Review;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.product.service.productServiceInf.*;
import com.levelUpToast.levelUpToast.util.utilInf.ProblemCheck;
import com.levelUpToast.levelUpToast.util.utilInf.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.vendor.service.vendor.InfoVendor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {

    private final ProblemCheck simpleProductInspection;
    private final UserIntegrityVerification simpleUserIntegrityVerification;
    private final InfoVendor infoVendor;
    private final ProductRepository productRepository;
    private final Recommendation recommendation;

    /**
     * Product 데이터 저장하는 메소드
     *
     * @param form      요청받은 Body 내용을 받아 Product 형태로 만든다.
     * @return 제품을 등록하고 등록된 SEQ 번호를 리턴한다.
     */
    @Override
    public Long registerProduct(ProductRequestForm form) throws LevelUpToastEx, SQLException {
        Member member = simpleUserIntegrityVerification.isMember(form.getVendorToken());
        log.info(String.valueOf(form));
        log.info("[ProductService log] manage Seq : {}",member.getManageSeq());
        ResponseProductTable responseProductTable = new ResponseProductTable(
                form.getTitle(),
                form.getInitialImgUrl(),
                form.getTag(),
                // 내부 데이터를 통해 처리
                new FundingInfo(
                        0,
                        form.getFinalAmount(),
                        form.getDeadline()),
                0,
                member.getManageSeq(),
                new ResponseProductInfo(
                        form.getText(),
                        form.getProductImgUrl()),
                form.getBuyOption(),
                new Review(0, 0, 0, 0, 0)
        );
        productRepository.saveProduct(responseProductTable);
        Long productSEQ = responseProductTable.getProductSeq();
        if (productSEQ == null)
            throw new LevelUpToastEx("제품등록에 오류가 발생했습니다.", 142);
        log.info("[ProductService log] : 상품 등록이 완료 되었습니다.");
        return productSEQ;
    }

    @Override
    public void deleteProduct(ProductDeleteRequestForm form) throws LevelUpToastEx, SQLException {
        Member member = simpleUserIntegrityVerification.isMember(form.getVendorToken());
        Long productSeq = Long.parseLong(form.getProductSEQ());

        simpleProductInspection.isProductSEQ(simpleProductInspection.isProduct(productSeq).orElseThrow().getVendorSeq(), member.getManageSeq());
        productRepository.removeProductBySeq(productSeq);
        log.info("[ProductService log] : 제품 삭제 완료!\t삭제요청한 판매자 SEQ = {},\t 삭제요청된 제품Seq = {}", member.getManageSeq(), productSeq);
    }

    /**
     * 특정 Product 데이터를 요청하고 Return 한다.
     *
     * @param seq 요청할 Seq 번호
     * @return 요청한 Product 리스트를 MAP 형식으로 만들어 반환한다..
     */
    @Override
    public Map<String, Object> getProduct(Long seq) throws LevelUpToastEx, SQLException {
        Map<String, Object> data = new LinkedHashMap<>();
        Optional<ResponseProductTable> product = simpleProductInspection.isProduct(seq);
        data.put("product", product);
        data.put("recommendedProducts", recommendationProduct());
        data.put("vendorInfo", infoVendor.infoVendor(product.orElseThrow().getVendorSeq()));
        log.info("[ProductService log] 제품 정보 요청이 되었습니다. SEQ = {}", seq);
        return data;
    }

    @Override
    public ArrayList<ProductListResponseForm> recommendationProduct() throws LevelUpToastEx {
        return recommendation.recommendedProducts();
    }

    /**
     * Product 상품 정보를 수정하는 요청 메소드
     * @param seq      수정할 Product seq
     * @param form     수정 내용을 담아 보낼 product
     */
    @Override
    public void updateProduct(Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException {
        Member member = simpleUserIntegrityVerification.isMember(form.getVendorToken());
        Optional<ResponseProductTable> originalProduct = simpleProductInspection.isProduct(seq);
        simpleProductInspection.isProductSEQ(originalProduct.orElseThrow().getVendorSeq(), member.getManageSeq());
        productRepository.updateProduct(seq, new ResponseProductTable(
                form.getTitle(),
                form.getInitialImgUrl(),
                form.getTag(),
                new FundingInfo(
                        0,
                        form.getFinalAmount(),
                        form.getDeadline()),
                originalProduct.orElseThrow().getLike(),
                originalProduct.orElseThrow().getVendorSeq(),
                new ResponseProductInfo(
                        form.getText(),
                        form.getProductImgUrl()),
                form.getBuyOption(),
                originalProduct.orElseThrow().getReview()
        ));
        log.info("[ProductService log] 제품 업데이트 완료 SEQ = {}", originalProduct.orElseThrow().getVendorSeq());
    }

}
