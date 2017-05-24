/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhengxuetao.gupiao.service.impl;

import com.zhengxuetao.gupiao.dao.DayDAO;
import com.zhengxuetao.gupiao.entity.DayData;
import com.zhengxuetao.gupiao.service.DayService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThinkPad
 */
@Service
public class DayServiceImpl implements DayService {

    private static Logger logger = LoggerFactory.getLogger(DayServiceImpl.class);

    @Autowired
    DayDAO userDAO;

    @Override
    public boolean saveDayData(DayData dayData) {
        return userDAO.saveDayData(dayData);
    }

    @Override
    public List<DayData> findDayDataList(Long id, Timestamp startDate, Timestamp endDate) {
        return userDAO.findDayDataList(id, startDate, endDate);
    }

    @Override
    public boolean saveDayDataFromFile(File file) {
        if (file != null && file.exists() && file.isFile()) {
            DayData dayData = new DayData();
            String fileName = file.getName();  //SH#600004.txt
            dayData.setFinanceId(Long.parseLong(fileName.substring(3, 9)));
            String encoding = "GBK";
            InputStreamReader reader = null;
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            DateFormat parseFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String readLine;
                while ((readLine = bufferedReader.readLine()) != null) {
                    //时间、开盘、最高、最低、收盘、成交量、成交额
                    String[] arr = readLine.split(",");
                    dayData.setTime(Timestamp.valueOf(format.format(parseFormat.parse(arr[0]))));
                    dayData.setStartPrice(Float.valueOf(arr[1]));
                    dayData.setHighPrice(Float.valueOf(arr[2]));
                    dayData.setLowPrice(Float.valueOf(arr[3]));
                    dayData.setEndPrice(Float.valueOf(arr[4]));
                    dayData.setVolume(Long.valueOf(arr[5]));
                    dayData.setAmount(Double.valueOf(arr[6]));
                    userDAO.saveDayData(dayData);
                }
            } catch (IOException | ParseException ex) {
                logger.error(ex.getMessage());
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            }
        } else {
            logger.error("文件不存在!");
        }
        return true;
    }


}
