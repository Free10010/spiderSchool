package com.spider.school.Model;

public class ProfessionalScore {
    private String spname;//专业名称
    private String average;//平均分
    private String min; //最低分
    private String min_section;//最低位次
    private String local_batch_name;//录取批次
    private String year;

    public ProfessionalScore() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSpname() {
        return spname;
    }

    public void setSpname(String spname) {
        this.spname = spname;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMin_section() {
        return min_section;
    }

    public void setMin_section(String min_section) {
        this.min_section = min_section;
    }

    public String getLocal_batch_name() {
        return local_batch_name;
    }

    public void setLocal_batch_name(String local_batch_name) {
        this.local_batch_name = local_batch_name;
    }

    @Override
    public String toString() {
        return
                spname + '\t' +
                average + '\t' +
                 min + '\t' +
                 min_section + '\t' +
                 local_batch_name + '\t' +
                '\n';
    }
}
