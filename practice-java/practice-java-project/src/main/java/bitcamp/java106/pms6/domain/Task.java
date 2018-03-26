package bitcamp.java106.pms6.domain;

import java.util.Date;

public class Task {
    private int no;
    private String workName;
    private Date startDate;
    private Date endDate;
    private String worker;
    
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getWorkName() {
        return workName;
    }
    public void setWorkName(String workName) {
        this.workName = workName;
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
    public String getWorker() {
        return worker;
    }
    public void setWorker(String worker) {
        this.worker = worker;
    }



}
