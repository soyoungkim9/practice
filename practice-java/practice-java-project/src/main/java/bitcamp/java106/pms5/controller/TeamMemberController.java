package bitcamp.java106.pms5.controller;

import java.util.Scanner;

import bitcamp.java106.pms5.dao.MemberDao;
import bitcamp.java106.pms5.dao.TeamDao;
import bitcamp.java106.pms5.domain.Member;
import bitcamp.java106.pms5.domain.Team;

public class TeamMemberController {
    Scanner keyScan;
    TeamDao teamdao;
    MemberDao memberDao;

    public TeamMemberController(Scanner scanner, TeamDao teamDao, MemberDao memberDao) {
        this.keyScan = scanner;
        this.teamdao = teamDao;
        this.memberDao = memberDao;
        
    }

    public void service(String menu, String option) {
        if (menu.equals("team/member/add")) {
            this.onTeamMemberAdd(option);
        } else if (menu.equals("team/member/list")) {
            this.onTeamMemberList(option);
        } else if (menu.equals("team/member/delete")) {
            this.onTeamMemberDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    // 팀 멤버 등록 메서드
    public void onTeamMemberAdd(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamdao.get(option);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        }     
        System.out.println("[팀 멤버 추가]");
        System.out.print("추가할 멤버의 아이디는? ");
        String memberId = keyScan.nextLine();
        
        if(team.isExist(memberId)) {
            System.out.println("이미 등록된 회원입니다.");
            return;
        }
        
        Member member = this.memberDao.get(memberId);
        if(member == null) {
            System.out.printf("%s 회원은 존재하지 않습니다.\n", memberId);
            return;
        }
        
        team.addMember(member);
        System.out.println("추가하였습니다.");
    }

    
    // 어느 팀에 속한 모든 멤버 리스트 출력 메서드
    public void onTeamMemberList(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamdao.get(option);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        }
        System.out.println("[팀 멤버 목록]");
        System.out.print("회원들: ");
        for (int i = 0; i < team.members.length; i++) {
            if (team.members[i] == null)
                continue;
            System.out.printf("%s, ", team.members[i].id);
        }
        System.out.println();
    }
    
    
    // 팀 이름 삭제 메서드
    public void onTeamMemberDelete(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return; 
        }
        
        Team team = this.teamdao.get(option);
        if(team == null){
            System.out.printf("%s 팀은 존재하지 않습니다.\n", option);
            return;
        }
        
        System.out.print("삭제할 팀원은? ");
        String memberId = keyScan.nextLine();
        if(!team.isExist(memberId)) {
            System.out.println("이 팀의 회원이 아닙니다.");
            return;
        }
        
        team.deleteMember(memberId);
        System.out.println("삭제하였습니다.");
        
    }
}