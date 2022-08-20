package com.levelUpToast.levelUpToast.domain.dataForm.signUp;

import lombok.Data;

@Data
public class SignUpRequestForm {

    private String id; // id
    private String pw; // pw
    private String name; // 이름
    private String birth; // 생년월일 ( ex 970128 )
    private String gender; // 성별 ( ex M or F )
    private String phoneNumber; // 전화번호 ( ex 010-6277-0650 )
    private String e_mail; // e_mail ( ex colorful8315@naver.com )
    private String address; // 주소 ( ex 서울시 구로구 오류동 )

}
