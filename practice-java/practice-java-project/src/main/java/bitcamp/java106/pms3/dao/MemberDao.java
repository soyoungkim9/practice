package bitcamp.java106.pms3.dao;

import bitcamp.java106.pms2.domain.Member;

public class MemberDao {
    Member[] members = new Member[1000];
    int mCount = 0;
    
    // 회원 데이터 등록하는 메서드
    public void insert(Member member) {
        member.no = mCount;
        this.members[this.mCount++] = member;
    }
    
    // 모든 회원 데이터 출력
    public Member[] list() {
        Member[] arr = new Member[mCount];
        for(int i = 0; i < this.mCount; i++)
            arr[i] = members[i];
        return arr;
    }
    
    public int get(String option) {
        for(int i = 0; i < this.mCount; i ++) {
            if(this.members[i] == null) continue;
            if(option.equals(this.members[i].id.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
    public Member view(int i) {
        return members[i];
    }
    
    public void update(Member member) {
        this.members[member.no] = member;
    }
    
    public void delete(int i) {
        this.members[i] = null;
    }
}
