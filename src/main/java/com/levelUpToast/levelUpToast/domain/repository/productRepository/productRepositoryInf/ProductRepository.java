package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf;

import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {


    void saveProduct(ResponseProductTable responseProductTable); // 제품 저장

    void updateProduct(Long productSeq, ResponseProductTable newResponseProductTable); // 제품 업데이트

    Optional<ResponseProductTable> findProductBySeq(Long productSeq); // 제품 번호를 통해 제품 찾기

    List<ResponseProductTable> findProductByTag(Tag tag); // 파라미터 태그를 가진 제품번호들 찾기

    List<ResponseProductTable> findProductByTitle(String title);

    ArrayList<ResponseProductTable> findAllProduct(); // 모든 제품 반환

    void removeProductBySeq(Long productSeq); // 제품 번호로 제품 제거

}
