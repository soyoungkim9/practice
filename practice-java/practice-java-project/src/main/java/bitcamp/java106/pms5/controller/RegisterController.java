package bitcamp.java106.pms5.controller;

import java.util.Scanner;

import bitcamp.java106.pms3.dao.TeamDao;
import bitcamp.java106.pms3.dao.MemberDao;
import bitcamp.java106.pms3.domain.Register;

public class RegisterController extends MemberController {
    Scanner keyScan;
    
    // 이따가 삭제
    Register[] registers = new Register[1000]; // 레퍼런스 생성
    int rCount = 0; // team인원 등록 수
    
    
    public RegisterController (Scanner scanner) {
        super(scanner);
        this.keyScan = scanner;
    }
    
    public void service(String menu, String option) {
        if(menu.equals("team/member/add")) {
            this.onRegisterAdd(option);
        } else if (menu.equals("team/list")) {
            this.onRegisterList();
        } else if(menu.equals("team/member/delete")) {
            this.onRegisterDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    public void onRegisterAdd(String option) {
        //TeamController teamController = new TeamController();
        //teamController.onTeamView(option);
        //teamController.onTeamList();
        // 기존에 저장된 팀명이 있는지 찾아서 담는 변수
        // int result = teamDao.get(option);
                        
        if(getTeamDao().get(option) == -1) {
            System.out.printf("%s팀은 존재하지 않습니다.\n", option);
        } else {
            Register register = new Register(); 

            System.out.print("추가할 멤버의 아이디는? ");
            register.name = this.keyScan.nextLine();
            
            registers[rCount++] = register; // 이따가 데이터 처리
            System.out.println("추가하였습니다.");
        }
    }
    
    public void onRegisterList() {
    
    }
    
    public void onRegisterDelete(String option) {
    
    }
}
