package com.back;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    String cmd = "";
    ArrayList<Quote> quotes = new ArrayList<>();

    public void start() {
        // 시작
        System.out.println("== 명언 앱 ==");
        // "종료" 입력 전까지 반복
        while (!cmd.equals("종료")) {
            System.out.print("명령) ");
            cmd = scanner.nextLine().trim();

            switch (cmd) {
                case "등록":
                    handleRegister();
                    break;

                case "목록":
                    handleList();
                    break;
                default:
                    // 삭제명령어
                    handleDelete();
                    // 수정명령어
                    handleEdit();
            }

        }

    }

    // 명언 등록
    private void handleRegister() {
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        // 특수문자 조건
        if (Util.isInValidContent(content)) {
            System.out.println("특수문자는 사용할 수 없습니다.");
            return;
        }
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();
        // 명언 객체 생성
        Quote quote = new Quote(content, author);
        // ArrayList에 추가
        quotes.add(quote);

        System.out.println(quote.getId() + "번 명언이 등록되었습니다.");
    }

    // 명언 목록
    private void handleList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");
        // ArrayList에 저장된 명언 출력(내림차순)
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quote q = quotes.get(i);
            System.out.println(q.getId() + " / " + q.getAuthor() + " / " + q.getContent());
        }
    }

    // 명언 삭제
    private void handleDelete() {
        // 삭제명령어
        if (cmd.startsWith("삭제?id=")) {
            // id값 추출
            int getLoc = cmd.indexOf("=");
            String idStr = cmd.substring(getLoc + 1); // id값(String)
            // id삭제 예외처리
            try {
                int idInt = Integer.parseInt(idStr); // id값(int)
                boolean found = false;
                for (int i = 0; i < quotes.size(); i++) {
                    Quote q = quotes.get(i);
                    // id값이 같으면 삭제
                    if (q.getId() == idInt) {
                        quotes.remove(i);
                        System.out.println(idInt + "번 명언이 삭제되었습니다.");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println(idInt + "번 명언은 존재하지 않습니다.");
                }
            }
            // id값이 숫자가 아닐 경우
            catch (NumberFormatException e) {
                System.out.println("id는 숫자여야 합니다.");
            }
        }
    }

    // 명언 수정
    private void handleEdit() {
        if (cmd.startsWith("수정?id=")) {
            // id값 추출
            int getLoc = cmd.indexOf("=");
            String idStr = cmd.substring(getLoc + 1); // id값(String)
            int idInt = Integer.parseInt(idStr); // id값(int)
            boolean found = false;
            for (int i = 0; i < quotes.size(); i++) {
                Quote q = quotes.get(i);
                // id값이 같으면 수정
                if (q.getId() == idInt) {
                    System.out.println("명언(기존) : " + q.getContent());
                    System.out.print("명언 : ");
                    String content2 = scanner.nextLine().trim();
                    if (Util.isInValidContent(content2)) {
                        System.out.println("특수문자는 사용할 수 없습니다.");
                        break;
                    }
                    System.out.println("작가(기존) : " + q.getAuthor());
                    System.out.print("작가 : ");
                    String author2 = scanner.nextLine().trim();
                    // 명언, 저자 갱신
                    q.setContent(content2);
                    q.setAuthor(author2);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println(idInt + "번 명언은 존재하지 않습니다.");
            }
        }
    }
}
