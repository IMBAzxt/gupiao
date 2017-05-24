/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhengxuetao.gupiao.dao;

import com.zhengxuetao.gupiao.entity.DayData;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author ThinkPad
 */
public interface DayDAO {

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

}
