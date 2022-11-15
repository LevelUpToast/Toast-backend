package com.levelUpToast.levelUpToast.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.service.productServiceInf.ProductService;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.util.utilInf.ProblemCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleProblemCheck implements ProblemCheck {
    private final ProductService simpleProductService;

//    private final ProductIsProduct simpleProductIsProduct;

    /**
     * 요청한 클라이언트가 일치한지 확인하는 메소드
     * 인증절차에서 문제가 있다면 throw 발생하여 문제를 알려준다.
     * 문제가 없다면 그냥 실행이후 행동 없이 종류된다.
     *
     * @param originalProductVendor 등록된 SEQ 정보를 받는다.
     * @param requestSeq            수정을 요청한 클라이언트의 SEQ
     */
    public void isProductSEQ(Long originalProductVendor, Long requestSeq) throws LevelUpToastEx {
        if (!Objects.equals(originalProductVendor, requestSeq)) { //삭제 요청한 Seq 판매자의 seq 같은지 확인한다.
            log.warn("[ProductService log] 판매자 정보가 일치하지 않습니다. 요청된 판매자 seq = {}\t제품 판매자 seq = {}", requestSeq, originalProductVendor);
            throw new LevelUpToastEx("판매자 정보가 일치하지 않습니다.", 54);
        }
//        simpleProductIsProduct.isProductSEQ(originalProductVendor, requestSeq);
    }

    /**
     * Product 무결섬을 체크하기 위한 메소드
     * productSeq 넣어 product 불러오고 데이터가 유효한지 잘못된 데이터인지 판단하고 문제가 없다면 Return
     *
     * @param productSeq 확인할 ProductSEQ 정보를 입력받는 파라미터
     * @return 입력받은 ProductSEQ 검사하고 문제가 없다면 Product 데이터를 Return
     * @throws LevelUpToastEx 오류가 발생한다면 오류 코드와 메세지를 반환
     */
    public Optional<ResponseProductTable> isProduct(Long productSeq) throws LevelUpToastEx, SQLException {
        Optional<ResponseProductTable> product = simpleProductService.getProduct(productSeq);
        if (product.isEmpty()) {
            log.info("[ProductService log] 요청된 제품 Seq 없거나 잘못된 번호입니다. SEQ = {}", productSeq);
            throw new LevelUpToastEx("요청된 제품 Seq 없거나 잘못된 번호입니다.", 53);
        }
        return product;
    }

}
