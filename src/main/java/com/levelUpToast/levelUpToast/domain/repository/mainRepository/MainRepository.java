package com.levelUpToast.levelUpToast.domain.repository.mainRepository;

import java.util.ArrayList;

public interface MainRepository {
    ArrayList<String> getBanner();

    void setBanner(String banner);
}
