package com.levelUpToast.levelUpToast.config;

import com.levelUpToast.levelUpToast.domain.model.member.Authority;
import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.model.product.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.domain.model.product.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.domain.model.product.productinfo.ProductInfo;
import com.levelUpToast.levelUpToast.domain.model.product.reviwe.Review;
import com.levelUpToast.levelUpToast.domain.model.product.tag.Tag;
import com.levelUpToast.levelUpToast.domain.repository.mainRepository.MainRepository;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.domain.model.vendor.Vendor;
import com.levelUpToast.levelUpToast.service.vendor.vendorServiceInf.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class InitData {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final VendorService vendorService;

    private final MainRepository mainRepository;

    @PostConstruct
    public void init() {
        //init Member
        Member ji = new Member("ji", "ji", Authority.ADMIN, "김지용",  "남", "010-6277-0650", "colorful8315@gmail.com", "seoul");
        Member mook = new Member("mook", "mook", Authority.ADMIN, "임성묵", "남", "---", "---", "seoul");
        memberRepository.save(ji);
        memberRepository.save(mook);
        memberRepository.save(new Member("beom", "beom", Authority.ADMIN, "김준범", "남", "---", "---", "seoul"));
        memberRepository.save(new Member("saac", "saac", Authority.ADMIN, "이삭",  "남", "---", "---", "seoul"));

        //init vendor
        Vendor testingVendor = vendorService.register(ji.getManageSeq(), ji.getName(), "196f3226-0a90-4944-ad13-4a147ce323b6.jpeg", "용묵농업");

        //init product
        productRepository.saveProduct(
                new Product(
                        "수입산 망고",
                        new ArrayList<>(Arrays.asList("97b4bc2f-c3a2-4c35-8b1a-eec4f3ee60e8.png", "2624e45c-15b2-4be8-9cb5-0c2b35a2d4a8.png")),
                        Tag.FRUIT,
                        new FundingInfo(5000, 10000, "2023-01-01"),
                        100,
                        testingVendor.getVendorSeq(),
                        new ProductInfo(
                                "맛있는 망고입니다 \n" +
                                        "\n" +
                                        "<img1> \n" +
                                        "이미지 보이시죠 맛있어요\n" +
                                        "\n" +
                                        "<img2>\n" +
                                        "꼭 먹어보세요",
                                new ArrayList<>(Arrays.asList("97b4bc2f-c3a2-4c35-8b1a-eec4f3ee60e8.png", "2624e45c-15b2-4be8-9cb5-0c2b35a2d4a8.png"))
                        ),
                        new ArrayList<>(Arrays.asList(new BuyOption("1kg", 1000), new BuyOption("2kg", 2000), new BuyOption("3kg", 3000))),
                        new Review(10, 5, 6, 7, 1)
                ));

        productRepository.saveProduct(
                new Product(
                        "한국산 대파",
                        new ArrayList<>(Arrays.asList("c7c9f10d-7e28-47ee-9844-0c180a9ed6f6.png", "3787d445-1a6d-4f16-8ddb-84d4d5bf89ad.png")),
                        Tag.VEGETABLE,
                        new FundingInfo(1000, 80000, "2023-03-01"),
                        999,
                        testingVendor.getVendorSeq(),
                        new ProductInfo(
                                "한국식 대파 입니다 \n" +
                                        "\n" +
                                        "<img1> \n" +
                                        "이미지 보이시죠 맛있어요\n" +
                                        "\n" +
                                        "<img2>\n" +
                                        "실제 오리가 보내드립니다.",
                                new ArrayList<>(Arrays.asList("c7c9f10d-7e28-47ee-9844-0c180a9ed6f6.png", "3787d445-1a6d-4f16-8ddb-84d4d5bf89ad.png"))
                        ),
                        new ArrayList<>(Arrays.asList(new BuyOption("1단", 1000), new BuyOption("2단", 2000), new BuyOption("3단", 3000))),
                        new Review(1022, 52, 63, 74, 1)
                ));

        //init main banner
        mainRepository.setBanner("e2f8f5f0-182e-4710-9b42-1c78d0b6f5eb.png");
    }



}
