package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.function.product.productAdapter.ProductAdapter;
import com.levelUpToast.levelUpToast.function.wordAnalysis.SimpleWordAnalysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSearchService implements SearchService {

    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    /**
     * 검색 내용을 요청받고 Product 내용을 반환하는 메소드
     * @param inputKeyword 검색할 Keyword 내용
     * @param index        페이지 형태로 불러온다.
     * @return 검색된 정보를 리턴한다 메소드 형태는 List<Product> 형태로 리턴한다.
     */
    @Override
    public List<ProductListResponseForm> SearchProduct(String inputKeyword, int index) {
        return productAdapter.changeProductList(Keyword(productAdapter.sizeController(productRepository.findAllProduct(), index), inputKeyword));
    }

    /**
     * 사용자로 부터 word 받아 Product에 해당 내용만 추출하는 메소드
     * @param responseProductTable      정보를 추출할 product
     * @param inputKeyword 검색할 Keyword 내용
     * @return Keyword 데이터를 가공후 Return
     */
    @Override
    public List<ResponseProductTable> Keyword(List<ResponseProductTable> responseProductTable, String inputKeyword) {
        SimpleWordAnalysis simpleWordAnalysis = new SimpleWordAnalysis();
        ArrayList<String> searchKeyword = simpleWordAnalysis.wordAnalysis(inputKeyword, 2);
        log.info("[SearchService log] 분석전 검색어 문장 = \"{}\"\t 추출된 명사 = {}", inputKeyword, searchKeyword);

        return responseProductTable.stream().filter(p -> {
            for (String text : searchKeyword) if (p.getTitle().contains(text)) return true;
            return false;
        }).collect(Collectors.toList());
    }

}