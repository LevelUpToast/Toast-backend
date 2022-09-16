package com.levelUpToast.levelUpToast.function.productAdapter;

import com.levelUpToast.levelUpToast.domain.model.product.Product;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductDetailResponseForm;
import com.levelUpToast.levelUpToast.domain.dataForm.requestForm.product.ProductListResponseForm;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @sizeController Product 데이터를 원하는 Size 만큼 가공하거나 10개 가공하는 메소드 데이터를 호출시 sizeController(List<Product> product)이거나 sizeController(List<Product> product, int index)으로 호출해야한다.
 * @listAdapter Product 형식의 데이터를 ProductList 형식으로 변환해주는 Adapter
 */

@Service
@RequiredArgsConstructor
public class SimpleProductAdapter implements ProductAdapter {

    private final ImgRepository imgRepository;

    /**
     * Product 데이터를 받고 데이터를 10개로 데이터를 가공하는 함수
     * 단 데이터가 많아질 경우 처리가 늦어질 수 있음(개선 여지가 필요함)
     *
     * @param product 데이터를 가공할 Product
     * @return 요청된 데이터 중 무조건 최상위 10개만 반환한다.
     */
    @Override
    public List<Product> sizeController(List<Product> product) {
        Collections.reverse(product);
        return product.stream().filter(p -> p.getProductSeq() > product.size() - 10).collect(Collectors.toList());
    }

    /**
     * Product 데이터를 받고 데이터를 요청에 따른 데이터를 가공하는 함수
     * 단 데이터가 많아질 경우 처리가 늦어질 수 있음(개선 여지가 필요함)
     *
     * @param product 데이터를 가공할 Product
     * @param index   요청에 따라 페이지 형태로 10개식 잘라서 요청
     * @return 요청 받은 index 사이트에 따라 정보를 가공하고 반환한다
     * @index 요청에 1을 넣을경우 최상위 10개만 추출한다
     * 2가 요청될경우 최상위에서 20개 이전에 있는 내용을 요청한다.
     */
    @Override
    public List<Product> sizeController(List<Product> product, int index) {
        Collections.reverse(product);
        return product.stream().filter(p -> (p.getProductSeq() > product.size() - index * 10L) && (p.getProductSeq() < product.size() - (10 - (index * 10L)))).collect(Collectors.toList());
    }

    /**
     * Product 데이터 형식을 리스트 형식으로 변경해주는 어뎁터
     *
     * @param product 데이터를 가공할 Product
     * @return Product 형태를 ProductList 형태로 변환해서 반환한다.
     */
    @Override
    public List<ProductListResponseForm> changeProductList(List<Product> product) {
        List<ProductListResponseForm> list = new ArrayList<>();
        for (Product value : product)
            list.add(new ProductListResponseForm(value.getProductSeq(), value.getTitle(), extractImgUUID(value.getInitialImgUrl()), value.getTag(), value.getFunding()));
        return list;
    }

    public ProductDetailResponseForm changeProductResponseForm(Optional<Product> product) {

        return new ProductDetailResponseForm(
                product.orElseThrow().getTitle(),
                extractImgUUID(product.orElseThrow().getInitialImgUrl()),
                product.orElseThrow().getTag(),
                product.orElseThrow().getFunding(),
                product.orElseThrow().getLike(),
                product.orElseThrow().getVendorSeq(),
                product.orElseThrow().getProductInfo(),
                product.orElseThrow().getBuyOption(),
                product.orElseThrow().getReview()
        );
    }

    @Override
    public List<String> extractImgUUID(List<Long> imgSeq) {
        List<String> imgUUIDList = new ArrayList<>();
        for (Long seq : imgSeq) {
            imgUUIDList.add(imgRepository.findBySeq(seq).getStoreFileName());
        }
        return imgUUIDList;
    }

    @Override
    public List<Long> extractImgSeq(List<String> imgUUIDList) {
        ArrayList<Long> imgSeqList = new ArrayList<>();
        for (String imgUUID : imgUUIDList) {
            imgSeqList.add(imgRepository.findByImgUUID(imgUUID).getManageSeq());
        }
        return imgSeqList;
    }

}
