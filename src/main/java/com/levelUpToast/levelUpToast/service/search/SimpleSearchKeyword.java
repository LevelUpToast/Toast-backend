package com.levelUpToast.levelUpToast.service.search;

import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleSearchKeyword {

    private static final Queue<String> keywordSearch = new LinkedList<>();

    public void setKeyword(String title) {
        if (keywordSearch.size() <= 10)
            keywordSearch.add(title);
        else{
            keywordSearch.add(title);
            keywordSearch.remove();
        }
    }

    public List<String> getKeyword() {
        return (List<String>) keywordSearch;
    }
}
