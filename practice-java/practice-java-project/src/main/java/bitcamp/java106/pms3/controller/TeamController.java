package bitcamp.java106.pms3.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms2.domain.Team;
import bitcamp.java106.pms3.dao.TeamDao;
import bitcamp.java106.pms3.util.Console;

public class TeamController {
    Scanner keyScan;
    
    // 이따가 삭제
    Team[] teams = new Team[1000]; // 레퍼런스 생성
    int tCount = 0; // team인원 등록 수
    
    TeamDao teamdao = new TeamDao();
    
    public TeamController (Scanner scanner) {
        this.keyScan = scanner;
    }
    
    public void service(String menu, String option) {
        if(menu.equals("team/add")) {
            this.onTeamAdd();
        } else if (menu.equals("team/list")) {
            this.onTeamList();
        } else if (menu.equals("team/view")) {
            this.onTeamView(option);
        } else if(menu.equals("team/update")) {
            onTeamUpdate(option);
        } else if(menu.equals("team/delete")) {
            onTeamDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }

//    // 옵션과 일치하는 팀의 인덱스를 찾는 메서드
//    int onTeamIndex(String option) {
//        for(int i = 0; i < this.tCount; i++) {
//            if(this.teams[i] == null) continue;
//            if(option.equals(this.teams[i].name.toLowerCase())) {
//                return i;
//            }
//        } 
//        return -1;     
//    }

    // 팀 등록 메서드
    public void onTeamAdd() {
        Team team = new Team(); // 인스턴스 생성
                                // add명령어 실행할 때 마다 
                                // team은 새로운 인스턴스가 생성된다.
        
        System.out.print("팀명? ");
        team.name = this.keyScan.nextLine();
        System.out.print("설명? ");
        team.description = this.keyScan.nextLine();
        System.out.print("최대인원? ");
        team.maxQty = this.keyScan.nextInt();
        keyScan.nextLine();
        System.out.print("시작일? ");
        team.startDate = Date.valueOf(this.keyScan.nextLine());
        System.out.print("종료일? ");
        team.endDate = Date.valueOf(this.keyScan.nextLine());

        teamdao.insert(team);
    }

    // 모든 팀 리스트 출력 메서드
    public void onTeamList() {
        Team[] teams = teamdao.list();
        
        for(int i = 0; i < teams.length; i++) {
            if(teams[i] == null) continue;
            System.out.printf("%s, %d, %s ~ %s\n",
                    teams[i].name,
                    teams[i].maxQty,
                    teams[i].startDate,
                    teams[i].endDate);
        }
    }

    // 팀명 찾아서 출력 하는 메서드
    public void onTeamView(String option) {
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = teamdao.get(option);
        Team team = teamdao.view(i);
        
        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");

        } else { 
            System.out.printf("팀명 : %s\n", team.name);
            System.out.printf("설명 : %s\n", team.description);
            System.out.printf("최대인원 : %d\n", team.maxQty);
            System.out.printf("기간 : %s ~ %s\n",
                    team.startDate, team.endDate);
        }
    }


    // 팀 정보 변경 기능
    public void onTeamUpdate(String option) {
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        int i = teamdao.get(option);
        Team team = teamdao.view(i);

        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team teamUpdate = new Team();
            System.out.printf("팀명(%s)? ", team.name);
            teamUpdate.name = keyScan.nextLine();
            System.out.printf("설명(%s)? ", team.description);
            teamUpdate.description = keyScan.nextLine();
            System.out.printf("최대인원(%d)? ", team.maxQty);
            teamUpdate.maxQty = keyScan.nextInt();
            keyScan.nextLine();
            System.out.printf("시작일(%s)? ", team.startDate);
            teamUpdate.startDate = Date.valueOf(this.keyScan.nextLine());
            System.out.printf("종료일(%s)? ", team.endDate);
            teamUpdate.endDate = Date.valueOf(this.keyScan.nextLine());
            teamUpdate.no = team.no;
            teamdao.update(teamUpdate);
            System.out.println("변경하였습니다.");
        }
    }

    // 팀 이름 삭제 메서드
    public void onTeamDelete(String option) {
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        int i = teamdao.get(option);


        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {            
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                teamdao.delete(i);
                System.out.println("삭제하였습니다.");
            }                   
        }
    }
            
        
}