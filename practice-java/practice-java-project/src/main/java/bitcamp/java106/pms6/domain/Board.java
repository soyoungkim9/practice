package bitcamp.java106.pms6.domain;

import java.sql.Date;

public class Board {
    private int no;
    private String subject;
    private String content;
    private Date date;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    
}