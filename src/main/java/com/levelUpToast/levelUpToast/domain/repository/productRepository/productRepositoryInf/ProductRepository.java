package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {


    void saveProduct(Product product); // 제품 저장

    void updateProduct(Long productSeq, Product newProduct); // 제품 업데이트

    Optional<Product> findProductBySeq(Long productSeq); // 제품 번호를 통해 제품 찾기

    List<Product> findProductByTag(Tag tag); // 파라미터 태그를 가진 제품번호들 찾기

    List<Product> findProductByTitle(String title);

    ArrayList<Product> findAllProduct(); // 모든 제품 반환

    void removeProductBySeq(Long productSeq); // 제품 번호로 제품 제거

}
