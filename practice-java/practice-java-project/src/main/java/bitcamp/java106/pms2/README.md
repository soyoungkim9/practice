## src04 - 문법의 활용
- 학습목표
  - 변수, 상수, 연산자, 조건문/반복문(일부), 클래스 문법의 활용을 통해 프로그래밍을 익숙하게 한다.
- 작업내용
  - 사용자로부터 팀 또는 회원 정보를 입력 받아 저장한다.
  - 사용자의 명령에 따라 팀 또는 회원 정보를 출력한다.
- 실행결과
```
> java -classpath bin bitcamp.java106.pms.App
명령> (사용자을 입력을 기다리고 있어야 한다.)
명령> help
팀 등록 명령 : team/add
팀 조회 명령 : team/list
팀 상세조회 명령 : team/view 팀명
회원 등록 명령 : member/add
회원 조회 명령 : member/list
회원 상세조회 명령 : member/view 아이디
종료 : quit

명령> team/add
팀명? 비트비트
설명? 자바 프로젝트 팀
최대인원? 5
시작일? 2018-05-05
종료일? 2018-08-20

명령> team/add
팀명? 비트비트2
설명? 자바 프로젝트 팀2
최대인원? 5
시작일? 2018-05-05
종료일? 2018-08-20

명령> team/list
비트비트, 5, 2018-05-05 ~ 2018-08-20
비트비트2, 5, 2018-05-05 ~ 2018-08-20

명령> team/view 비트비트2
팀명: 비트비트2
설명: 자바 프로젝트 팀2
최대인원: 5
기간: 2018-05-05 ~ 2018-08-20

명령> team/view 비트오케이
해당 이름의 팀이 없습니다.

명령> team/view
팀명을 입력하시기 바랍니다.

명령> member/add
아이디? hong
이메일? hong@test.com
암호? 1111

명령> member/add
아이디? leem
이메일? leem@test.com
암호? 1111

명령> member/list
hong, hong@test.com, 1111
leem, leem@test.com, 1111

명령> member/view leem
아이디: leem
이메일: leem@test.com
암호: 1111

명령> member/view okok
해당 아이디의 회원이 없습니다.

명령> member/view 
아이디를 입력하시기 바랍니다.

명령> memeber/list
명령어가 올바르지 않습니다.

명령> quit
안녕히가세요!

>
```