package com.levelUpToast.levelUpToast.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.Img.ImgAdapter;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.product.ToProductUUID;
import com.levelUpToast.levelUpToast.product.domain.DataBaseProductTable;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.ResponseProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToProductUUIDImpl implements ToProductUUID {
    private final ImgAdapter simpleImgAdapter;
    @Override
    public ResponseProductTable toProductUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx {
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
}
