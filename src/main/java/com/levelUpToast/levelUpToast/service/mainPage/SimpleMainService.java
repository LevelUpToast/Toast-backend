package com.levelUpToast.levelUpToast.service.mainPage;

import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.mainRepository.MainRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMainService implements MainService {

    private final ProductRepository productRepository;
    private final MainRepository mainRepository;

    @Override
    public String myFundingProducts() {
        return null;
    }

    @Override
    public List<Product> listProcessing(List<Product> product){
        return product
                .stream()
                .filter(p -> p.getProductSeq() > product.size() - 10)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProduct() {return listProcessing(productRepository.findAllProduct()); }

    @Override
    public List<Product> getProductTag(Tag tag) { return listProcessing(productRepository.findProductByTag(tag)); }


    @Override
    public Map<String, Object> mainPage(Member loggedMem) {
        Map<String, Object> data = new LinkedHashMap<>();

        data.put("bannerImgUrl", mainRepository.getBanner());
        if (loggedMem != null) data.put("myFundingProducts", myFundingProducts()); // 펀딩레포지토리 완성 이후 token을 통해 찾아서 넣을 예정
        else data.put("myFundingProducts", null);
        data.put("recommendedProducts", getProduct());
        data.put("fruitProducts", getProductTag(Tag.FRUIT));
        data.put("vegetableProducts", getProductTag(Tag.VEGETABLE));

        return data;
    }
}
