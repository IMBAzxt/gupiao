/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhengxuetao.gupiao.dao.impl;

import com.zhengxuetao.gupiao.dao.DayDAO;
import com.zhengxuetao.gupiao.entity.DayData;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ThinkPad
 */
@Repository
public class DayDAOImpl implements DayDAO {


    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取和当前线程绑定的session
     *
     * @return
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean saveDayData(DayData dayData) {
        try {
            getSession().save(dayData);
        } catch (Exception e) {
            LoggerFactory.getLogger(DayDAOImpl.class).error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<DayData> findDayDataList(Long id, Timestamp startDate, Timestamp endDate) {
        Query query = getSession().createQuery("FROM DayData d WHERE d.financeId=:id AND d.time>=:startDate "
                + "AND d.time <=:endDate ORDER BY d.time ASC");
        query.setLong("id", id);
        query.setTimestamp("startDate", startDate);
        query.setTimestamp("endDate", endDate);
        return query.list();
    }



}
