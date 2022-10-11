package com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.img.adapter.ImgAdapter;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.DataBaseProductInfo;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.domain.repository.productRepository.productRepositoryInf.ProductRepository;
import com.levelUpToast.levelUpToast.domain.data.product.data.tag.Tag;
import com.levelUpToast.levelUpToast.util.img.SimpleImgAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MemoryProductRepository implements ProductRepository {

    private static final Map<Long, DataBaseProductTable> productStore = new ConcurrentHashMap<>();
    private final ImgAdapter simpleImgAdapter;
    private Long productSeq = 0L;

    private DataBaseProductTable changeImgToSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx {
        return new DataBaseProductTable(
                responseProductTable.getTitle(),
                simpleImgAdapter.extractImgSeq(responseProductTable.getInitialImgUrl()),
                responseProductTable.getTag(),
                responseProductTable.getFunding(),
                responseProductTable.getLike(),
                responseProductTable.getVendorSeq(),
                new DataBaseProductInfo(
                        responseProductTable.getProductInfo().getText(),
                        simpleImgAdapter.extractImgSeq(responseProductTable.getProductInfo().getProductImgUrl())
                ),
                responseProductTable.getBuyOption(),
                responseProductTable.getReview()
        );
    }

    private ResponseProductTable changeImgToUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx {
        ResponseProductTable changeResponseProductTable = new ResponseProductTable(
                product.getTitle(),
                simpleImgAdapter.extractImgUUID(product.getInitialImgUrl()),
                product.getTag(),
                product.getFunding(),
                product.getLike(),
                product.getVendorSeq(),
                new ResponseProductInfo(
                        product.getProductInfo().getText(),
                        simpleImgAdapter.extractImgUUID(product.getProductInfo().getProductImgUrl())
                ),
                product.getBuyOption(),
                product.getReview());
        changeResponseProductTable.setProductSeq(seq);
        return changeResponseProductTable;
    }

    @Override
    public ResponseProductTable saveProduct(ResponseProductTable responseProductTable) throws LevelUpToastEx {
        DataBaseProductTable dataBaseProductTable = changeImgToSEQ(responseProductTable);
        dataBaseProductTable.setProductSeq(productSeq++);
        productStore.put(dataBaseProductTable.getProductSeq(), dataBaseProductTable);

        responseProductTable.setProductSeq(dataBaseProductTable.getProductSeq()); //디버그용 코드 무시해도됨
        return responseProductTable;
    }

    @Override
    public void updateProduct(Long productSeq, ResponseProductTable newResponseProductTable) throws LevelUpToastEx {
        if (productStore.containsKey(productSeq)) {
            DataBaseProductTable dataBaseProductTable = changeImgToSEQ(newResponseProductTable);
            dataBaseProductTable.setProductSeq(productSeq);
            productStore.put(productSeq, dataBaseProductTable);
        }

    }

    @Override
    public Optional<ResponseProductTable> findProductBySeq(Long productSeq) throws LevelUpToastEx {
        if (productStore.containsKey(productSeq))
            return findAllProduct().stream().filter(product -> product.getProductSeq().equals(productSeq)).findFirst();
        return Optional.empty();
    }

    public List<ResponseProductTable> findProductByTitle(String title) throws LevelUpToastEx {
        return findAllProduct().stream().filter(p -> p.getTitle().equals(title)).collect(Collectors.toList());
    }


    @Override
    public List<ResponseProductTable> findProductByTag(Tag tag) throws LevelUpToastEx {
        return findAllProduct().stream().filter(p -> p.getTag().equals(tag)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<ResponseProductTable> findAllProduct() throws LevelUpToastEx {
        ArrayList<ResponseProductTable> chResponseProductTable = new ArrayList<>();
        for (DataBaseProductTable dataBaseProductTable : productStore.values())
            chResponseProductTable.add(changeImgToUUID(dataBaseProductTable.getProductSeq(), dataBaseProductTable));
        return chResponseProductTable;
    }

    @Override
    public void removeProductBySeq(Long productSeq) throws LevelUpToastEx {
        if (findProductBySeq(productSeq).isPresent()) productStore.remove(productSeq);
    }
}
