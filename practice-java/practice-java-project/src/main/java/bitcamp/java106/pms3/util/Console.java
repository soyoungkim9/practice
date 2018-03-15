package bitcamp.java106.pms3.util;

import java.util.Scanner;

public class Console {
    static Scanner keyScan;

    // y/N여부를 확인하는 메서드
    public static boolean confirm(String s) {
        System.out.printf("%s(y/N) ", s);
        String input = keyScan.nextLine();
        
        if(input.equals("y"))
            return true;
        else
            return false;
    }

    // 명령어를 입력받는 메서드
    public static String[] prompt() {
        System.out.print("명령> ");
        return keyScan.nextLine().toLowerCase().split(" ");
    }

}