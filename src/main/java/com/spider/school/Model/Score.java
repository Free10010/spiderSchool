package com.spider.school.Model;

public class Score {
    private String year;//年份
    private String min;//最低分
    private String max;//最高分
    private String average;//平均分
    private String min_section;//最低位次
    private String proscore;//省控线
    private String zslx_name;//招生类型
    private String local_batch_name;//录取批次
    private String school_id;//学校id

    public Score() {
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getMin_section() {
        return min_section;
    }

    public void setMin_section(String min_section) {
        this.min_section = min_section;
    }

    public String getProscore() {
        return proscore;
    }

    public void setProscore(String proscore) {
        this.proscore = proscore;
    }

    public String getZslx_name() {
        return zslx_name;
    }

    public void setZslx_name(String zslx_name) {
        this.zslx_name = zslx_name;
    }

    public String getLocal_batch_name() {
        return local_batch_name;
    }

    public void setLocal_batch_name(String local_batch_name) {
        this.local_batch_name = local_batch_name;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    @Override
    public String toString() {
        //  "年份\t最高分\t平均分\t最低分\t最低位次\t省控线\t招生类型\t录取批次");
        return
                '\t'+year + '\t' +
                        max + '\t' +
                        average + '\t'+'\t' +
                        min + '\t' +
                        min_section + '\t' +
                        proscore + '\t'+'\t' +
                        zslx_name + '\t' +
                        local_batch_name + '\t' +
                        "学校id："+school_id+'\n'
                ;
    }
}
