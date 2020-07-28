package com.spider.school.service;

import com.spider.school.Model.School;

import java.io.IOException;


public interface SpiderSchoolService {

    /**
     * 爬取学校信息
     * @param id 学校的id
     * @param school  学校
     * @return
     * @throws IOException
     */
    School spiderSchoolInfo(int id, School school) throws IOException;

    /**
     * 获取目标学校的历年分数
     * @param id
     * @param key 文理  wen || li
     * @param school  学校
     * @throws IOException
     */
    void getPScore(String id, String key, School school) throws IOException;

    /**
     * 获取一个范围内的学校，比如本科 理科是415是分数线   415 <= score <= targetScore+20
     *
     * @param score
     * @param professional
     * @param name
     */
    void getTargetSchool(double score, String professional, String name);

    /**
     * 根据学生已获取的学校，根据专业名称过滤出目标学校
     *
     * @param name         考生名称。建议考生名字大写缩写
     * @param professional 要报考的专业
     */
    void filterSchool(String name, String professional);

}
