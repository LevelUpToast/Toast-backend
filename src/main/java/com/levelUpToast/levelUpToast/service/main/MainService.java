package com.levelUpToast.levelUpToast.service.main;

import com.levelUpToast.levelUpToast.domain.member.Member;

import java.util.Map;

public interface MainService {
    Map<String, Object> mainPage(Member loggedMem);

}
