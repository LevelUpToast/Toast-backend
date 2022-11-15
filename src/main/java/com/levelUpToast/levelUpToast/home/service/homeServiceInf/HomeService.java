package com.levelUpToast.levelUpToast.home.service.homeServiceInf;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;

import java.util.Map;

public interface HomeService {

    Map<String, Object> homeService(String token) throws LevelUpToastEx;
}
