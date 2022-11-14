package com.levelUpToast.levelUpToast.service.product;


import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.product.*;
import com.levelUpToast.levelUpToast.domain.UseCase.product.InformationProduct;
import com.levelUpToast.levelUpToast.domain.UseCase.util.recommendation.Recommendation;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductRequestForm;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProductService implements ProductService {
    private final DeleteProduct deleteProduct;
    private final InformationProduct informationProduct;
    private final UpdateProduct updateProduct;
    private final RegisterProduct registerProduct;
    private final Recommendation recommendation;

    /**
     * Product 데이터 저장하는 메소드
     *
     * @param form      요청받은 Body 내용을 받아 Product 형태로 만든다.
     * @param ManageSeq 제품 등록하는 판매자의 Seq 등록한다.
     * @return 데이터 폼을 만든 것을 Return 한다.
     */
    @Override
    public Long registerProduct(ProductRequestForm form, Long ManageSeq) throws LevelUpToastEx, SQLException {
        return registerProduct.registerProductInfo(form, ManageSeq).getProductSeq();
    }

    /**
     * 등록된 제품을 삭제하는 메소드
     *
     * @param seq 삭제할 Product Seq 요청하면 삭제를 진행
     *            Return 존재하지 않는다.
     */
    @Override
    public void deleteProduct(Long seq) throws LevelUpToastEx {
        deleteProduct.deleteProduct(seq);
    }

    /**
     * 특정 Product 데이터를 요청하고 Return 한다.
     *
     * @param seq 요청할 Seq 번호
     * @return 요청한 product 정보를 가져오고 넘겨준다.
     */
    @Override
    public Optional<ResponseProductTable> getProduct(Long seq) throws LevelUpToastEx, SQLException {
        return informationProduct.getProductByInfo(seq);
    }

    @Override
    public ArrayList<ProductListResponseForm> recommendationProduct() throws LevelUpToastEx {
        return recommendation.recommendedProducts();
    }

    /**
     * Product 상품 정보를 수정하는 요청 메소드
     *
     * @param original 수정 대상의 Product 데이터를 받는다.
     * @param seq      수정할 Product seq
     * @param form     수정 내용을 담아 보낼 product
     */
    @Override
    public void updateProduct(Optional<ResponseProductTable> original, Long seq, ProductRequestForm form) throws LevelUpToastEx, SQLException {
        updateProduct.updateProduct(original, seq, form);
    }

}
