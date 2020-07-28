package com.spider.school.Model;

import java.util.*;

public class School {
    private String school_id;//学校ID
    private String schoolName;//学校名称
    private List<Score> scores;
    private Double minScore = 0.0;
    protected Map<String,List<ProfessionalScore>> liScore;
    protected Map<String,List<ProfessionalScore>> wenScore;

    public School() {

    }
    public void initMap(){
        this.wenScore=new HashMap<String,List<ProfessionalScore>>();
        this.liScore=new HashMap<String,List<ProfessionalScore>>();
        for (int year=2019;year>2014;year--) {
            this.wenScore.put(year + "", new ArrayList<ProfessionalScore>());
            this.liScore.put(year + "", new ArrayList<ProfessionalScore>());
        }
    }

    public Map<String, List<ProfessionalScore>> getLiScore() {
        return liScore;
    }

    public void setLiScore(Map<String, List<ProfessionalScore>> liScore) {
        this.liScore = liScore;
    }

    public Map<String, List<ProfessionalScore>> getWenScore() {
        return wenScore;
    }

    public void setWenScore(Map<String, List<ProfessionalScore>> wenScore) {
        this.wenScore = wenScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }


    public void putPScore(String year,String key, List<ProfessionalScore> pScore) {

        if (key.equals("wen")) {
            this.wenScore.get(year).addAll(pScore);
        } else {
            this.liScore.get(year).addAll(pScore);
        }
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public void setScoresAndMin(List<Score> scores) {
        this.scores = scores;
        for (Score score : scores) {
            if (score.getLocal_batch_name().contains("本科") && score.getYear().equals("2019")) {
                this.minScore = Double.parseDouble(score.getMin());
            }
        }
        if (this.minScore == 0.0) {
            this.minScore = Double.parseDouble(scores.get(0).getMin());
        }
    }

    @Override
    public String toString() {
        return "School{" +
                "school_id='" + school_id + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", minScore='" + minScore + '\'' +
                ", scores=" + "\n\t 年份\t最高分\t平均分\t最低分\t最低位次\t省控线\t招生类型\t录取批次" + "\n" +
                scores +
                ",liScore=" + "\n" + "\t专业名称\t最高分\t平均分\t最低分\t最低位次\t录取批次" +
                liScore +
                ",wenScore=" + "\n" + "\t专业名称\t最高分\t平均分\t最低分\t最低位次\t录取批次" +
                wenScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (school_id != null ? !school_id.equals(school.school_id) : school.school_id != null) return false;
        if (schoolName != null ? !schoolName.equals(school.schoolName) : school.schoolName != null) return false;
        if (scores != null ? !scores.equals(school.scores) : school.scores != null) return false;
        if (minScore != null ? !minScore.equals(school.minScore) : school.minScore != null) return false;
        if (liScore != null ? !liScore.equals(school.liScore) : school.liScore != null) return false;
        return wenScore != null ? wenScore.equals(school.wenScore) : school.wenScore == null;
    }

    @Override
    public int hashCode() {
        int result = school_id != null ? school_id.hashCode() : 0;
        result = 31 * result + (schoolName != null ? schoolName.hashCode() : 0);
        result = 31 * result + (scores != null ? scores.hashCode() : 0);
        result = 31 * result + (minScore != null ? minScore.hashCode() : 0);
        result = 31 * result + (liScore != null ? liScore.hashCode() : 0);
        result = 31 * result + (wenScore != null ? wenScore.hashCode() : 0);
        return result;
    }
}
