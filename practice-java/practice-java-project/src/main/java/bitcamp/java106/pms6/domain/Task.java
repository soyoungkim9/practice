package bitcamp.java106.pms6.domain;

import java.sql.Date;

public class Task {
    public static final int READY = 0;
    public static final int WORKING = 1;
    public static final int COMPLETE = 9;
    
    private int no;
    private String subject;
    private Date startDate;
    private Date endDate;
    private int state;
    private Team team;
    private Member worker;
    
    public Task(Team team) {
        this.team = team;
    }
    
    public Task(Team team, String subject, Date startDate, Date endDate) {
        this.team = team;
        this.subject= subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = Task.READY;
    }
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
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
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Member getWorker() {
        return worker;
    }

    public void setWorker(Member worker) {
        this.worker = worker;
    }

    @Override
    public String toString() {
        return "Task [subject=" + subject + ", startDate=" + startDate + ", endDate=" + endDate + ", worker=" + worker
                + "]";
    }
    
    
    
}
