package bitcamp.java106.pms3.controller;

import java.util.Scanner;

import bitcamp.java106.pms2.domain.Member;
import bitcamp.java106.pms3.dao.MemberDao;
import bitcamp.java106.pms3.util.Console;

public class MemberController extends TeamController {
    Scanner keyScan;
    
    MemberDao memberDao = new MemberDao();
    
    // 이따가 삭제
    Member[] members = new Member[1000];
    int mCount = 0;

    public MemberController(){}
    public MemberController(Scanner scanner) {
        super(scanner); // 매개변수가 있는 생성자 호출
        this.keyScan = scanner;
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
    
//    public int onMemberIndex(String option) {
//        for(int i = 0; i < mCount; i ++) {
//            if(members[i] == null) continue;
//            if(option.equals(members[i].id.toLowerCase())) {
//                return i;
//            }
//        }
//        return -1;
//    }

    // 회원 등록 메서드
    public void onMemAdd() {
        Member member = new Member();
                
        System.out.print("아이디? ");
        member.id = this.keyScan.nextLine();
        System.out.print("이메일? ");
        member.email = this.keyScan.nextLine();
        System.out.print("암호? ");
        member.password = this.keyScan.nextLine();

        memberDao.insert(member);
    }

    // 모든 회원 출력 메서드
    public void onMemList() {
        Member[] members = memberDao.list();
        for(int i = 0; i < members.length; i++) {
            if(members[i] == null) continue;
            System.out.printf("%s, %s, %s\n", 
                members[i].id,
                members[i].email,
                members[i].password);
        }
    }

    // 회원명 찾아서 출력 하는 메서드
    public void onMemView(String option) {
        if(option == null) {
            System.out.println("아이디를 입력하시기 바랍니다.");
            return;
        }
        
        int i = memberDao.get(option);

        if(i == -1) {
            System.out.println("해당 아이디의 회원이 없습니다.");
        } else {
            Member member = memberDao.view(i);
            System.out.printf("아이디: %s\n", member.id);
            System.out.printf("이메일: %s\n", member.email);
            System.out.printf("암호: %s\n", member.password);
        }
    }

    // 멤버 정보 변경 기능
    public void onMemberUpdate(String option) {
        if(option == null) {
            System.out.println("팀 이름을 입력하시기 바랍니다.");
            return;
        }

        int i = memberDao.get(option);
        Member member = memberDao.view(i);
        
        if(i == -1) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {
            Member memberUpdate = new Member();
            System.out.printf("아이디(%s)\n", member.id);
            memberUpdate.id = member.id;
            System.out.printf("이메일(%s)? ", member.email);
            memberUpdate.email = keyScan.nextLine();
            System.out.printf("암호(%s)? ", member.password);
            memberUpdate.password = keyScan.nextLine();
            memberUpdate.no = member.no;
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

        int i = memberDao.get(option);

        if(i == -1) {
            System.out.println("해당 이름의 멤버가 없습니다.");
        } else {            
            if(Console.confirm("정말 삭제하시겠습니까?")) {
                memberDao.delete(i);
                System.out.println("삭제하였습니다.");
            }                   
          }
        }

}