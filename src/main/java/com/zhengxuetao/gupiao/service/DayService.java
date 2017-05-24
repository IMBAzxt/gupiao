/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhengxuetao.gupiao.service;

import com.zhengxuetao.gupiao.entity.DayData;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ThinkPad
 */
public interface DayService {

    /**
     * 保存天数据
     *
     * @param dayData
     * @return
     */
    public boolean saveDayData(DayData dayData);

    /**
     * 获取某个id的时间范围数据, 按时间的升序排序。
     *
     * @param id
     * @param startDate
     * @param endDate
     * @return
     */
    public List<DayData> findDayDataList(Long id, Timestamp startDate, Timestamp endDate);

    /**
     * 从文件中保存数据到数据库
     *
     * @param file
     * @return
     */
    public boolean saveDayDataFromFile(File file);
}
