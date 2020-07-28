package com.spider.school;


import com.spider.school.Model.School;
import com.spider.school.Util.RedisUtil;

import com.spider.school.service.SpiderSchoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Resource
    private SpiderSchoolService spiderSchoolService;

    @Resource
    RedisUtil redisUtil;

    @Test
    public void testGet() {//获取ID 1 ~ 4000的高校，实际没有这么多 相当于获取所有中国高校了
        List<School> schools = new ArrayList<>();
        try {
            for (int i = 1; i < 4000; i++) {
                School school = new School();
                school = spiderSchoolService.spiderSchoolInfo(i, school);
                if (Objects.nonNull(school)) {
                    System.out.println(school);
                    schools.add(school);
                }
                Thread.sleep(5);
                System.out.println(i);
            }
            redisUtil.set("School", schools);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取所有理科相关信息
    @Test
    public void getProfessionalScore() {
        List<School> schools = (List<School>) redisUtil.get("School");
        try {
            for (School school : schools) {
                System.out.println(school.getSchool_id());
                spiderSchoolService.getPScore(school.getSchool_id(), "li", school);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtil.set("School", schools);
    }

    @Test
    public void getTargetSchool() {
//        spiderSchoolService.getTargetSchool(329,"应用英语","jia");
//        spiderSchoolService.filterSchool("jia","应用英语");
//        spiderSchoolService.getTargetSchool(481,"计算机","LYC");
//        spiderSchoolService.filterSchool("LYC","计算机");
        System.out.println(redisUtil.get("LYCTargetSchool"));
    }

}
