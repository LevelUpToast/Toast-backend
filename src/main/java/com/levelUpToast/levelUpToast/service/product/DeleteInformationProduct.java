package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.domain.UseCase.product.DeleteProduct;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteInformationProduct implements DeleteProduct {
    private final ProductRepository productRepository;

    /**
     * 등록된 제품을 삭제하는 메소드
     *
     * @param seq 삭제할 Product Seq 요청하면 삭제를 진행
     *            Return 존재하지 않는다.
     */
    @Override
    public void deleteProduct(Long seq) {
        productRepository.removeProductBySeq(seq);
    }
}
