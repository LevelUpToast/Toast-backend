package com.levelUpToast.levelUpToast.search.service;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.search.service.searchServiceInf.SearchProduct;
import com.levelUpToast.levelUpToast.search.service.searchServiceInf.SearchService;
import com.levelUpToast.levelUpToast.bodyForm.requestForm.product.ProductListResponseForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSearchService implements SearchService {

    private final SearchProduct searchProduct;

    @Override
    public List<ProductListResponseForm> SearchProduct(String inputKeyword, int index) throws LevelUpToastEx {
        return searchProduct.Search(inputKeyword, index);
    }


}