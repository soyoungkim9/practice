package bitcamp.java106.pms6.dao;

import bitcamp.java106.pms6.domain.Task;
import bitcamp.java106.pms6.domain.Team;

public class TaskDao {
    Task[] tasks = new Task[1000];
    int tsCount = 0;
    
    // 팀작업 등록 데이터 처리
    public void insert(Task task) {
        task.setNo(tsCount);
        this.tasks[this.tsCount++] = task;
    }
    
    private int count(String teamName) {
        int cnt = 0;
        for(int i = 0; i < tsCount; i++) {
            if(tasks[i] == null) continue;
            if(tasks[i].getTeam().getName().toLowerCase().equals(teamName)) {
                cnt++;
            }
        }
        return cnt;
    }
    
    // 원하는 팀명의 모든 작업을 출력한다.
    public Task[] list(String teamName) {
        Task[] arr = new Task[this.count(teamName)];
        for(int i = 0, x = 0; i < tsCount; i++) {
            if(tasks[i] == null) continue;
            if(tasks[i].getTeam().getName().toLowerCase().equals(teamName)) {
                arr[x++] = tasks[i];
            }
        }
        return arr;
    }
    
    
    // 작업번호로 원하는 작업 찾기
    public Task get(String teamName, int taskNo) {     
        for(int i = 0, x = 0; i < tsCount; i++) {
            if(tasks[i] == null) continue;
            if(tasks[i].getTeam().getName().toLowerCase().equals(teamName)
                    && tasks[i].getNo() == taskNo) {
                return tasks[i];
            }
        }
        return null;
    }
    
    
    // 게시글 번호로 원하는 게시글 변경하기
    public void update(Task task) {
        this.tasks[task.getNo()] = task;
    }
    
    public void delete(int i) {
        this.tasks[i] = null;
    }
    
    
}

// ver 14 - TaskController로부터 데이터 관리 기능을 분리하여 TaskDao 생성
