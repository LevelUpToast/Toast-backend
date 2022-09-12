package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryImpl;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MemoryProductRepository implements ProductRepository {


    private static final Map<Long, Product> productStore = new ConcurrentHashMap<>();
    private Long productSeq = 0L;

    @Override
    public Product saveProduct(Product product) {
        product.setProductSeq(productSeq++);
        productStore.put(product.getProductSeq(), product);
        return null;
    }

    @Override
    public void updateProduct(Long productSeq, Product newProduct) {
        if (productStore.containsKey(productSeq))
            productStore.put(productSeq, newProduct);
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
        return new ArrayList<>(productStore.values());
    }


    @Override
    public void removeProductBySeq(Long productSeq) {
        if (findProductBySeq(productSeq).isPresent()) productStore.remove(productSeq);
    }
}
