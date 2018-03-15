# 자바 프로젝트 

## src06 - 유지보수 경험
- 학습목표
  - 기존의 코드 규칙에 따라 새 기능을 추가하는 훈련을 한다.
- 작업내용
  - 팀 정보 변경 및 삭제 기능을 추가한다.
  - 회원 정보 변경 및 삭제 기능을 추가한다.
- 실행 결과
```
> java -classpath bin bitcamp.java106.pms.App
명령> team/update okok
해당 이름의 팀이 없습니다.

명령> team/update aaa
팀명(aaa)? okok
설명(aaaaaaa)? okokok
최대인원(5)? 10
시작일(2018-5-5)? 2018-5-10
종료일(2018-8-8)? 2018-8-8
변경하였습니다.

명령> team/delete okok
해당 이름의 팀이 없습니다.

명령> team/delete aaa
정말 삭제하시겠습니까?(Y/n) y
삭제하였습니다.

명령> team/delete aaa
정말 삭제하시겠습니까?(Y/n) n

명령>

// 나머지 회원 변경과 삭제가 동일하다.

```