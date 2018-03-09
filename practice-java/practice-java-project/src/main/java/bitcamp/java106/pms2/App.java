package bitcamp.java106.pms2;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

import bitcamp.java106.pms2.domain.Team;
import bitcamp.java106.pms2.domain.Member;

public class App {
    public static void main (String[] args) {
        Scanner keyScan = new Scanner(System.in);
        
        Team[] team = new Team[100]; // 레퍼런스 변수 생성
        Member[] mem = new Member[100];
        int tCount = 0; // 팀이 등록된 수
        int mCount = 0; // 회원이 등록된 수

        for(int i = 0; i < team.length; i++) {
            team[i] = new Team(); // 인스턴스 변수 생성
            mem[i] = new Member();
        }

        while(true) {
            System.out.print("명령> ");
            String order = keyScan.nextLine(); // 명령을 입력받는다.
            if(order.equals("help")) { // 명령어 목록 출력
                System.out.println("팀 등록 명령 : team/add");
                System.out.println("팀 조회 명령 : team/list");
                System.out.println("팀 상세조회 명령 : team/view 팀명");
                System.out.println("회원 등록 명령 : member/add");
                System.out.println("회원 조회 명령 : member/list");
                System.out.println("회원 상세조회 명령 : member/view 아이디");
                System.out.println("종료 : quit");
            }

            else if(order.equals("team/add")) { // 팀 등록 명령
                System.out.print("팀명? ");
                team[tCount].name = keyScan.nextLine();
                System.out.print("설명? ");
                team[tCount].description = keyScan.nextLine();
                System.out.print("최대인원? ");
                team[tCount].maxQty = keyScan.nextInt();
                keyScan.nextLine(); // 개행문자 제거
                System.out.print("시작일? ");
                team[tCount].startDate = keyScan.nextLine();
                System.out.print("종료일? ");
                team[tCount].endDate = keyScan.nextLine();
                ++tCount; // 팀 등록인원 +1 증가
            }
            
            else if(order.equals("team/list")) { // 팀 조회 명령
                for(int i = 0; i < tCount; i++) {
                    System.out.printf("%s, %d, %s ~ %s\n",
                        team[i].name,
                        team[i].maxQty,
                        team[i].startDate,
                        team[i].endDate);
                }
            }
            
            else if(order.contains("team/view") == true) { // 팀 상세조회 명령
                String token = order.substring(10);

                // token == null 안하면 exception 오류남
                if(token == null || token.equals("")) {
                    System.out.println("팀명을 입력하시기 바랍니다.");
                    continue; // 아래의 명령은 실행하지 않고 다시 "명령>"을 묻는다.
                }
               
                for(int i = 0; i < tCount; i++) {
                    if(team[i].name.equals(token)) {
                        System.out.printf("팀명: %s\n", team[i].name);
                        System.out.printf("설명: %s\n", team[i].description);
                        System.out.printf("최대인원: %d\n", team[i].maxQty);
                        System.out.printf("기간: %s ~ %s\n", 
                            team[i].startDate, team[i].endDate);
                        break; // 밑의 if가 실행되는 경우를 막기 위해서
                    }
                    else if(i == tCount-1)
                        System.out.println("해당 이름의 팀이 없습니다.");
                }
            }
            
            else if(order.equals("member/add")) { // 회원 등록 명령
                System.out.print("아이디? ");
                mem[mCount].id = keyScan.nextLine();
                System.out.print("이메일? ");
                mem[mCount].email = keyScan.nextLine();
                System.out.print("암호? ");
                mem[mCount].pwd = keyScan.nextLine();
                ++mCount;
            }
            
            else if(order.equals("member/list")) { // 회원 조회 명령
                for(int i = 0; i < mCount; i++) {
                    System.out.printf("%s, %s, %s\n",
                        mem[i].id,
                        mem[i].email,
                        mem[i].pwd);
                }
            }
            
            else if(order.contains("member/view") == true) { // 회원 상세조회 명령
                String token = order.substring(12);

                if(token == null || token.equals("")) {
                    System.out.println("팀명을 입력하시기 바랍니다.");
                    continue;
                }

                for(int i = 0; i < mCount; i++) {
                    if(token.equals(mem[i].id)) {
                        System.out.printf("아이디: %s\n", mem[i].id);
                        System.out.printf("이메일: %s\n", mem[i].email);
                        System.out.printf("암호: %s\n", mem[i].pwd);
                        break;
                    }
                    else if(i == mCount-1)
                        System.out.println("해당 이름의 팀이 없습니다."); 
                }
            }  

            else if(order.equals("quit")) {
                System.out.println("안녕히가세요!");
                break; // 종료
            }

            else 
                System.out.println("명령어가 올바르지 않습니다.");
        }
    }
}