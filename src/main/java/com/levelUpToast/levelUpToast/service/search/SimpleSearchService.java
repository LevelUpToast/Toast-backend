package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.product.Product;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSearchService implements SearchService{

    private final ProductRepository productRepository;

    public List<Product> getProduct(int index) {
        return productRepository.findAllProduct()
                .stream()
                .filter(p -> p.getProductSeq() <= index)
                .filter(p -> p.getProductSeq() >= index - 10)
                .collect(Collectors.toList());
    }
}
