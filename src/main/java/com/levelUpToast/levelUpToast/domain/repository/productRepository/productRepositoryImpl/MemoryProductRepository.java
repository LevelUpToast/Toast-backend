package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryImpl;

import com.levelUpToast.levelUpToast.domain.product.ProductDetail;
import com.levelUpToast.levelUpToast.domain.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryProductRepository implements ProductRepository {


    private static Map<Long, ProductDetail> productList = new ConcurrentHashMap<>();
    private Long productSeq = 0L;

    @Override
    public ProductDetail saveProduct(ProductDetail productDetail) {
        productDetail.setProductSeq(productSeq++);
        productList.put(productDetail.getProductSeq(), productDetail);
        return null;
    }

    @Override
    public ProductDetail updateProduct(Long productSeq, ProductDetail productDetail) {
        if (productList.containsKey(productSeq))
            productList.put(productSeq, productDetail);
        return null;
    }

    @Override
    public Optional<ProductDetail> findByProductSeq(Long productSeq) {
        if (productList.containsKey(productSeq))
            return findAllProduct().stream().filter(product -> product.getProductSeq().equals(productSeq)).findFirst();
        return Optional.empty();
    }

    @Override
    public Optional<ProductDetail> findByProductTag(Tag tag) {
        if (productList.containsKey(productSeq))
            return findAllProduct().stream().filter(product -> product.getTag().equals(tag)).findFirst();
        return Optional.empty();
    }

    @Override
    public Optional<ProductDetail> findByFunding(int date) {
        if (productList.containsKey(productSeq))
            return findAllProduct().stream().filter(product -> product.getFunding().getDeadline() < date).findFirst();
        return Optional.empty();
    }

    @Override
    public List<ProductDetail> findAllProduct() {
        return new ArrayList<>(productList.values());
    }

    @Override
    public void delete(Long productSeq) {

    }
}
