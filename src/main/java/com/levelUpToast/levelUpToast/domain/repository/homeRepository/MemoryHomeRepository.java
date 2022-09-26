package com.levelUpToast.levelUpToast.domain.repository.homeRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MemoryHomeRepository implements HomeRepository {

    private static final ArrayList<String> banner = new ArrayList<>();

    @Override
    public ArrayList<String> getBanner(){
        return banner;
    }

    @Override
    public void setBanner(String banner) {
        MemoryHomeRepository.banner.add(banner);
    }


}
