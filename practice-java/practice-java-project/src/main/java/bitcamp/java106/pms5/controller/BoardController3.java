// 컨트롤러가 작업하는데 필요한 객체를 반드시 준비하도록 생성자를 추가한다.
// => 생성자를 통해 필수 입력 값을 반드시 설정하도록 강제시킬 수 있다.
// => 즉 생성자란, 객체를 사용하기 전에 유효한 값으로 설정하게 만드는 문법이다.
package bitcamp.java106.pms5.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms3.dao.BoardDao;
import bitcamp.java106.pms3.domain.Board;
import bitcamp.java106.pms3.util.Console;

public class BoardController3 {
    
    Scanner keyScan;
    
    BoardDao boardDao = new BoardDao();
      
    public BoardController3(Scanner scanner) {
        this.keyScan = scanner;
    }
    // board만 처리하는 함수
    public void service(String menu, String option) {
        if(menu.equals("board/add")) {
            this.onBoardAdd();
        } else if(menu.equals("board/list")) {
            this.onBoardList();
        } else if(menu.equals("board/view")) {
            this.onBoardView(option);
        } else if(menu.equals("board/update")) {
            this.onBoardUpdate(option);
        } else if(menu.equals("board/delete")) {
            this.onBoardDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    /*
    // 게시글 방번호 찾는 메서드
    int onBoardIndex(int option) {
        for(int i = 0; i < this.bCount; i++) {
            if(this.boards[i] == null) continue;
            if(this.boards[option].subject == this.boards[i].subject) {
                return i;
            }
        } 
        return -1; 
    }*/

    // 게시글 등록 메서드
    void onBoardAdd() {
        Board board = new Board(); // 인스턴스 생성
                                // add명령어 실행할 때 마다 
                                // team은 새로운 인스턴스가 생성된다.
        System.out.println("[게시글 등록]");
        System.out.print("제목? ");
        board.subject = this.keyScan.nextLine();
        System.out.print("내용? ");
        board.content = this.keyScan.nextLine();
        System.out.print("등록일? ");
        board.date = Date.valueOf(this.keyScan.nextLine());

        // this.boards[this.bCount++] = board;
        boardDao.insert(board);
    }
    
    // 모든 게시글 출력 메서드
    void onBoardList() {
        System.out.println("[게시글 목록]");
        Board[] board = boardDao.list();
        
        for(int i = 0; i < board.length; i++) {
            if(board[i] == null) continue;
            System.out.printf("%d, %s, %s\n",
                i, board[i].subject, board[i].date);
        }

    }

    // 게시글 조회 메서드
    void onBoardView(String option) {
        System.out.println("[게시글 조회]");
        if(option == null) {
            System.out.println("게시물번호를 입력하시기 바랍니다.");
            return;
        }

        Board board = boardDao.get(Integer.parseInt(option));

        if(board == null) {
            System.out.println("해당 번호의 게시물이 없습니다.");

        } else {
            System.out.printf("제목 : %s\n", board.subject);
            System.out.printf("내용 : %s\n", board.content);
            System.out.printf("등록일 : %s\n", board.date);
        }
    }

    // 게시글 업데이트 메서드
    void onBoardUpdate(String option) {
        System.out.println("[게시글 변경]");
        if(option == null) {
            System.out.println("게시물번호를 입력하시기 바랍니다.");
            return;
        }

        int i = Integer.parseInt(option);
        Board board = boardDao.get(i);
        
        if(board == null) {
            System.out.println("해당 번호의 게시물이 없습니다.");
        } else {
            Board boardUpdate = new Board();
            System.out.printf("제목(%s): ", board.subject);
            boardUpdate.subject = keyScan.nextLine();
            System.out.printf("내용(%s): ", board.content);
            boardUpdate.content = keyScan.nextLine();
            boardUpdate.date = board.date;
            boardUpdate.no = board.no;
            boardDao.update(boardUpdate);
            System.out.println("변경하였습니다.");
            
        }
    }

    // 게시물을 삭제하는 메서드
    void onBoardDelete(String option) {
        if(option == null) {
            System.out.println("게시물번호를 입력하시기 바랍니다.");
            return;
        }

        int i = Integer.parseInt(option);
        Board board = boardDao.get(i);

        if(board == null) {
            System.out.println("해당 번호의 게시물이 없습니다.");
        } else {            
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                boardDao.delete(i);
                System.out.println("삭제하였습니다.");
            }                   
        }
    }
}