package bitcamp.java106.pms6.dao;

import bitcamp.java106.pms6.domain.Team;

public class TeamDao {
    Team[] teams = new Team[1000]; // 레퍼런스 생성
    int tCount = 0; // team인원 등록 수
    
    private int getTeamIndex(String option) {
        for(int i = 0; i < this.tCount; i++) {
            if(this.teams[i] == null) continue;
            if(option.equals(this.teams[i].getName().toLowerCase())) {
                return i;
            }
        } 
        return -1;
    }
    
    public void insert(Team team) {
        team.setNo(tCount);
        this.teams[this.tCount++] = team;
    }
    
    public Team[] list() {
        Team[] arr = new Team[tCount];
        for(int i = 0; i < tCount; i++)
            arr[i] = this.teams[i];
        return arr;
    }
    
    public Team get(String option) {
        int i = this.getTeamIndex(option);
        
        if(i == -1)
            return null;
        return teams[i];
    }
    
    public void update(Team team) {
        this.teams[team.getNo()] = team;
    }
    
    public void delete(String option) {
        int i = this.getTeamIndex(option);
        if(i != -1)
            teams[i] = null;        
    }
}




