package bitcamp.java106.pms4.controller;

import java.util.Scanner;
import bitcamp.java106.pms3.domain.Board;
import bitcamp.java106.pms3.util.Console;

public class BoardController {
    
    public static Scanner keyScan;

    static Board[] boards = new Board[1000]; // 레퍼런스 생성
    static int bCount = 0; // boards 등록 수 

    // board만 처리하는 함수
    public static void service(String menu, String option) {
        if(menu.equals("board/add")) {
            onBoardAdd();
        } else if(menu.equals("board/list")) {
            onBoardList();
        } else if(menu.equals("board/view")) {
            onBoardView(option);
        } else if(menu.equals("board/update")) {
            onBoardUpdate(option);
        } else if(menu.equals("board/delete")) {
            onBoardDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    
    // 게시글 방번호 찾는 메서드
    static int onBoardIndex(int option) {
        for(int i = 0; i < bCount; i++) {
            if(boards[i] == null) continue;
            if(boards[option].subject == boards[i].subject) {
                return i;
            }
        } 
        return -1; 
    }

    // 게시글 등록 메서드
    static void onBoardAdd() {
        Board board = new Board(); // 인스턴스 생성
                                // add명령어 실행할 때 마다 
                                // team은 새로운 인스턴스가 생성된다.
        System.out.println("[게시글 등록]");
        System.out.print("제목? ");
        board.subject = keyScan.nextLine();
        System.out.print("내용? ");
        board.content = keyScan.nextLine();
        System.out.print("등록일? ");
        board.date = keyScan.nextLine();

        boards[bCount++] = board;
    }
    
    // 모든 게시글 출력 메서드
    static void onBoardList() {
        System.out.println("[게시글 목록]");
        for(int i = 0; i < bCount; i++) {
            if(boards[i] == null) continue;
            System.out.printf("%d, %s, %s\n",
                i,
                boards[i].subject,
                boards[i].date);
        }

    }

    // 게시글 조회 메서드
    static void onBoardView(String option) {
        System.out.println("[게시글 조회]");
        if(option == null) {
            System.out.println("게시물번호를 입력하시기 바랍니다.");
            return;
        }

        int iOption = Integer.parseInt(option); // 옵션을 숫자로 변환
        int i = onBoardIndex(iOption);

        if(i < 0 || bCount <= i) {
            System.out.println("해당 번호의 게시물이 없습니다.");

        } else { 
            System.out.printf("제목 : %s\n", boards[i].subject);
            System.out.printf("내용 : %s\n", boards[i].content);
            System.out.printf("등록일 : %s\n", boards[i].date);
        }
    }

    // 게시글 업데이트 메서드
    static void onBoardUpdate(String option) {
        System.out.println("[게시글 변경]");
        if(option == null) {
            System.out.println("게시물번호를 입력하시기 바랍니다.");
            return;
        }

        int iOption = Integer.parseInt(option); // 옵션을 숫자로 변환
        int i = onBoardIndex(iOption);

        if(i < 0 || bCount <= i) {
            System.out.println("해당 번호의 게시물이 없습니다.");
        } else {
            Board boardUpdate = new Board();
            System.out.printf("제목(%s): ", boards[i].subject);
            boardUpdate.subject = keyScan.nextLine();
            System.out.printf("내용(%s): ", boards[i].content);
            boardUpdate.content = keyScan.nextLine();
            boardUpdate.date = boards[i].date;
            System.out.println("변경하였습니다.");
            boards[i] = boardUpdate;
        }
    }

    // 게시물을 삭제하는 메서드
    static void onBoardDelete(String option) {
        if(option == null) {
            System.out.println("게시물번호를 입력하시기 바랍니다.");
            return;
        }

        int iOption = Integer.parseInt(option); // 옵션을 숫자로 변환
        int i = onBoardIndex(iOption);


        if(i < 0 || bCount <= i) {
            System.out.println("해당 번호의 게시물이 없습니다.");
        } else {            
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                boards[i] = null;
                System.out.println("삭제하였습니다.");
            }                   
        }
    }
}