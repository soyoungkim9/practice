package bitcamp.java106.pms5.domain;

import java.sql.Date;

public class Team {
    public int no;
    public String name;
    public String description;
    public int maxQty;
    public Date startDate;
    public Date endDate;
    public Member[] members = new Member[10];
    
    public boolean isExist(String membersId) {
        for(int i = 0; i < this.members.length; i++) {
            if(this.members[i] == null) continue;
            if(this.members[i].id.equals(membersId))
                return true;
        }
        return false;
    }
    
    public int addMember(Member member) {
        for(int i = 0; i < this.members.length; i++) {
            if (this.members[i] == null) {
                this.members[i] = member;
                return 1;
            }
        }
        return 0;
    }
    
    public int deleteMember(String memberId) {
        for(int i = 0; i < this.members.length; i++) {
            if(this.members[i] == null) continue;
            if(members[i].id.equals(memberId)) {
                members[i] = null;
                return 1;
            }
        }
        return 0;
    }
    

}
