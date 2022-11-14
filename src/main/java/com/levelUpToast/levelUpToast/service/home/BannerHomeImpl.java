package com.levelUpToast.levelUpToast.service.home;

import com.levelUpToast.levelUpToast.domain.UseCase.home.BannerHome;
import com.levelUpToast.levelUpToast.domain.repository.homeRepository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BannerHomeImpl implements BannerHome {
    private final HomeRepository homeRepository;

    @Override
    public ArrayList<String> getBanner() {
        return homeRepository.getBanner();
    }
}
