package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.UseCase.search.SearchProduct;
import com.levelUpToast.levelUpToast.domain.UseCase.search.SearchService;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
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
    public List<ProductListResponseForm> SearchProduct(String inputKeyword, int index) {
        return searchProduct.Search(inputKeyword, index);
    }


}