*** 게시판 삭제 정책 추가 (계층 구조 상의 상위 글 삭제시 모든 하위 게시글의 참조 구조를 삭제하는 경우 활용)
1. FreeBoard_delete_procedure.sql 의 프로시저 및 커스텀 타입 반영
2. BoardDAO 및 BoardService 의 삭제 관련 메소드 변경 참고

*** 덧글 처리 관련 변경 사항.
1. ReplyVO 프로퍼티 추가됨.
2. Reply.xml 수정 - 덧글 계층구조 및 수정/삭제 반영
3. ReplyServiceImpl - spring security password encoder 주입 사용 방식 변경.

*** 거래처 관리 기능 추가됨.

*** 데이터베이스 기반 접근 제어 구조 (spring-security.pdf 파일 참고)
1. 보호 자원 및 역할에 관한 테이블 추가(SECURITY-SCHEMA.sql 참고) - 사용자 역할 부여 1:N 구조 반영.
												   - 권한 계층 구조(role_hierarchy)
2. security 패키지에 필요 객체 정의
3. security-context.xml 수정 - access decision manager 설정 추가
						   - interceptor-url 제거 -> DB 기반의 접근 제어 설정
4. 기 설정된 접근 제어 구조를 변경할 수 있는 모듈 추가 (security/**) - 자원 설정 권한 및 사용자 역할 변경을 위한 layered 구조 추가(security.auth 패키지 참고) 
						   	
