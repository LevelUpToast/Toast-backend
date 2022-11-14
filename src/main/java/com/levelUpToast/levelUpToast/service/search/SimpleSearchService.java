package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.search.SearchProduct;
import com.levelUpToast.levelUpToast.domain.UseCase.search.SearchService;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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