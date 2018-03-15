package bitcamp.java106.pms3.my;

import java.util.Scanner;
import bitcamp.java106.pms2.domain.Team;
import bitcamp.java106.pms2.domain.Member;

public class App2 {

    static Scanner keyScan = new Scanner(System.in);
    static Team[] teams = new Team[1000]; // 레퍼런스 생성
    static Member[] members = new Member[1000];
    static int tCount = 0; // team인원 등록 수
    static  int mCount = 0;
    static String option = null; // 명령어 뒤의 문자열

    static int getTeamIndex(String name) {
        for(int i = 0; i < tCount; i++) {
            if(teams[i] == null) continue;
            if(name.equals(teams[i].name.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    static int getMemberIndex(String id) {
        for(int i = 0; i < mCount; i ++) {
            if(members[i] == null) continue;
            if(option.equals(members[i].id.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
    // 명령어를 입력받는 메서드
    static String[] onInput() {
        return keyScan.nextLine().toLowerCase().split(" ");
    }

    // 도움말을 출력하는 메서드
    static void onHelp() {
        System.out.println("[도움말 제공]");
        System.out.println("팀 등록 명령 : team/add");
        System.out.println("팀 조회 명령 : team/list");
        System.out.println("팀 상세조회 명령 : team/view 팀명");
        System.out.println("회원 등록 명령 : member/add");
        System.out.println("회원 조회 명령 : member/list");
        System.out.println("회원 상세조회 명령 : member/view 아이디");
        System.out.println("종료 : quit");
    }

    // 팀 등록 메서드
    static void onTeamAdd() {
        Team team = new Team(); // 인스턴스 생성
                                // add명령어 실행할 때 마다 
                                // team은 새로운 인스턴스가 생성된다.
        
        System.out.print("팀명? ");
        team.name = keyScan.nextLine();
        System.out.print("설명? ");
        team.description = keyScan.nextLine();
        System.out.print("최대인원? ");
        team.maxQty = keyScan.nextInt();
        keyScan.nextLine();
        System.out.print("시작일? ");
        team.startDate = keyScan.nextLine();
        System.out.print("종료일? ");
        team.endDate = keyScan.nextLine();

        teams[tCount++] = team;
    }

    // 모든 팀 리스트 출력 메서드
    static void onTeamList() {
        for(int i = 0; i < tCount; i++) {
            if(teams[i] == null) continue;
            System.out.printf("%s, %d, %s ~ %s\n",
                teams[i].name,
                teams[i].maxQty,
                teams[i].startDate,
                teams[i].endDate);
        }
    }

    // 팀명 찾아서 출력 하는 메서드
    static void onTeamView() {
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = getTeamIndex(option);
 
        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");

        } else {
            Team team = teams[i];
            System.out.printf("팀명 : %s\n", team.name);
            System.out.printf("설명 : %s\n", team.description);
            System.out.printf("최대인원 : %d\n", team.maxQty);
            System.out.printf("기간 : %s ~ %s\n",
                team.startDate, team.endDate);
        }
    }

    // 회원 등록 메서드
    static void onMemAdd() {
        Member member = new Member();
                
        System.out.print("아이디? ");
        member.id = keyScan.nextLine();
        System.out.print("이메일? ");
        member.email = keyScan.nextLine();
        System.out.print("암호? ");
        member.password = keyScan.nextLine();

        members[mCount++] = member;
    }

    // 모든 회원 출력 메서드
    static void onMemList() {
        for(int i = 0; i < mCount; i++) {
            if(members[i] == null) continue;
            System.out.printf("%s, %s, %s\n", 
                members[i].id,
                members[i].email,
                members[i].password);
        }
    }

    // 회원명 찾아서 출력 하는 메서드
    static void onMemView() {
        if(option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = getMemberIndex(option);

        if(i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member member = members[i];
            System.out.printf("아이디: %s\n", member.id);
            System.out.printf("이메일: %s\n", member.email);
            System.out.printf("암호: %s\n", member.password);
        }
    }

    // 팀 정보 변경 기능
    static void onTeamUpdate() {
        
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = getTeamIndex(option);

        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");

        } else {
            Team team = teams[i];
            Team updateTeam = new Team();
            System.out.printf("팀명(%s) ? ", team.name);
            updateTeam.name = keyScan.nextLine();
            System.out.printf("설명(%s) ? ", team.description);
            updateTeam.description = keyScan.nextLine();
            System.out.printf("최대인원(%d) ?", team.maxQty);
            updateTeam.maxQty = keyScan.nextInt();
            keyScan.nextLine();
            System.out.printf("시작일(%s) ?", team.startDate);
            updateTeam.startDate = keyScan.nextLine();
            System.out.printf("종료일(%s) ?", team.endDate);
            updateTeam.endDate = keyScan.nextLine();
            teams[i] = updateTeam;
            System.out.println("변경하였습니다.");
        }
        
        
        /*
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        Team team = null;
        for(int i = 0; i < tCount; i++) {
            if(option.equals(teams[i].name.toLowerCase())) {
                team = teams[i];
                break;
            }
        }

        if(team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.printf("팀명(%s)? ", team.name);
            team.name = keyScan.nextLine();
            System.out.printf("설명(%s)? ", team.description);
            team.description = keyScan.nextLine();
            System.out.printf("최대인원(%d)? ", team.maxQty);
            team.maxQty = keyScan.nextInt();
            keyScan.nextLine();
            System.out.printf("시작일(%s)? ", team.startDate);
            team.startDate = keyScan.nextLine();
            System.out.printf("종료일(%s)? ", team.endDate);
            team.endDate = keyScan.nextLine();
            System.out.println("변경하였습니다.");
        }
        */
    }

    // 팀 이름 삭제 메서드
    static void onTeamDelete() {

        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = getTeamIndex(option);

        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");

        } else {
            System.out.print("정말 삭제하시겠습니까?(y/N) ");
            String input = keyScan.nextLine().toLowerCase();
            if(input.equals("y")) {
                teams[i] = null;
                System.out.println("삭제하였습니다.");
            }
        }
        /*
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        Team team = null;
        for(int i = 0; i < tCount; i++) {
            if(option.equals(teams[i].name.toLowerCase())) {
                team = teams[i];
                break;
            }
        }

        if(team == null) {
            System.out.println("해당 이름의 팀이 없습니다.");
        } else {
            System.out.print("정말 삭제하시겠습니까?(Y/n) ");
            String Yn = keyScan.nextLine();
            
            if(Yn.equals("Y")) {
                int del = 0;
                for(int i = 0; i < tCount; i++) { // 삭제할 인덱스 번호 찾기
                    if(team.name.equals(teams[i].name.toLowerCase())) {
                        del = i;
                        break;
                    }
                }

                for(int i = del; i < tCount; i++) {
                    teams[i] = teams[i+1];
                    tCount--;
                }
            }                   
                System.out.println("삭제하였습니다.");
            }
            */
        }

    // 멤버 정보 변경 기능
    static void onMemberUpdate() {

        if(option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = getMemberIndex(option);

        if(i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member member = members[i];
            Member updateMember = new Member();
            System.out.printf("아이디(%s)? ", member.id);
            updateMember.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", member.email);
            updateMember.email = keyScan.nextLine();
            System.out.printf("암호? ");
            updateMember.password = keyScan.nextLine();
            members[i] = updateMember;
            System.out.println("변경하였습니다.");
        }
        /*
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        Member member = null;
        for(int i = 0; i < mCount; i++) {
            if(option.equals(members[i].id.toLowerCase())) {
                member = members[i];
                break;
            }
        }

        if(member == null) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {
            System.out.printf("아이디(%s)? ", member.id);
            member.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", member.email);
            member.email = keyScan.nextLine();
            System.out.printf("암호(%s)? ", member.password);
            member.password = keyScan.nextLine();
            System.out.println("변경하였습니다.");
        }
        */
    }

    // 멤버 정보 삭제 기능
    static void onMemberDelete() {
        if(option == null) {
            System.out.println("팀명을 입력하시기 바랍니다.");
            return;
        }

        int i = getMemberIndex(option);

        if(i == -1) {
            System.out.println("해당 이름의 팀이 없습니다.");

        } else {
            System.out.print("정말 삭제하시겠습니까?(y/N) ");
            String input = keyScan.nextLine().toLowerCase();
            if(input.equals("y")) {
                members[i] = null;
                System.out.println("삭제하였습니다.");
            }
        }
        /*
        if(option == null) {
            System.out.println("멤버 이름을 입력하시기 바랍니다.");
            return;
        }

        Member member = null;
        for(int i = 0; i < mCount; i++) {
            if(option.equals(members[i].id.toLowerCase())) {
                member = members[i];
                break;
            }
        }

        if(member == null) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {
            System.out.print("정말 삭제하시겠습니까?(Y/n) ");
            String Yn = keyScan.nextLine();
            
            if(Yn.equals("Y")) {
                int del = 0;
                for(int i = 0; i < mCount; i++) { // 삭제할 인덱스 번호 찾기
                    if(member.id.equals(members[i].id.toLowerCase())) {
                        del = i;
                        break;
                    }
                }

                for(int i = del; i < mCount; i++) {
                    members[i] = members[i+1];
                    mCount--;
                }
            }                   
                System.out.println("삭제하였습니다.");
            }
            */
        }

    public static void main(String[] args) {
        
        while(true) {
            System.out.print("명령> ");
            String[] arr = onInput();
            String menu = arr[0];

            if(arr.length == 2) { // 명령어 이외의 값이 있다면 다음 배열에 저장
                option = arr[1];
            } else {
                option = null;
            }

            if(menu.equals("help")) {
                onHelp();
            } else if(menu.equals("team/add")) {
                onTeamAdd();
            } else if (menu.equals("team/list")) {
                onTeamList();
            } else if (menu.equals("team/view")) {
                onTeamView();
            } else if(menu.equals("member/add")) {
                onMemAdd();
            } else if(menu.equals("member/list")) {
                onMemList();
            } else if(menu.equals("member/view")) {
                onMemView();
            } else if(menu.equals("team/update")) {
                onTeamUpdate();
            } else if(menu.equals("team/delete")) {
                onTeamDelete();
            } else if(menu.equals("member/update")) {
                onMemberUpdate();
            } else if(menu.equals("member/delete")) {
                onMemberDelete();
            } else if(menu.equals("quit")) {
                System.out.println("안녕히가세요!");
                break;
            } else {
                System.out.println("명령어가 올바르지 않습니다.");
            }

            System.out.println();
        }
    }
}