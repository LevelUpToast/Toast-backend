
<div align="center">
  
<img width="15%" alt="LevelUptoast" src="https://user-images.githubusercontent.com/20126358/185047884-1b199d5c-163a-495d-80e6-124dfd9bf3af.png">

	
# LevelUpToast Backend Notify	
 <img src="https://img.shields.io/badge/Spring boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white" alt="spring boot"/> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat&logo=IntelliJ IDEA&logoColor=white" alt="IntelliJ IDEA"/> <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white" alt="Apache Tomcat"/> <img src="https://img.shields.io/badge/Rocky Linux-10B981?style=flat&logo=Rocky Linux&logoColor=white" alt="Rocky Linux"/> <img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white" alt="Notion"/> <img src="https://img.shields.io/badge/Jenkins-D24939?style=flat&logo=Jenkins&logoColor=white" alt="Jenkins"/>

	
</div>

## 팀원소개
+ 임성묵 (https://github.com/mookseong)
+ 김지용 (https://github.com/JiYongKim-A)

## 🍞 어떤 프로젝트 인가요?

- LevelUpToast는 제품을 사고 파는 도소매 상황에서 중간에 발생하던 유통 과정을 단축하는 방법을 통해 소비자는 조금더 낮은 가격으로 산지유통업자는 조금 더 높은 가격으로 판매를 유도하여 직거래 방식과 같이 소비자와 산지 유통 업자와 연결 해주는 애플리케이션을 진행하는 프로젝트 이다.

<br>

### 📃 유스케이스 다이어그램

<img width="649" alt="유스케이스" src="https://user-images.githubusercontent.com/81874493/181481479-bf07d3e1-1b94-4532-8a2e-ed304c296fbc.png">

<br>

## 💻 프로젝트 진행 및 관리

### 프로젝트 진행 방법

- 프로젝트에서의 업무는 직급과 상관 없이 요청할건 요청하고 인원별 롤 대로 나눠서 업무를 진행 한다.
- 구두로 협의한 내용이나 업무적으로 협의한 내용이 있을 시 **작은 내용이더라도 모두 노션으로 일일히 정리해관리 한다.**
- 업무 협의 시 일정에 맞게 진행을 해야한다. 만약 일정이 늦어지거나 문제가 발생 시 바로바로 프로젝트 관련인원들에게 공유하여 해결 및 조율 한다.
- 함께 논의해야할 사항이 생길 경우 노션에서 논의 사항을 작성하여 함께 고민하여 해결한다.
- <U>자신이 작성한 코드가 아닌 다른 코드에서 문제점이 발견되었을 경우 직접 수정이 아닌 **요청**을 통해 수정한다. </U> 
<br>

### GitHub 관리 규칙

- Commit
    - Commit 규칙을 통해 각 branch 별 commit 사항을 관리한다.
        - 커밋은 기능 '완성' 단위로  수정만했다고 커밋하는게 아니라  **최소 함수나 클래스 구현한 단위로 커밋한다.**

    <br>
    
    - commit message
        - 형식
            
            ```markdown
            <type>(<scope>): <subject>          
            
            <BLANK LINE>
            
            <body>
            ```
            
            <br>
            
        - type
            - feat : 새로운 기능에 대한 커밋
            - fix : 버그 수정에 대한 커밋
            - build : 빌드 관련 파일 수정에 대한 커밋
            - chore : 그 외 자잘한 수정에 대한 커밋
            - ci : CI관련 설정 수정에 대한 커밋
            - docs : 문서 수정에 대한 커밋
            - style : 코드 스타일 혹은 포맷 등에 관한 커밋
            - refactor :  코드 리팩토링에 대한 커밋
            - test : 테스트 코드 수정에 대한 커밋
        
        <br>
        
        ex) commit message example
        
        `refactor (LoginService) : id, pw matching system change`

### REST API 기본 규칙
 - URI는 정보의 자원을 표현해야 한다.
 - resource는 동사보다는 명사를, 소문자를 사용한다.
 - resource의 스토어 이름으로는 복수 명사를 사용해야 한다.
 - 확장자를 사용하지 않는다.
 - 밑줄( _ ) 을 사용하지 않고 하이픈을( - ) 사용한다.
 - 자원에 대한 행위는 HTTP Method(GET, POST, PUT, DELETE 등)으로 표현한다.
 - HTTP Method나 동사표현이 URI에 들어가면 안됩니다.
 - id와 같이 변하는 값은 하나의 특정 resource를 나타내는 고유값이어야 한다.
### REST API 설계 예시
  - 전체조회 @GetMapping(/member)
  - 특정조회 @GetMapping(/member/{id})
  - 등록 @PostMapping(/member)
  - 삭제 @DeleteMappiong(/member/{id})
  - 수정 @PutMapping(/member/{id})



<br>

- Code review
    - 프로젝트 코드 변경사항이 있을 시 각 branch를 통해 Pull Request를 작성한다.
        - reviewr로 함께 프로젝트를 진행하는 인원을 태그한다.
        - 코드 변경 진행 중 일시 `WIP` 태그를 통해  코드 리뷰를 미룰 수 있다.
    
    <br>
    
    - Pull Request는 코드 리뷰를 통해 Comment를 남기고 approve 받은 이후 merge 시킨다.
        - 논의 사항이 있을 경우 카카오톡 혹은 노션을 통해 공지하고 논의 하여 해결하고 해결 한다.
            - 문제 완료 사항은 정리하여 LevelUpToast Backend Notify 문제점 및 해결사항에 작성한다.

<br>

## 📋 System Architecture

<br>

![System Architecture](https://user-images.githubusercontent.com/20126358/184833298-56956261-d175-43fb-8228-20d2920fea53.jpeg)

<br>

## 💼 Rest API 기본 프레임 형식

### Request Data Frame

```json
URL : /
Method : Post or Get or Put
Body : Json {}
```

<br>

### Response Data Frame

```json
Body : 
	json {
		"detailCode" : " ",
		"message" : " ",
		"data" : " "
	}
```

<br>

## 📦 DB Table 설계도


<img width="1268" alt="DB 명세서" src="https://user-images.githubusercontent.com/81874493/189471591-f8a1e658-bdec-47e6-a805-d7994fdd2c8c.png">



<br>


## ⚠️ 문제점 및 해결 방법

[프로젝트 개발 중 문제점 발견시 명시하고 이에 대한 해결 방법을 명시한다.]

ex)

> - [x]  서버 통신간 CORS(Cross-Origin Resource Sharing)문제 (2022.7.30 김지용)
>
>    <details>
>   <summary>문제점 & 해결 방법</summary>
>      
>       문제점 : CORS 정책 위반하여 서로 다른 출처를 가진 상태에서 요청시 브라우저가 보안상 이유로 차단
>       
>       해결방법 : 동일 출처에서 리소스 요청 방식을 사용
>
>    </details>

<br>

- [x] email 인증 기능의 Google 보안 수준이 낮은 앱 설정 불가 (2022.8.22 김지용, 임성묵)
	
	+ 문제점 : email 관련 gmail smtp 서버를 이용하여 하려고 했으나 gmail 보안 수준이 낮은 앱 허용을  22년 5월 30일 부터 구글에서 설정을 막아 email smtp 사용 현재 불가능
	
	+ 해결방법 : 자체 leveluptoast 이메일 서버를 구축하고, 이메일 발송과정에서 SPF, DKIM, DMARC 정책 적용하여 보안설정

<br>


- [x] application.properties 보안설정 문제  (2022.9.1 임성묵)
	
	+ 문제점 : GitHub와 같은 Git에서 Public으로 되어 있다면, API 서버 내부 구조 보안, 아이디, 비밀번호를 보고 이메일이나 다른 서비스에 접속하여 보안문제나, 스팸으로 사용 가능성이 높음
	
	+ 해결방법 : Github Actions을 이용하여 하는 방법이 있지만, Jenkins 서버에서 CI/CD 과정에서 application.properties 키를 입력하여 외부로 부터 보안 유지하며, 변경사항이 있을시에도 원격으로 바로 변경할 수 있도록 수정

<br>


- [x] 검색 내용 추출, 검색 엔진 문제  (2022.9.10 임성묵)
	
	+ 문제점 : Elasticsearch을 통해 검색 엔진을 만드려고했으나 개발 적용시간이 상당히 소요될것으로 예상되고, 자연어 처리하는 Python 코드를 실행하여 검색엔진을 만들기엔 처리 속도가 느려짐 
	
	+ 해결방법 : KOMORAN 라이브러리로 한국어 형태소 분석하여 명사 추출하고, 내용을 간단하게 분석하여 정확도가 높은 내용을 Repository에 요청하여 필요한 데이터만 뽑을 수있는 기능으로 개발

<br>

## 📔 개발 관련 참고 문서

[개발 중 도움이 되었던 문서가 있을 시 이에 대한 참고 문서를 하이퍼링크 형태로 명시한다.]

ex) [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)

<br>

[Exception 만들기](https://devbox.tistory.com/entry/Java-%EC%98%88%EC%99%B8-%EB%A7%8C%EB%93%A4%EA%B8%B0)

[KOMORAN 라이브러리](https://www.shineware.co.kr/)
