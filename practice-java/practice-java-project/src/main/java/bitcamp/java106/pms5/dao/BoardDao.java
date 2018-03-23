package bitcamp.java106.pms5.dao;

import bitcamp.java106.pms3.domain.Board;

public class BoardDao {
    Board[] boards = new Board[1000];
    int bCount = 0;
    
    // 게시글 등록 데이터 처리
    public void insert(Board board) {
        board.no = bCount;
        this.boards[this.bCount++] = board;
    }
    
    // 출력할 데이터 추출
    public Board[] list() {
        Board[] arr = new Board[bCount];
        for(int i = 0; i < bCount; i++)
            arr[i] = boards[i];
        return arr;
    }
    
    
    // 게시글 번호로 원하는 게시글 찾기찾기
    public Board get(int i) {     
        if(i < 0 || i >= this.bCount)
            return null;
        return boards[i];
    }
    
    
    // 게시글 번호로 원하는 게시글 변경하기
    public void update(Board board) {
        this.boards[board.no] = board;
    }
    
    public void delete(int i) {
        this.boards[i] = null;
    }
}

// ver 14 - BoardController로부터 데이터 관리 기능을 분리하여 BoardDao 생성
