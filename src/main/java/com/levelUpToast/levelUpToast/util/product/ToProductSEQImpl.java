package com.levelUpToast.levelUpToast.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.Img.ImgAdapter;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.product.ToProductSEQ;
import com.levelUpToast.levelUpToast.product.domain.DataBaseProductTable;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.DataBaseProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToProductSEQImpl implements ToProductSEQ {
    private final ImgAdapter simpleImgAdapter;

    @Override
    public DataBaseProductTable toProductSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx {
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
}
