package bitcamp.java106.pms5.dao;

import bitcamp.java106.pms5.domain.Member;

public class MemberDao {
    Member[] members = new Member[1000];
    int mCount = 0;
    
    // 회원명과 일치하느 인덱스 번호를 찾는 메서드
    private int getMemberIndex(String option) {
        for(int i = 0; i < this.mCount; i ++) {
            if(this.members[i] == null) continue;
            if(option.equals(this.members[i].id.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
    
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
    
    public Member get(String option) {
        int i = getMemberIndex(option);
        
        if(i == -1)
            return null;
        return members[i];
    }
    
    public void update(Member member) {
        this.members[member.no] = member;
    }
    
    public void delete(String option) {
        int i = getMemberIndex(option);
        
        if(i != -1)
            this.members[i] = null;
    }
}
