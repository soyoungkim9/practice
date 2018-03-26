package bitcamp.java106.pms6.controller;

import java.util.Scanner;

import bitcamp.java106.pms6.domain.Member;
import bitcamp.java106.pms6.dao.MemberDao;
import bitcamp.java106.pms6.util.Console;

public class MemberController {
    Scanner keyScan;
    MemberDao memberDao;
        
    public MemberController(Scanner scanner, MemberDao memberDao) {
        this.keyScan = scanner;
        this.memberDao = memberDao;
    }
    
    public void service(String menu, String option) {
        if(menu.equals("member/add")) {
            this.onMemAdd();
        } else if(menu.equals("member/list")) {
            this.onMemList();
        } else if(menu.equals("member/view")) {
            this.onMemView(option);
        } else if(menu.equals("member/update")) {
            this.onMemberUpdate(option);
        } else if(menu.equals("member/delete")) {
            this.onMemberDelete(option);
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
    }
    
    // 회원 등록 메서드
    public void onMemAdd() {
        Member member = new Member();
                
        System.out.print("아이디? ");
        member.setId(this.keyScan.nextLine());
        System.out.print("이메일? ");
        member.setEmail(this.keyScan.nextLine());
        System.out.print("암호? ");
        member.setPassword(this.keyScan.nextLine());

        memberDao.insert(member);
    }

    // 모든 회원 출력 메서드
    public void onMemList() {
        Member[] members = memberDao.list();
        for(int i = 0; i < members.length; i++) {
            if(members[i] == null) continue;
            System.out.printf("%s, %s, %s\n", 
                members[i].getId(),
                members[i].getEmail(),
                members[i].getPassword());
        }
    }

    // 회원명 찾아서 출력 하는 메서드
    public void onMemView(String option) {
        if(option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        Member member = memberDao.get(option);

        if(member == null) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            System.out.printf("아이디: %s\n", member.getId());
            System.out.printf("이메일: %s\n", member.getEmail());
            System.out.printf("암호: %s\n", member.getPassword());
        }
    }

    // 멤버 정보 변경 기능
    public void onMemberUpdate(String option) {
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        Member member = memberDao.get(option);
        
        if(member == null) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {
            Member memberUpdate = new Member();
            System.out.printf("아이디(%s)\n", member.getId());
            memberUpdate.setId(member.getId());
            System.out.printf("이메일(%s)? ", member.getEmail());
            memberUpdate.setEmail(keyScan.nextLine());
            System.out.printf("암호(%s)? ", member.getPassword());
            memberUpdate.setPassword(keyScan.nextLine());
            memberUpdate.setNo(member.getNo());
            memberDao.update(memberUpdate);
            System.out.println("변경하였습니다.");
        }
    }

    // 멤버 정보 삭제 기능
    public void onMemberDelete(String option) {
        if(option == null) {
            System.out.println("멤버 이름을 입력하시기 바랍니다.");
            return;
        }

        Member member = memberDao.get(option);

        if(member == null) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {            
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                memberDao.delete(option);
                System.out.println("삭제하였습니다.");
            }                   
          }
        }

}