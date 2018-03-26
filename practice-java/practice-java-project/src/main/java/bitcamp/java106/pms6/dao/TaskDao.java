package bitcamp.java106.pms6.dao;

import bitcamp.java106.pms6.domain.Task;

public class TaskDao {
    Task[] tasks = new Task[1000];
    int tCount = 0;
    
    // task 등록 데이터 처리
    public void insert(Task Task) {
        Task.setNo(tCount);
        this.tasks[this.tCount++] = Task;
    }
    
    // task 데이터 추출
    public Task[] list() {
        Task[] arr = new Task[tCount];
        for(int i = 0; i < tCount; i++)
            arr[i] = tasks[i];
        return arr;
    }
    
    
    // 게시글 번호로 원하는 게시글 찾기찾기
    public Task get(int i) {     
        if(i < 0 || i >= this.tCount)
            return null;
        return tasks[i];
    }
    
    
    // 게시글 번호로 원하는 게시글 변경하기
    public void update(Task Task) {
        this.tasks[Task.getNo()] = Task;
    }
    
    public void delete(int i) {
        this.tasks[i] = null;
    }
}

// ver 14 - TaskController로부터 데이터 관리 기능을 분리하여 TaskDao 생성
