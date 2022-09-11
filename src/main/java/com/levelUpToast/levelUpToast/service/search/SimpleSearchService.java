package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.service.function.ProductAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSearchService implements SearchService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter = new ProductAdapter();

    /**
     * 검색 내용을 요청받고 Product 내용을 반환하는 메소드
     * @param inputKeyword 검색할 Keyword 내용
     * @param index 페이지 형태로 불러온다.
     * @return 검색된 정보를 리턴한다 메소드 형태는 List<Product> 형태로 리턴한다.
     */
    @Override
    public List<Product> SearchProduct(String inputKeyword, int index) {
        return productAdapter.sizeController(Keyword(productRepository.findAllProduct(), inputKeyword), index);
    }

    /**
     * Keyword 받아 Product에서 해당 내용만 추출하는 메소드
     * @param product 정보를 추출할 product
     * @param inputKeyword 검색할 Keyword 내용
     * @return Keyword 데이터를 가공후 Return
     */
    @Override
    public List<Product> Keyword(List<Product> product, String inputKeyword){
        return product.stream().filter(p -> p.getTitle().contains(inputKeyword)).collect(Collectors.toList());

    }

}