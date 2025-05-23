package com.back;

// 명언 클래스
public class Quote {
    private String content;
    private String author;
    private int id = 1;
    private static int idCounter = 1;

    public Quote(String content, String author) {
        this.content = content;
        this.author = author;
        this.id = idCounter;
        idCounter++;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}