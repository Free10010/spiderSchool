package com.spider.school.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.spider.school.Model.School;
import com.spider.school.Model.Score;
import com.spider.school.Util.RedisUtil;
import com.spider.school.service.SpiderSchoolService;
import com.spider.school.Model.DetailInfo;
import com.spider.school.Model.ProfessionalScore;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
public class SpiderSchoolServiceImpl implements SpiderSchoolService {
    private static final Logger logger = LoggerFactory.getLogger(SpiderSchoolServiceImpl.class);
    @Resource
    RedisUtil redisUtil;

    @Override
    public School spiderSchoolInfo(int id, School school) {
        if (getSchool(id, school) != 1) {
            System.out.println("获取id：" + id + "失败");
            return null;
        }
        try {
            getScore(id, school);
            return school;
        } catch (JSONException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private int getSchool(int id, School school) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient实例
            HttpGet httpGet = new HttpGet("https://static-data.eol.cn/www/2.0/school/" + id + "/info.json");//创建HttpGet实例
            logger.info("URL: {}", httpGet.getURI());
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            CloseableHttpResponse response;
            response = httpClient.execute(httpGet);//执行HttpGet请求
            HttpEntity entity = response.getEntity();//获取返回实体
            String content = EntityUtils.toString(entity, "UTF-8");//获取网页内容 并且指定了编码
            if (content.length() == 2) {
                return 2;
            }
            JSONObject jsonObject = JSON.parseObject(content);
            if (jsonObject.get("message").toString().equals("成功")) {
                school.setSchoolName(jsonObject.getJSONObject("data").getString("name"));
                school.setSchool_id(id + "");
                return 1;
            } else {
                return 2;
            }
        } catch (JSONException e) {
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void getScore(int id, School school) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient实例
        HttpGet httpGet = new HttpGet("https://static-data.eol.cn/www/2.0/schoolprovinceindex/detial/" + id + "/13/1/1.json");//创建HttpGet实例
        logger.info("URL: {}", httpGet.getURI());
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");
        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        CloseableHttpResponse response;
        response = httpClient.execute(httpGet);//执行HttpGet请求
        HttpEntity entity = response.getEntity();//获取返回实体
        String content = EntityUtils.toString(entity, "UTF-8");//获取网页内容 并且指定了编码
        JSONObject jsonObject = JSON.parseObject(content);
        if (jsonObject.get("message").toString().equals("成功")) {
            JSONArray data = jsonObject.getJSONObject("data").getJSONArray("item");
            List<Score> score;
            score = data.toJavaList(Score.class);
            school.setScores(score);
        }
    }

    //https://static-data.eol.cn/www/2.0/schoolspecialindex/2019/459/13/2/1.json 文科分数
    //https://static-data.eol.cn/www/2.0/schoolspecialindex/2019/459/13/2/2.json 文科分数

    //https://static-data.eol.cn/www/2.0/schoolspecialindex/2019/459/13/1/1.json 理科分数
    //https://static-data.eol.cn/www/2.0/schoolspecialindex/2019/459/13/1/2.json 理科分数

    /**
     * @param id     学校id
     * @param key    文理科 wen || li
     * @param school 学校
     * @throws IOException
     */
    @Override
    public void getPScore(String id, String key, School school) throws IOException {
        school.initMap();
        for (int year = 2019; year > 2014; year--) {
            int page = 1;
            while (true) {
                CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient实例
                HttpGet httpGet;
                if (key.equals("li")) {
                    httpGet = new HttpGet("https://static-data.eol.cn/www/2.0/schoolspecialindex/" + year + "/" + id + "/13/1/" + page + ".json");//理科
                } else {
                    httpGet = new HttpGet("https://static-data.eol.cn/www/2.0/schoolspecialindex/" + year + "/" + id + "/13/2/" + page + ".json");//文科
                }
                logger.info("URL: {}", httpGet.getURI());
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");
                httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
                CloseableHttpResponse response;
                response = httpClient.execute(httpGet);//执行HttpGet请求
                HttpEntity entity = response.getEntity();//获取返回实体
                String content = EntityUtils.toString(entity, "UTF-8");//获取网页内容 并且指定了编码
                if (content.length() == 2) {
                    break;
                }
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.get("message").toString().equals("成功")) {
                    JSONArray data = jsonObject.getJSONObject("data").getJSONArray("item");
                    int dataNumber = jsonObject.getJSONObject("data").getInteger("numFound");
                    List<ProfessionalScore> professionalScore ;
                    professionalScore = data.toJavaList(ProfessionalScore.class);
                    school.putPScore(year + "", "li", professionalScore);
                    if (page * 10 < dataNumber) {
                        page++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    /**
     *
     * @param score 考生分数
     * @param professional 要报考的专业
     * @param name  比如张三 为redis key 做唯一标识
     */
    @Override
    public void getTargetSchool(double score, String professional, String name) {
        List<School> schools = (List<School>) redisUtil.get("School");
        List<School> targetSchool = new ArrayList<>();
        Double min;
        Double max = score + 20;
        String keyWord;
        if (score > 415.0) {
            min = 415.0;
            keyWord = "本";
        } else {
            min = 200.0;
            keyWord = "专";
        }
        try {
            for (School school : schools) {
                Double temp = 0.0;
                for (ProfessionalScore professionalScore : school.getLiScore().get(2019 + "")) {
                    if (professionalScore.getLocal_batch_name().contains(keyWord)) {
                        if (!professionalScore.getMin().contains("-")) {
                            temp = Double.parseDouble(professionalScore.getMin());
                        }else{
                            temp=score;
                        }
                    }
                }
                if (temp > min && temp < max) {
                    school.getLiScore().get(2019 + "").stream().forEach((professionalScore) -> {
                        if ((professionalScore.getSpname()).contains(professional) && professionalScore.getLocal_batch_name().contains(keyWord)) {
                            Double tempScore=0.0;
                            if (professionalScore.getMin().equals("-")){
                                tempScore =Double.parseDouble(professionalScore.getAverage());
                            }else{
                                 tempScore = Double.parseDouble(professionalScore.getMin());
                            }
                            if (tempScore > min && tempScore <= max) {
                                targetSchool.add(school);
                                System.out.println(professionalScore);
                                System.out.println(school.getSchool_id() + "  " + school.getSchoolName());
                            }
                        }
                    });
                }
            }
            redisUtil.set(name + "School", targetSchool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void filterSchool(String name,String professional) {
        List<School> schools = (List<School>) redisUtil.get(name + "School");
        System.out.println(schools.size());
        Set<School> sets = new HashSet<>();
        sets.addAll(schools);
        schools.clear();
        schools.addAll(sets);
        System.out.println(schools.size());
        List<String> resultList = new ArrayList<>();
        List<DetailInfo> professionalScore = new ArrayList<>();
        try {
            for (School school : schools) {
                CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建HttpClient实例
                HttpGet httpGet;
                httpGet = new HttpGet("https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&is_recruitment=1&keyword=" + URLEncoder.encode(school.getSchoolName(), "UTF-8") + "&page=1&province_id=&request_type=1&school_type=&signsafe=&size=20&sort=view_total&type=&uri=apigkcx/api/school/hotlists");//理科
                logger.info("URL: {}", httpGet.getURI());
                httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.90 Safari/537.36");
                httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
                CloseableHttpResponse response;
                response = httpClient.execute(httpGet);//执行HttpGet请求
                HttpEntity entity = response.getEntity();//获取返回实体
                String content = EntityUtils.toString(entity, "UTF-8");//获取网页内容 并且指定了编码
                JSONObject jsonObject = JSON.parseObject(content);
                if (jsonObject.get("message").toString().equals("获取列表成功")) {
                    JSONArray data = jsonObject.getJSONObject("data").getJSONArray("item");
                    List<DetailInfo> tempList = data.toJavaList(DetailInfo.class);
                    for (DetailInfo detailInfo : tempList) {
                        if (school.getSchoolName().contains(detailInfo.getName())) {
                            List<ProfessionalScore> professionalScores = school.getLiScore().get(2019+"");
                            for (ProfessionalScore score : professionalScores) {
                                if (score.getSpname().contains(professional)) {
                                    if (score.getMin().equals("-")){
                                        detailInfo.setMin(Double.parseDouble(score.getAverage()));
                                    }else{
                                        detailInfo.setMin(Double.parseDouble(score.getMin()));

                                    }
                                    break;
                                }
                            }
                            professionalScore.add(detailInfo);
                        }
                    }
                }
            }
            Collections.sort(professionalScore, (DetailInfo d1, DetailInfo d2) -> {
                return (int) ((Double.parseDouble(d2.getView_total().substring(0, d2.getView_total().length() - 1))) - Double.parseDouble(d1.getView_total().substring(0, d1.getView_total().length() - 1)));
            });
            for (DetailInfo detailInfo : professionalScore) {
                resultList.add(detailInfo.toString());
            }
            redisUtil.set(name + "TargetSchool", resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
