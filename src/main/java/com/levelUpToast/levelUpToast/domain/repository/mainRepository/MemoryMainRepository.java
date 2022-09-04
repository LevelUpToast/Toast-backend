package com.levelUpToast.levelUpToast.domain.repository.mainRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MemoryMainRepository implements MainRepository{
    private static final ArrayList<String> banner = new ArrayList<>();

    public ArrayList<String> getBanner(){
        return banner;
    }

    public void setBanner(String banner){
        MemoryMainRepository.banner.add(banner);
    }


}
