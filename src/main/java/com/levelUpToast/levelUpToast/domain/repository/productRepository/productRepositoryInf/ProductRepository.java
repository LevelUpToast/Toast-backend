package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf;

import com.levelUpToast.levelUpToast.domain.product.ProductDetail;
import com.levelUpToast.levelUpToast.domain.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {


    ProductDetail saveProduct(ProductDetail productDetail);

    ProductDetail updateProduct(Long productSeq, ProductDetail productDetail);

    Optional<ProductDetail> findByProductSeq(Long productSeq);

    Optional<ProductDetail> findByProductTag(Tag tag);

    Optional<ProductDetail> findByFunding(int date);

    List<ProductDetail> findAllProduct();

    void delete(Long productSeq);

}
