package bitcamp.java106.pms4.controller;

import java.util.Scanner;

import bitcamp.java106.pms2.domain.Member;
import bitcamp.java106.pms2.domain.Team;
import bitcamp.java106.pms4.dao.MemberDao;
import bitcamp.java106.pms4.dao.TeamDao;

public class RegisterController {
    Scanner keyScan;
    TeamDao teamDao;
    MemberDao memberDao;
        
    public RegisterController (Scanner scanner, TeamDao teamDao, MemberDao memberDao) {
        this.keyScan = scanner;
        this.teamDao = teamDao;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        if(menu.equals("team/member/add")) {
            this.onRegisterAdd(option);
        } else if (menu.equals("team/member/list")) {
            this.onRegisterList(option);
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
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        System.out.println("[팀 멤버 추가]");
        Team team = new Team();
        int i1 = teamDao.get(option);
        if(i1 == -1) {
            System.out.printf("%s팀은 존재하지 않습니다.\n", option);
            return;
        }
        
        System.out.print("추가할 멤버의 아이디는? ");
        String memberId = this.keyScan.nextLine();
        
        Member member = new Member();
        int i2 = memberDao.get(memberId);
        if(i2 == -1) {
            System.out.printf("%s회원은 존재하지 않습니다.\n", memberId);
            return;
        }
        
        member = memberDao.view(i2);
        
        if(team.isExist(memberId)) {
            System.out.println("이미 등록된 회원입니다.");
            return;
        }
        
        team.addMember(member);
        
     }
        
    public void onRegisterList(String option) {
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        int i1 = teamDao.get(option);
        Team team = teamDao.view(i1);
        
        if(i1 == -1) {
            System.out.printf("%s팀은 존재하지 않습니다.\n", option);
            return;
        }
        
        System.out.println("[팀 멤버  목록]");
        System.out.println("회원들: ");
        for(int i = 0; i < team.members.length; i++) {
            if(team.members[i] == null) continue;
            System.out.printf("%s, ", team.members[i].id);
        }
        System.out.println();

    }
    
    public void onRegisterDelete(String option) {
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }
        
        int i1 = teamDao.get(option);
        Team team = teamDao.view(i1);

        System.out.println("삭제할 팀원은? ");
        String memberId = keyScan.nextLine();

        if(!team.isExist(memberId)) {
            System.out.println("이 팀의 회원이 아닙니다.");
            return;
        }
        
        team.deleteMember(memberId);
        
        System.out.println("[팀 멤버 삭제]");
        System.out.println("삭제 하였습니다.");

    }
}
