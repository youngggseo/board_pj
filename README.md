# 💡 게시판 프로젝트 💡

### 개발 환경 ⚒️
- JAVA 11
- Framework : SpringBoot 2.7.9
- Build : Gradle 7.6.1
- Database : MySQL 8.0

### 라이브러리 📁
- Spring Boot Web
- Lombok
- Spring Data Jpa, MySQL
- Spring Security
- Spring Boot DevTools
- Thymeleaf
- Validation
- thymeleaf-extras-springsecurity5

### 기능 📝

#### 회원가입
- 회원가입 성공 시 성공 메세지 출력 후 로그인 화면으로 redirect
- 로그인 한 유저는 회원가입 페이지에 접근할 수 없음
- 아이디, 닉네임이 중복되거나 비밀번호, 비밀번호 확인이 일치하지 않으면 회원가입 불가
- 비밀번호는 암호화해서 저장
  
#### 로그인
- 아이디(loginId), 비밀번호로 로그인
- 로그인 성공시 성공 메세지 출력 후 이전 페이지로 redirect
- 만약 이전 페이지가 없거나 회원가입 페이지라면 홈으로 redirect
- 로그인 한 유저는 로그인 페이지에 접근할 수 없음
- Spring Security에서 로그인 진행

#### 마이 페이지
- 로그인하지 않은 유저는 접근할 수 없음
- 로그인 한 유저의 정보 확인 가능
- category에 따라 내가 작성한 글(board) 리스트 확인 가능

#### 게시판
- 가입인사(GREETING), 자유게시판(FREE) 게시판
- 해당 카테고리의 글 리스트 출력
- 글 작성 버튼 클릭 시 해당 카테고리에 글을 작성할 수 있는 글 작성 페이지로 이동
- 글 클릭 시 해당 글 조회 페이지로 이동
- 한 페이지에 10개의 글 출력 (공지 제외)
- 로그인 한 유저만 접근 가능
- 제목, 내용 작성 가능
- 글 작성 성공 시 작성된 글 조회 페이지로 redirect
- 입력된 제목(title), 내용(body)로 글 저장
- 등록 시 작성일, 작성 유저도 같이 저장
- 로그인 한 유저만 작성 가능
- boardId에 해당하는 글 내용을 화면에 출력
