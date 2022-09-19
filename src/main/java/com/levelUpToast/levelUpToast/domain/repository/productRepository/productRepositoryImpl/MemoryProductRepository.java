package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryImpl;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.ProductDB;
import com.levelUpToast.levelUpToast.domain.model.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.model.product.productinfo.ProductInfoDB;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.function.imgAdapter.SimpleImgAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemoryProductRepository implements ProductRepository {

    private static final Map<Long, ProductDB> productStore = new ConcurrentHashMap<>();
    private final SimpleImgAdapter simpleImgAdapter;
    private Long productSeq = 0L;

    private ProductDB changeImgToSEQ(Product product) {
        return new ProductDB(
                product.getTitle(),
                simpleImgAdapter.extractImgSeq(product.getInitialImgUrl()),
                product.getTag(),
                product.getFunding(),
                product.getLike(),
                product.getVendorSeq(),
                new ProductInfoDB(
                        product.getProductInfo().getText(),
                        simpleImgAdapter.extractImgSeq(product.getProductInfo().getProductImgUrl())
                ),
                product.getBuyOption(),
                product.getReview()
        );
    }

    private Product changeImgToUUID(Long seq, ProductDB product) {
        Product changeProduct = new Product(
                product.getTitle(),
                simpleImgAdapter.extractImgUUID(product.getInitialImgUrl()),
                product.getTag(),
                product.getFunding(),
                product.getLike(),
                product.getVendorSeq(),
                new ProductInfo(
                        product.getProductInfo().getText(),
                        simpleImgAdapter.extractImgUUID(product.getProductInfo().getProductImgUrl())
                ),
                product.getBuyOption(),
                product.getReview());
        changeProduct.setProductSeq(seq);
        return changeProduct;
    }

    @Override
    public void saveProduct(Product product) {
        ProductDB productDB = changeImgToSEQ(product);
        productDB.setProductSeq(productSeq++);
        productStore.put(productDB.getProductSeq(), productDB);

        product.setProductSeq(productDB.getProductSeq()); //디버그용 코드 무시해도됨
    }

    @Override
    public void updateProduct(Long productSeq, Product newProduct) {
        if (productStore.containsKey(productSeq)) {
            ProductDB productDB = changeImgToSEQ(newProduct);
            productDB.setProductSeq(productSeq);
            productStore.put(productSeq, productDB);
        }

    }

    @Override
    public Optional<Product> findProductBySeq(Long productSeq) {
        if (productStore.containsKey(productSeq))
            return findAllProduct().stream().filter(product -> product.getProductSeq().equals(productSeq)).findFirst();
        return Optional.empty();
    }

    public List<Product> findProductByTitle(String title) {
        return findAllProduct().stream().filter(p -> p.getTitle().equals(title)).collect(Collectors.toList());
    }


    @Override
    public List<Product> findProductByTag(Tag tag) {
        return findAllProduct().stream().filter(p -> p.getTag().equals(tag)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<Product> findAllProduct() {
        ArrayList<Product> chProduct = new ArrayList<>();
        for (ProductDB productDB : productStore.values())
            chProduct.add(changeImgToUUID(productDB.getProductSeq(), productDB));
        return chProduct;
    }

    @Override
    public void removeProductBySeq(Long productSeq) {
        if (findProductBySeq(productSeq).isPresent()) productStore.remove(productSeq);
    }
}
