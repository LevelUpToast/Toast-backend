package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf;

import com.levelUpToast.levelUpToast.domain.product.Product;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {


    Product saveProduct(Product product); // 제품 저장

    Product updateProduct(Long productSeq, Product newProduct); // 제품 업데이트

    Optional<Product> findProductBySeq(Long productSeq); // 제품 번호를 통해 제품 찾기

    List<Product> findProductByTag(Tag tag); // 파라미터 태그를 가진 제품번호들 찾기

    List<Product> findAllProduct(); // 모든 제품 반환

    void removeProductBySeq(Long productSeq); // 제품 번호로 제품 제거

}
