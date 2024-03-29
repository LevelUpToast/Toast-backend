package com.levelUpToast.levelUpToast.product.repository.productRepositoryInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.domain.data.tag.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {


    ResponseProductTable saveProduct(ResponseProductTable responseProductTable) throws LevelUpToastEx, SQLException; // 제품 저장

    void updateProduct(Long productSeq, ResponseProductTable newResponseProductTable) throws LevelUpToastEx, SQLException; // 제품 업데이트

    Optional<ResponseProductTable> findProductBySeq(Long productSeq) throws LevelUpToastEx, SQLException; // 제품 번호를 통해 제품 찾기

    List<ResponseProductTable> findProductByTag(Tag tag) throws LevelUpToastEx; // 파라미터 태그를 가진 제품번호들 찾기

    List<ResponseProductTable> findProductByTitle(String title) throws LevelUpToastEx;

    ArrayList<ResponseProductTable> findAllProduct() throws LevelUpToastEx; // 모든 제품 반환

    void removeProductBySeq(Long productSeq) throws LevelUpToastEx; // 제품 번호로 제품 제거

}
