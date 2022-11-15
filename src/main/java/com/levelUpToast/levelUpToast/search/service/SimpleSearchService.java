package com.levelUpToast.levelUpToast.search.service;

import com.levelUpToast.levelUpToast.bodyForm.responseForm.ResponseForm;
import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.search.service.searchServiceInf.SearchService;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.product.ProductAdapter;
import com.levelUpToast.levelUpToast.util.wordAnalysis.SimpleWordAnalysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    private List<ProductListResponseForm> Search(String inputKeyword, int index) throws LevelUpToastEx {
        return productAdapter.toProductList(productRepository.findProductByTitle(Keyword(inputKeyword)));

    }

    /**
     * 사용자로 부터 word 받아 Product 해당 내용만 추출하는 메소드
     * @param inputKeyword 검색할 Keyword 내용
     * @return Keyword 데이터를 가공후 Return
     */
    private String Keyword( String inputKeyword) {
        SimpleWordAnalysis simpleWordAnalysis = new SimpleWordAnalysis();
        String searchKeyword = String.valueOf(simpleWordAnalysis.wordAnalysis(inputKeyword, 2));
        log.info("[SearchService log] 분석전 검색어 문장 = \"{}\"\t 추출된 명사 = {}", inputKeyword, searchKeyword);

        return searchKeyword;
    }

    @Override
    public Map<String, Object> SearchProduct(String inputKeyword, int index) throws LevelUpToastEx {
        Map<String, Object> data = new LinkedHashMap<>();
        log.info("[SearchController log] 입력받은 검색 요청 요청된 검색어 = \"{}\"\t 검색 index 범위 = {}", inputKeyword, index);

        List<ProductListResponseForm> searchResult = Search(inputKeyword, index);
        if (searchResult.isEmpty()){
            throw new LevelUpToastEx("검색 요청 데이터 없음", -1);
        }
        data.put("searchProduct", searchResult);
        return data;
    }


}