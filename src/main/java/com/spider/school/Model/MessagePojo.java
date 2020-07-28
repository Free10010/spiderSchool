package com.spider.school.Model;


import java.util.Objects;

public class MessagePojo {
    private String title;
    private String contents;

    @Override
    public String toString() {
        return "MessagePojo{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagePojo that = (MessagePojo) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(contents, that.contents) &&
                Objects.equals(Url, that.Url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents, Url);
    }

    private String Url;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
