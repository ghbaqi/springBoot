package com.example.demo.entity;

//@Table(name = "learn_resource")
public class LearnResouce {



    /**
     * 作者
     */
    private String author;

    /**
     * 描述
     */
    private String title;

    /**
     * 地址链接
     */
    private String url;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LearnResouce() {
    }

    public LearnResouce(String author, String title, String url) {
        this.author = author;
        this.title = title;
        this.url = url;
    }
}