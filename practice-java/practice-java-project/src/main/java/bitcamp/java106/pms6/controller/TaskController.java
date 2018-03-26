// 컨트롤러가 작업하는데 필요한 객체를 반드시 준비하도록 생성자를 추가한다.
// => 생성자를 통해 필수 입력 값을 반드시 설정하도록 강제시킬 수 있다.
// => 즉 생성자란, 객체를 사용하기 전에 유효한 값으로 설정하게 만드는 문법이다.
package bitcamp.java106.pms6.controller;

import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import bitcamp.java106.pms6.dao.TaskDao;
import bitcamp.java106.pms6.dao.TeamDao;
import bitcamp.java106.pms6.domain.Task;
import bitcamp.java106.pms6.domain.Team;

public class TaskController {
    
    Scanner keyScan;
    TaskDao taskDao;
    TeamDao teamDao;
    
    public TaskController(Scanner scanner, TaskDao taskDao, TeamDao teamDao) {
        this.keyScan = scanner;
        this.taskDao = taskDao;
        this.teamDao = teamDao;
    }
    // board만 처리하는 함수
    public void service(String menu, String option) throws ParseException {
        if(menu.equals("task/add")) {
            this.onTaskAdd(option);
        } else if(menu.equals("task/list")) {
            this.onTaskList(option);
        } else if(menu.equals("task/view")) {
            this.onTaskView(option);
        } else if(menu.equals("task/update")) {
            this.onTaskUpdate(option);
        } else if(menu.equals("task/delete")) {
            //this.ontaskDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    void onTaskAdd(String option) throws ParseException {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(option); // teams[i] 반환
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        } 
        
        Task task = new Task(); 
        System.out.println("[task 등록]");
        System.out.print("작업명? ");
        String workName = this.keyScan.nextLine();
        task.setWorkName(workName);
        System.out.print("시작일? "); 
        String startDate = this.keyScan.nextLine();
        // 시작일을 지정하지 않으면 팀 시작일을 사용한다.
        // startDate가 null이 아니면 String을 Date로 형변환
        if(startDate.equals(null) || startDate.equals("")) {
            task.setStartDate(team.getStartDate());
        } else {
            // Date tempDate1 = Date.valueOf(startDate);
            DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date tempDate1 = sdf1.parse(startDate);
            task.setStartDate(tempDate1);
        }
        System.out.print("종료일? ");
        String endDate = this.keyScan.nextLine();
        // 종료일을 지정하지 않으면 팀 종료일을 사용한다.
        // startDate가 null이 아니면 String을 Date로 형변환
        if(endDate.equals(null) || endDate.equals("")) {
            task.setEndDate(team.getEndDate());
        } else {
            // Date tempDate2 = Date.valueOf(startDate);
            DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date tempDate2 = sdf2.parse(endDate);
            task.setEndDate(tempDate2);
        }
        
        System.out.print("작업자? ");
        String worker = this.keyScan.nextLine();
     // 작업자를 지정하지 않으면 null 값을 저장한다.
        if(endDate.equals(null) || endDate.equals("")) {
            task.setWorker(" ");
        } else {
            task.setWorker(worker);
        }
        
        taskDao.insert(task);
    }
    
    // 모든 Task 출력 메서드
    void onTaskList(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(option); // teams[i] 반환
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        } 
        
        System.out.println("[Task 목록]");
        Task[] task = taskDao.list();
        
        for(int i = 0; i < task.length; i++) {
            if(task[i] == null) continue;
            System.out.printf("%d, %s, ",
                i, task[i].getWorkName());
            System.out.println(task[i].getStartDate() + ", " 
                    + task[i].getEndDate() + ", " + task[i].getWorker());
        }

    }
    
    // Task 조회 메서드
    void onTaskView(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(option); // teams[i] 반환
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        } 
        
        System.out.println("[Task 조회]");
        System.out.print("작업번호? ");
        int i = keyScan.nextInt();
        
        Task task = taskDao.get(i);
        if(task == null) {
            System.out.println("해당 번호의 Task가 없습니다.");

        } else {
            System.out.printf("작업명: %s\n", task.getWorkName());
            System.out.println("시작일: " + task.getStartDate());
            System.out.println("종료일: " + task.getEndDate());
            System.out.printf("작업자: %s\n", task.getWorker());
        }
    }
    
    
    
    // 게시글 업데이트 메서드
    void onTaskUpdate(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamDao.get(option); // teams[i] 반환
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        } 
        
        System.out.println("[Task 변경]");
        System.out.print("변경할 작업의 번호? ");
        int i = keyScan.nextInt();
        
        Task task = taskDao.get(i);
        if(task == null) {
            System.out.println("해당 번호의 Task가 없습니다.");

        } else {
            Task taskUpdate = new Task();
            System.out.printf("작업명(%s)? ", task.getWorkName());
            taskUpdate.setWorkName(keyScan.nextLine());
            System.out.print("시작일(" + task.getStartDate() + ")? ");
            String sDate = keyScan.nextLine();
            if(sDate.equals(null) || sDate.equals("")) {
                taskUpdate.setStartDate(task.getStartDate());
            } else {
                DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                Date tempDate1 = sdf1.parse(sDate);
                taskUpdate.setStartDate(tempDate1);
            }
            System.out.print("종료일(" + task.getStartDate() + ")? ");
            String eDate = keyScan.nextLine();
            if(eDate.equals(null) || eDate.equals("")) {
                taskUpdate.setEndDate(task.getEndDate());
            } else {
                DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                Date tempDate2 = sdf2.parse(sDate);
                taskUpdate.setEndDate(tempDate2);
            }

            System.out.printf("작업자(%s)? ", task.getWorker());
            taskUpdate.setWorkName(keyScan.nextLine());
        }
        
        
        
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
            System.out.printf("제목(%s): ", board.getSubject());
            boardUpdate.setSubject(keyScan.nextLine());
            System.out.printf("내용(%s): ", board.getContent());
            boardUpdate.setContent(keyScan.nextLine());
            boardUpdate.setDate(board.getDate());
            boardUpdate.setNo(board.getNo());
            boardDao.update(boardUpdate);
            System.out.println("변경하였습니다.");
            
        }
    }
    
    /*
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
    */
}
