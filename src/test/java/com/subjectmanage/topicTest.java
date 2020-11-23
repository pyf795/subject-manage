package com.subjectmanage;

import com.subjectmanage.beans.Group;
import com.subjectmanage.beans.PageElement;
import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Topic;
import com.subjectmanage.mapper.GroupMapper;
import com.subjectmanage.mapper.TopicMapper;
import com.subjectmanage.services.GroupServiceImpl;
import com.subjectmanage.services.StudentServiceImpl;
import com.subjectmanage.services.TopicService;
import com.subjectmanage.services.TopicServiceImpl;
import com.subjectmanage.utils.LayuiTableData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SpringBootTest(classes= OurprjApplication.class)
 class topicTest {
    @Resource
    private StudentServiceImpl studentService;

    @Resource
    private TopicServiceImpl topicService;

    @Resource
    private GroupServiceImpl groupService;
    @Test
    void topicTest1() {
       List<Group> groups = groupService.getGroupWithTopic(1);
        System.out.println(groups);
    }

    @Test
    void test2() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> clearInfo = new HashMap<>();
        clearInfo.put("clearUrl", "api/clear.json");
        map.put("clearInfo", clearInfo);
        Map<String, Object> homeInfo = new HashMap<>();
        homeInfo.put("title", "首页");
        homeInfo.put("icon", "fa fa-home");
        homeInfo.put("href", "page/welcome-1.html");
        map.put("homeInfo", homeInfo);
        Map<String, Object> logoInfo = new HashMap<>();
        logoInfo.put("title", "layuiAdmin");
        logoInfo.put("image", "images/logo.png");
        logoInfo.put("href", "");
        map.put("logoInfo", logoInfo);

        Map<String, Object> menuInfo = new LinkedHashMap<>();//保证菜单的顺序
        Map<String, Object> child = new HashMap<>();


        PageElement element2 = new PageElement();
        element2.setTitle("个人信息修改");
        element2.setIcon("fa fa-home");
        element2.setTarget("_self");
        element2.setHref("");
        child.put("child1", element2);

        PageElement element1 = new PageElement();
        element1.setTitle("欢迎您");
        element1.setIcon("fa fa-address-book");
        element1.setTarget("_self");
        element1.setHref("");
        element1.setChild(child);

        List<PageElement> elements = new ArrayList<PageElement>();
        elements.add(element1);

        menuInfo.put("menu", elements);


        map.put("menuInfo", menuInfo);
        System.out.println(map);


    }

    @Test
    void test3(){
        Map<String,Object> map=new HashMap<>();
        Map<String,Object>clearInfo=new HashMap<>();
        clearInfo.put("clearUrl","api/clear.json");
        map.put("clearInfo",clearInfo);
        Map<String,Object>homeInfo=new HashMap<>();
        homeInfo.put("title","首页");
        homeInfo.put("icon","fa fa-home");
        homeInfo.put("href","page/welcome-1.html");
        map.put("homeInfo",homeInfo);
        Map<String,Object>logoInfo=new HashMap<>();
        logoInfo.put("title","layuiAdmin");
        logoInfo.put("image","images/logo.png");
        logoInfo.put("href","");
        map.put("logoInfo",logoInfo);

        List<Map<String,Object>>menuInfo=new ArrayList<>();//保证菜单的顺序
        List<Map<String,Object>>childInfo=new ArrayList<>();



        Map<String,Object>aInfo=new HashMap<>();
        aInfo.put("title","个人信息修改");
        aInfo.put("icon","fa fa-home");
        aInfo.put("href","");
        childInfo.add(aInfo);

        Map<String,Object>bInfo=new HashMap<>();
        bInfo.put("title","查看所有课题");
        bInfo.put("icon","fa fa-window-maximize");
        bInfo.put("href","");
        childInfo.add(bInfo);



        Map<String,Object>cInfo=new HashMap<>();
        cInfo.put("title","欢迎你");
        cInfo.put("icon","fa fa-address-book");
        cInfo.put("href","");
        cInfo.put("target","_self");
        cInfo.put("child",childInfo);

        menuInfo.add(cInfo);


        map.put("menuInfo",menuInfo);
        System.out.println(map);
    }


}
