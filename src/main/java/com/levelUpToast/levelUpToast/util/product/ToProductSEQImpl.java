package com.levelUpToast.levelUpToast.util.product;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.Img.ImgAdapter;
import com.levelUpToast.levelUpToast.domain.UseCase.util.adapter.product.ToProductSEQ;
import com.levelUpToast.levelUpToast.domain.data.product.DataBaseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.ResponseProductTable;
import com.levelUpToast.levelUpToast.domain.data.product.data.productinfo.DataBaseProductInfo;
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
