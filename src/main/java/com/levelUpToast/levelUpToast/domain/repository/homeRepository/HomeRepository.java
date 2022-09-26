package com.levelUpToast.levelUpToast.domain.repository.homeRepository;

import java.util.ArrayList;

public interface HomeRepository {
    ArrayList<String> getBanner();
    void setBanner(String banner);
}
