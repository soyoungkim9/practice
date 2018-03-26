package bitcamp.java106.pms6.controller;

import java.sql.Date;
import java.util.Scanner;

import bitcamp.java106.pms6.domain.Team;
import bitcamp.java106.pms6.dao.TeamDao;
import bitcamp.java106.pms6.util.Console;

public class TeamController {
    Scanner keyScan;
    TeamDao teamdao;

    public TeamController(Scanner scanner, TeamDao teamDao) {
        this.keyScan = scanner;
        this.teamdao = teamDao;
    }

    public void service(String menu, String option) {
        if (menu.equals("team/add")) {
            this.onTeamAdd();
        } else if (menu.equals("team/list")) {
            this.onTeamList();
        } else if (menu.equals("team/view")) {
            this.onTeamView(option);
        } else if (menu.equals("team/update")) {
            onTeamUpdate(option);
        } else if (menu.equals("team/delete")) {
            onTeamDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }

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

        for (int i = 0; i < teams.length; i++) {
            if (teams[i] == null)
                continue;
            System.out.printf("%s, %d, %s ~ %s\n", teams[i].name, teams[i].maxQty, teams[i].startDate,
                    teams[i].endDate);
        }
    }

    // 팀명 찾아서 출력 하는 메서드
    public void onTeamView(String option) {
        if (option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
        }

        Team team = teamdao.get(option);

        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.printf("팀명 : %s\n", team.name);
            System.out.printf("설명 : %s\n", team.description);
            System.out.printf("최대인원 : %d\n", team.maxQty);
            System.out.printf("기간 : %s ~ %s\n", team.startDate, team.endDate);
        }
    }

    // 팀 정보 변경 기능
    public void onTeamUpdate(String option) {
        if (option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        Team team = teamdao.get(option);

        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            Team teamUpdate = new Team();
            System.out.printf("팀명(%s)\n", team.name);
            teamUpdate.name = team.name;
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
        if (option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        Team team = teamdao.get(option);

        if (team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            if (Console.confirm("정말 삭제하시겠습니까?")) {
                teamdao.delete(option);
                System.out.println("삭제하였습니다.");
            }
        }
    }

}