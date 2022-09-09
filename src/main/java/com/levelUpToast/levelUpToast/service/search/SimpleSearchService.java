package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSearchService implements SearchService {

    private final ProductRepository productRepository;

    public List<Product> sizeController(List<Product> product, int index) {
        return product
                .stream()
                .filter(p -> p.getProductSeq() <= index * 10L)
                .filter(p -> p.getProductSeq() >= index - 10)
                .collect(Collectors.toList());
    }

    public List<Product> processingProduct(String title ) {
        List<Product> Test = productRepository.findProductByTitle(title);
        return sizeController(Test, 1);
    }

    public Map<String, Object> Search(int index) {

        return null;
    }
}