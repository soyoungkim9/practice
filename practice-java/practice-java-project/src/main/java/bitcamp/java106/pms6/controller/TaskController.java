package bitcamp.java106.pms6.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms6.dao.TaskDao;
import bitcamp.java106.pms6.dao.TeamDao;
import bitcamp.java106.pms6.domain.Member;
import bitcamp.java106.pms6.domain.Task;
import bitcamp.java106.pms6.domain.Team;
import bitcamp.java106.pms6.util.Console;

public class TaskController {
    Scanner keyScan;
    TeamDao teamDao;
    TaskDao taskDao;

    public TaskController(Scanner scanner, TeamDao teamDao, TaskDao taskDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
        
        
    }

    public void service(String menu, String teamName) {
        if (menu.equals("task/add")) {
            this.onTaskAdd(teamName);
        } else if (menu.equals("task/list")) {
            this.onTaskList(teamName);
        } else if (menu.equals("task/view")) {
            this.onTaskView(teamName);
        } else if (menu.equals("task/update")) {
            this.onTaskUpdate(teamName);
        } else if (menu.equals("task/delete")) {
            this.onTaskDelete(teamName);
        } else if (menu.equals("task/state")) {
            this.onTaskState(teamName);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    
    
    // 팀 작업 등록 메서드
    private void onTaskAdd(String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(teamName);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", teamName);
            return;
        }     
        
        Task task = new Task(team);
        System.out.println("[팀 작업 추가]");
        System.out.print("작업명? ");
        task.setSubject(keyScan.nextLine());
        
        System.out.print("시작일? ");
        String strDate = keyScan.nextLine();
        if(strDate.length() == 0) {
            task.setStartDate(team.getStartDate());
        } else {
            Date date = Date.valueOf(strDate);
            if(date.getTime() < team.getStartDate().getTime()) {
                task.setStartDate(team.getStartDate());
            } else {
                task.setStartDate(date);
            }
        }
        
        System.out.print("종료일? ");
        strDate = keyScan.nextLine();
        if(strDate.length() == 0) {
            task.setEndDate(team.getEndDate());
        } else {
            Date date = Date.valueOf(strDate);
            if(date.getTime() > team.getEndDate().getTime()) {
                task.setEndDate(team.getEndDate());
            } else {
                task.setEndDate(date);
            }
        }
        
        System.out.print("작업자? ");
        String worker = keyScan.nextLine();
        if(worker.length() != 0) {
            Member member = team.getMember(worker);
            if (member == null) {
                System.out.printf("'%s'는 이 팀의 회원이 아닙니다. 작업자는 비워두겠습니다.", worker);
            } else {
                task.setWorker(member);
            }
         }
        
        taskDao.insert(task);
    }
    
    private void onTaskList(String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(teamName);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", teamName);
            return;
        }
        
        System.out.println("[팀 작업 목록]");
        Task[] tasks = taskDao.list(teamName);
        
        for(Task task : tasks) {
            System.out.printf("%d, %s, %s, %s, %s\n",
                    task.getNo(), task.getSubject(),
                    task.getStartDate(), task.getEndDate(),
                    (task.getWorker() == null) ? 
                            "-" : task.getWorker().getId());
        }
        System.out.println();
    }
    
    // 원하는 팀  작업 리스트 출력 메서드
    private void onTaskView(String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(teamName);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", teamName);
            return;
        }
        
        System.out.println("[팀 작업 정보]");
        System.out.print("작업번호? ");
        int taskNo = Integer.parseInt(keyScan.nextLine());
        Task task = taskDao.get(teamName, taskNo);
        System.out.printf("작업명: %s\n", task.getSubject());
        System.out.printf("시작일: %s\n", task.getStartDate());
        System.out.printf("종료일: %s\n", task.getEndDate());
        System.out.printf("작업자: %s\n", 
                (task.getWorker() == null) ? 
                        "-" : task.getWorker().getId());
        System.out.printf("작업상태: %s\n", getStateLabel(task.getState()));
    }
    
    // 원하는 팀  작업 리스트 출력 메서드
    private void onTaskUpdate(String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(teamName);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", teamName);
            return;
        }
        
        System.out.println("[팀 작업 변경]");
        System.out.print("변경할 작업의 번호? ");
        
        int taskNo = Integer.parseInt(keyScan.nextLine());
        Task originTask = taskDao.get(teamName, taskNo);
        
        Task task = new Task(team);
        task.setNo(originTask.getNo());
        System.out.printf("작업명(%s)? ", originTask.getSubject());
        String workName = keyScan.nextLine();
        if(workName.length() == 0) {
            task.setSubject(originTask.getSubject());
        } else {
            task.setSubject(workName);
        }
        
        System.out.printf("시작일(%s) ? ", originTask.getStartDate());
        String str = keyScan.nextLine();
        if(str.length() == 0) {
            task.setStartDate(originTask.getStartDate());
        } else {
            Date date = Date.valueOf(str);
            if(team.getStartDate().getTime() > date.getTime()) {
                task.setStartDate(team.getStartDate());
            } else {
                task.setStartDate(date);
            }
        }
        
        System.out.printf("종료일(%s) ? ", originTask.getEndDate());
        str = keyScan.nextLine();
        if(str.length() == 0) {
            task.setEndDate(originTask.getEndDate());
        } else {
            Date date = Date.valueOf(str);
            if(team.getEndDate().getTime() < date.getTime()) {
                task.setEndDate(team.getEndDate());
            } else {
                task.setEndDate(date);
            }
        }
        
        System.out.printf("작업자(%s) ? ", originTask.getWorker());
        String memberId = keyScan.nextLine();
        if(memberId.length() == 0) {
            task.setWorker(originTask.getWorker());
        } else {
            Member member = team.getMember(memberId);
            if(member == null) {
                System.out.printf("'%s'는 이 팀의 회원이 아닙니다. 작업자는 비워두겠습니다.", str);
            } else {
                task.setWorker(member);
            }
        }
        
        if(Console.confirm("변경하시겠습니까?")) {
            taskDao.update(task);
            System.out.println("저장하였습니다.");
        } else {
            System.out.println("취소하였습니다.");
        }
    }
    
    
    // 팀 작업 삭제 메서드
    private void onTaskDelete(String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        System.out.print("삭제할 작업의 번호? ");
        int no = Integer.parseInt(keyScan.nextLine());

        Task task = this.taskDao.get(teamName, no);
        if(task == null){
            System.out.printf("'%s'팀의 %d번 작업을 찾을 수 없습니다.\n",
                    teamName, no);
            return;
        }
        
        if(Console.confirm("정말 삭제하시겠습니까?")) {
            taskDao.delete(task.getNo());
            System.out.println("삭제하였습니다.");
        } else {
            System.out.println("취소하였습니다.");
        }
    }
    
    private void onTaskState(String teamName) {
        if (teamName == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        System.out.print("상태를 변경할 작업의 번호? ");
        int no = Integer.parseInt(keyScan.nextLine());
        Task task = this.taskDao.get(teamName, no);
        if(task == null){
            System.out.printf("'%s'팀의 %d번 작업을 찾을 수 없습니다.\n",
                    teamName, no);
            return;
        }
        
        // state부터 시작
        
        
        
        
    }
    
    public String getStateLabel(int state) {
        switch(state) {
            case Task.READY: return "작업대기";
            case Task.WORKING: return "작업중";
            case Task.COMPLETE: return "작업완료";
            default : return null;
        }
    }
}