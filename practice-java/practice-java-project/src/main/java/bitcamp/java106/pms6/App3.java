package bitcamp.java106.pms6;

import java.text.ParseException;
import java.util.Scanner;

import bitcamp.java106.pms6.controller.BoardController3;
import bitcamp.java106.pms6.controller.MemberController;
import bitcamp.java106.pms6.controller.TaskController;
import bitcamp.java106.pms6.controller.TeamController;
import bitcamp.java106.pms6.controller.TeamMemberController;
import bitcamp.java106.pms6.dao.MemberDao;
import bitcamp.java106.pms6.dao.TaskDao;
import bitcamp.java106.pms6.dao.TeamDao;
import bitcamp.java106.pms6.util.Console;

public class App3 {

    static Scanner keyScan = new Scanner(System.in);
    static String option = null; // 명령어 뒤의 문자열

    // 종료인사
    static void onQuit() {
        System.out.println("안녕히가세요!");
    }
    
    // 도움말을 출력하는 메서드
    static void onHelp() {
        System.out.println("[도움말 제공]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
    }

    

    public static void main(String[] args) throws ParseException {
        TeamDao teamDao = new TeamDao();
        MemberDao memberDao = new MemberDao();
        TaskDao taskDao = new TaskDao();
        TeamController teamController = new TeamController(keyScan, teamDao);
        MemberController memberController = new MemberController(keyScan, memberDao);
        BoardController3 boardController = new BoardController3(keyScan);
        TeamMemberController teamMemberController = new TeamMemberController(keyScan, teamDao, memberDao);
        TaskController taskController = new TaskController(keyScan, taskDao, teamDao);
        Console.keyScan = keyScan;

        
        while(true) {
            
            String[] arr = Console.prompt();
            String menu = arr[0];

            if(arr.length == 2) { // 명령어 이외의 값이 있다면 다음 배열에 저장
                option = arr[1];
            } else {
                option = null;
            }
            
            if(menu.equals("quit")) {
                onQuit();
                break;
            } else if(menu.startsWith("team/member/")) {
                teamMemberController.service(menu, option);
            } else if(menu.equals("help")) {
                onHelp();
            } else if(menu.startsWith("team/")) {
                teamController.service(menu, option);
            } else if(menu.startsWith("member/")) {
                memberController.service(menu, option);
            } else if(menu.startsWith("board/")) {
                boardController.service(menu, option);
            } else if(menu.startsWith("task/")) {
                taskController.service(menu, option);
            } else {
                System.out.println("명령어가 올바르지 않습니다.");
            }

            System.out.println();
        }
    }
}