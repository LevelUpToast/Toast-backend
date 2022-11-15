package com.levelUpToast.levelUpToast.home.repository;

import java.util.ArrayList;

public interface HomeRepository {
    ArrayList<String> getBanner();
    void setBanner(String banner);
}
