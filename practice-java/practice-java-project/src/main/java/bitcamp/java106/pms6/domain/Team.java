package bitcamp.java106.pms6.domain;

import java.sql.Date;

public class Team {
    private int no;
    private String name;
    private String description;
    private int maxQty;
    private Date startDate;
    private Date endDate;
    private Member[] members = new Member[10];
    
    public boolean isExist(String membersId) {
        for(int i = 0; i < this.members.length; i++) {
            if(this.members[i] == null) continue;
            if(this.members[i].getId().equals(membersId))
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
            if(members[i].getId().equals(memberId)) {
                members[i] = null;
                return 1;
            }
        }
        return 0;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Member[] getMembers() {
        return members;
    }
    

}
