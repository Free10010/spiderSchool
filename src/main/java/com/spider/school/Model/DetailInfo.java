package com.spider.school.Model;

public class DetailInfo {
    private String province_name;//所在地
    private String level_name;//类型
    private String name;//名称
    private String view_total;//高校热度
    private String answerurl;//咨询链接
    private Double min;//最低分

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView_total() {
        return view_total;
    }

    public void setView_total(String view_total) {
        this.view_total = view_total;
    }

    public String getAnswerurl() {
        return answerurl;
    }

    public void setAnswerurl(String answerurl) {
        this.answerurl = answerurl;
    }

    @Override
    public String toString() {
        return
                ", 学校名称='" + name + '\'' +
                "所在地='" + province_name + '\'' +
                ", 类型='" + level_name + '\'' +
                ", 最低分='" + min + '\'' +
                ", 热度='" + view_total + '\'' +
                ", 咨询链接='" + answerurl + '\'' +
                '\n';
    }
}
