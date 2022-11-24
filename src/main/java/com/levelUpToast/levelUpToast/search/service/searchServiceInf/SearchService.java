package com.levelUpToast.levelUpToast.search.service.searchServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface SearchService {
    Map<String, Object> getSearchKeyword();

    Map<String, Object> getSearchProduct(String title, int index) throws LevelUpToastEx;

}
