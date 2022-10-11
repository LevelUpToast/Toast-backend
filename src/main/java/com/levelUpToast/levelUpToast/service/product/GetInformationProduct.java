package com.levelUpToast.levelUpToast.service.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.product.InformationProduct;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetInformationProduct implements InformationProduct {

    private final ProductRepository productRepository;

    /**
     * 특정 Product 데이터를 요청하고 Return 한다.
     *
     * @param seq 요청할 Seq 번호
     * @return 요청한 product 정보를 가져오고 넘겨준다.
     */
    public Optional<ResponseProductTable> getProduct(Long seq) throws LevelUpToastEx, SQLException {
        return productRepository.findProductBySeq(seq);
    }
}
