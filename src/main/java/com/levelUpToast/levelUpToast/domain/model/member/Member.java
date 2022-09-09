package com.levelUpToast.levelUpToast.domain.model.member;

import lombok.Data;


@Data
public class Member {
    public Member(String id, String password, Authority authority, String name, String gender, String phoneNumber, String e_mail, String address) {
        this.id = id;
        this.password = password;
        this.authority = authority;
        this.name = name;
//        this.birth = birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.e_mail = e_mail;
        this.address = address;
    }

    private Long manageSeq; // 회원 고유 번호

    private String token; // 회원 확인용 토큰

    private String id; // 회원 ID

    private String password; // 회원 password

    private Authority authority; // 회원 or 관리자 권한

    private String name; // 회원 이름

//    private String birth; // 회원 생년월일

    private String gender; // 회원 성별

    private String phoneNumber; // 회원 전화번호

    private String e_mail; // 회원 이메일

    private String address; // 회원 주소

    // toastWallet

    // myFunding products

    // my wish products

    private Boolean Lock; // 회원 계정 활성/비활성



}
