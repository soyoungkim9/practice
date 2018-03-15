package bitcmp.java106.pms3.controller;

import java.util.Scanner;
import bitcamp.java106.pms2.domain.Member;
import bitcamp.java106.pms3.util.Console;

class MemberController {
    public static Scanner keyScan;
    static Member[] members = new Member[1000];
    static  int mCount = 0;

    public static void service(String menu, String option) {
        if(menu.equals("member/add")) {
            onMemAdd();
        } else if(menu.equals("member/list")) {
            onMemList();
        } else if(menu.equals("member/view")) {
            onMemView(option);
        } else if(menu.equals("member/update")) {
            onMemberUpdate(option);
        } else if(menu.equals("member/delete")) {
            onMemberDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }

    // 옵션과 일치하는 멤버의 인덱스를 찾는 메서드
    static int onMemberIndex(String option) {
        for(int i = 0; i < mCount; i ++) {
            if(members[i] == null) continue;
            if(option.equals(members[i].id.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }

    // 회원 등록 메서드
    public static void onMemAdd() {
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
    public static void onMemList() {
        for(int i = 0; i < mCount; i++) {
            if(members[i] == null) continue;
            System.out.printf("%s, %s, %s\n", 
                members[i].id,
                members[i].email,
                members[i].password);
        }
    }

    // 회원명 찾아서 출력 하는 메서드
    public static void onMemView(String option) {
        if(option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = onMemberIndex(option);

        if(i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            System.out.printf("아이디: %s\n", members[i].id);
            System.out.printf("이메일: %s\n", members[i].email);
            System.out.printf("암호: %s\n", members[i].password);
        }
    }

    // 멤버 정보 변경 기능
    public static void onMemberUpdate(String option) {
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        int i = onMemberIndex(option);

        if(i == -1) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {
            Member memberUpdate = new Member();
            System.out.printf("아이디(%s)? ", members[i].id);
            memberUpdate.id = keyScan.nextLine();
            System.out.printf("이메일(%s)? ", members[i].email);
            memberUpdate.email = keyScan.nextLine();
            System.out.printf("암호(%s)? ", members[i].password);
            memberUpdate.password = keyScan.nextLine();
            System.out.println("변경하였습니다.");
            members[i] = memberUpdate;
        }
    }

    // 멤버 정보 삭제 기능
    public static void onMemberDelete(String option) {
        if(option == null) {
            System.out.println("멤버 이름을 입력하시기 바랍니다.");
            return;
        }

        int i = onMemberIndex(option);

        if(i == -1) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {            
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                members[i] = null;
                System.out.println("삭제하였습니다.");
            }                   
          }
        }

}