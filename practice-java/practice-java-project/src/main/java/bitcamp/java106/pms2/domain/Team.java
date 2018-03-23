package bitcamp.java106.pms2.domain;

import java.sql.Date;

public class Team {
    public int no;
    public String name;
    public String description;
    public int maxQty;
    public Date startDate;
    public Date endDate;
    
    public Member[] members = new Member[10];
    
    public int addMember(Member memberId) {
        for(int i = 0; i < this.members.length; i++) {
            if(this.members[i] == null) {
                this.members[i] = memberId;
                return 1;
            }
        }
        return 0;
    }
    
    
    public int deleteMember(String memberId) {
        for(int i = 0; i < this.members.length; i++) {
            if(this.members[i] == null) continue;
            if (this.members[i].id.equals(memberId)) {
                this.members[i] = null;
                System.out.println("삭제하였습니다.");
                return 1;
            }
        }
        return 0;
    }
    
    public boolean isExist(String memberId) {
        for(int i = 0; i < this.members.length; i++) {
            if(this.members[i] == null) continue;
            if(this.members[i].id.equals(memberId)) {
                return true;
            }
        }
        return false;
    }
}