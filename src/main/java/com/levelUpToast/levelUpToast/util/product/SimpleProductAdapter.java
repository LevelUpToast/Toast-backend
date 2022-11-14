package com.levelUpToast.levelUpToast.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product.ProductAdapter;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product.ToProductSEQ;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product.ToProductUUID;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.bodyForm.requestForm.product.ProductListResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code @sizeController} Product 데이터를 원하는 Size 만큼 가공하거나 10개 가공하는 메소드 데이터를 호출시 sizeController(List<Product> product)이거나 sizeController(List<Product> product, int index)으로 호출해야한다.
 * {@code @listAdapter} Product 형식의 데이터를 ProductList 형식으로 변환해주는 Adapter
 */

@Service
@RequiredArgsConstructor
public class SimpleProductAdapter implements ProductAdapter {

    private final ToProductSEQ toProductSEQImpl;

    private final ToProductUUID toProductUUIDImpl;

    /**
     * Product 데이터를 받고 데이터를 10개로 데이터를 가공하는 함수
     * 단 데이터가 많아질 경우 처리가 늦어질 수 있음(개선 여지가 필요함)
     *
     * @param responseProductTable 데이터를 가공할 Product
     * @return 요청된 데이터 중 무조건 최상위 10개만 반환한다.
     */
    @Override
    public List<ResponseProductTable> sizeController(List<ResponseProductTable> responseProductTable) {
        Collections.reverse(responseProductTable);
        return responseProductTable.stream().filter(p -> p.getProductSeq() > responseProductTable.size() - 10).collect(Collectors.toList());
    }

    /**
     * Product 데이터 형식을 리스트 형식으로 변경해주는 어뎁터
     *
     * @param responseProductTable 데이터를 가공할 Product
     * @return Product 형태를 ProductList 형태로 변환해서 반환한다.
     */
    @Override
    public List<ProductListResponseForm> toProductList(List<ResponseProductTable> responseProductTable) {
        List<ProductListResponseForm> list = new ArrayList<>();
        for (ResponseProductTable value : responseProductTable)
            list.add(new ProductListResponseForm(value.getProductSeq(), value.getTitle(), value.getInitialImgUrl(), value.getTag(), value.getFunding()));
        return list;
    }

    /**
     * 사용자로부터 입력받은 Product 형식을 DB 형식으로 변환하는 메소드
     * @param responseProductTable 사용자로부터 받은 Response 테이블
     * @return ResponseProductTable 형식을 변환후 DataBaseProductTable 형식으로 반환
     * @throws LevelUpToastEx 변환 과정중 오류가 발생하면 Throws 발생
     */

    @Override
    public DataBaseProductTable toProductSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx {
      return toProductSEQImpl.toProductSEQ(responseProductTable);
    }


    /**
     * 서버에서 사용자에게 보낼형식으로 만드는 과정
     * @param seq 변환할 Product 번호
     * @param product 변환 대상의 DataBaseProductTable 형식
     * @return DataBaseProductTable 형식을 변환후 ResponseProductTable 형식으로 반환
     * @throws LevelUpToastEx 변환 과정중 오류가 발생하면 Throws 발생
     */
    @Override
    public ResponseProductTable toProductUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx {
        return toProductUUIDImpl.toProductUUID(seq,product);
    }


}
