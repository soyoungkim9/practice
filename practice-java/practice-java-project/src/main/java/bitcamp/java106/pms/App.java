package bitcamp.java106.pms;

import bitcamp.java106.pms.domain.Team;
import java.util.Scanner;

public class App {
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);

        int count = 0;
        String answer;
        Team[] team = new Team[5]; // 레퍼런스 생성
        
        for(int i = 0; i < team.length; i++) {
            team[i] = new Team(); // 인스턴스 생성

            System.out.print("팀명? ");
            team[i].name = scan.nextLine();
            System.out.print("설명? ");
            team[i].description = scan.nextLine();
            System.out.print("최대인원? ");
            team[i].maxNum = scan.nextInt();
            scan.nextLine(); // 개행문자 제거
            System.out.print("시작일? ");
            team[i].startDate = scan.nextLine();
            System.out.print("종료일? ");
            team[i].endDate = scan.nextLine();

            count++;
            if(count == team.length) break;
            System.out.print("계속 입력하시겠습니까?(Y/n)");
            answer = scan.nextLine();
            if(answer.toUpperCase().equals("Y")) continue;
            else if(answer.toLowerCase().equals("n")) break;
        }

        System.out.println("------------------------------");

        for(int i = 0; i < count; i++) {
            System.out.printf("%s, %d, %s ~ %s",
                team[i].name,
                team[i].maxNum,
                team[i].startDate,
                team[i].endDate);
        }

    }
}