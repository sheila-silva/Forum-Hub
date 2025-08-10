package br.com.forum.forumhub.dto;

public class TopicFilter {
    private String course;
    private Integer year;


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}