package bitcamp.java106.pms5.dao;

import bitcamp.java106.pms2.domain.Team;

public class TeamDao {
    Team[] teams = new Team[1000]; // 레퍼런스 생성
    int tCount = 0; // team인원 등록 수
    
    public void insert(Team team) {
        team.no = tCount;
        this.teams[this.tCount++] = team;
    }
    
    public Team[] list() {
        Team[] arr = new Team[tCount];
        for(int i = 0; i < tCount; i++)
            arr[i] = this.teams[i];
        return arr;
    }
    
    public int get(String option) {
        for(int i = 0; i < this.tCount; i++) {
            if(this.teams[i] == null) continue;
            if(option.equals(this.teams[i].name.toLowerCase())) {
                return i;
            }
        } 
        return -1;
    }
    
    public Team view(int i) {
        return teams[i];
    }
    
    public void update(Team team) {
        this.teams[team.no] = team;
    }
    
    public void delete(int i) {
        this.teams[i] = null;
    }
}




