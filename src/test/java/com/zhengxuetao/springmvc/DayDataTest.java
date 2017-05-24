/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhengxuetao.springmvc;

import com.zhengxuetao.gupiao.entity.DayData;
import com.zhengxuetao.gupiao.service.DayService;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ThinkPad
 */
public class DayDataTest {

    ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
    }

    @Test
    public void testSaveDayData() {
        File file = new File("src\\main\\resources\\SH#600009.txt");
        LoggerFactory.logger(DayDataTest.class).info(file.getAbsolutePath());
        DayService dayService = context.getBean("dayService", DayService.class);
        dayService.saveDayDataFromFile(file);
    }

    @Test
    public void testFindDayData() {
        DayService dayService = context.getBean("dayService", DayService.class);
        List<DayData> list = dayService.findDayDataList(600009l, Timestamp.valueOf("2017-05-01 00:00:00"), Timestamp.valueOf("2017-05-25 00:00:00"));
        LoggerFactory.logger(DayDataTest.class).info("DayData size:" + list.size());
    }

}
