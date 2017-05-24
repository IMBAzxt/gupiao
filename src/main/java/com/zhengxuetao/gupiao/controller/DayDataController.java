/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhengxuetao.gupiao.controller;

import com.zhengxuetao.gupiao.service.DayService;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ThinkPad
 */
@Controller
public class DayDataController {

    @Autowired
    DayService dayService;

    public boolean saveDayDataFromFile() {
        return true;
    }
}
