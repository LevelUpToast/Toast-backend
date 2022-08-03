
<img width="176" alt="스크린샷 2022-07-28 오후 7 11 22" src="https://user-images.githubusercontent.com/81874493/181481382-d7fa4aa4-4f37-433d-8d39-27e856dcd0e6.png">

# LevelUpToast Backend Notify


 <img src="https://img.shields.io/badge/Spring boot-6DB33F?style=flat&logo=Spring Boot&logoColor=white" alt="spring boot"/>
 <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat&logo=IntelliJ IDEA&logoColor=white" alt="spring boot"/>
 <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=flat&logo=Apache Tomcat&logoColor=white" alt="spring boot"/>
 <img src="https://img.shields.io/badge/Rocky Linux-10B981?style=flat&logo=Rocky Linux&logoColor=white" alt="spring boot"/>
 <img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white" alt="spring boot"/>

## 팀원소개

---
+ 임성묵 (https://github.com/mookseong)
+ 김지용 (https://github.com/JiYongKim-A)

## 🍞 어떤 프로젝트 인가요?

---

- LevelUpToast는 제품을 사고 파는 도소매 상황에서 중간에 발생하던 유통 과정을 단축하는 방법을 통해 소비자는 조금더 낮은 가격으로 산지유통업자는 조금 더 높은 가격으로 판매를 유도하여 직거래 방식과 같이 소비자와 산지 유통 업자와 연결 해주는 애플리케이션을 진행하는 프로젝트 이다.

<br>

### 📃 유스케이스 다이어그램

---

<img width="649" alt="스크린샷 2022-07-28 오후 7 11 35" src="https://user-images.githubusercontent.com/81874493/181481479-bf07d3e1-1b94-4532-8a2e-ed304c296fbc.png">

<br>

## 💻 프로젝트 진행 및 관리

---

### 프로젝트 진행 방법

- 프로젝트에서의 업무는 직급과 상관 없이 요청할건 요청하고 인원별 롤 대로 나눠서 업무를 진행 한다.
- 구두로 협의한 내용이나 업무적으로 협의한 내용이 있을 시 **작은 내용이더라도 모두 노션으로 일일히 정리해관리 한다.**
- 업무 협의 시 일정에 맞게 진행을 해야한다. 만약 일정이 늦어지거나 문제가 발생 시 바로바로 프로젝트 관련인원들에게 공유하여 해결 및 조율 한다.
- 함께 논의해야할 사항이 생길 경우 노션에서 논의 사항을 작성하여 함께 고민하여 해결한다.
- <U>자신이 작성한 코드가 아닌 다른 코드에서 문제점이 발견 되었을 경우 직접 수정이 아닌 **요청**을 통해 수정한다. </U> 
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

### 📋 System Architecture

---

<img width="558" alt="스크린샷 2022-07-28 오후 7 11 41" src="https://user-images.githubusercontent.com/81874493/181481625-b0ea330b-f893-42a7-bc4e-e8533c29ac06.png">

<br>

## 💼 Rest API 기본 프레임 형식

---

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

## ⚠️ 문제점 및 해결 방법

---

[프로젝트 개발 중 문제점 발견시 명시하고 이에 대한 해결 방법을 명시한다.]

ex)

- [x]  서버 통신간 CORS(Cross-Origin Resource Sharing)문제 (2022.7.30 김지용)

    <details>
    <summary>문제점 & 해결 방법</summary>
       
        문제점 : CORS 정책 위반하여 서로 다른 출처를 가진 상태에서 요청시 브라우저가 보안상 이유로 차단
        
        해결방법 : 동일 출처에서 리소스 요청 방식을 사용
       
     </details>

<br>

## 📔 개발 관련 참고 문서

---

[개발 중 도움이 되었던 문서가 있을 시 이에 대한 참고 문서를 하이퍼링크 형태로 명시한다.]

ex) [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)

<br>
