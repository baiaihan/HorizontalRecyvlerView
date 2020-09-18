/*--------------------------------------------------
 * Copyright (C) 2015 The Android Y-CarPlus Project
 *                http://www.yesway.cn/
 * 创建时间：2017年3月23日
 * 内容说明：
 *
 * 编号                日期                     担当者             内容
 * -------------------------------------------------
 *
 * -------------------------------------------------- */
package com.baiaihan.pageandautohorizontalrecycleview.bean;


public class Module {


    public static final int MODULE_NAVIGATION = 1;

    public static final int MODULE_TRAVEL = 2;

    public static final int MODULE_CARLIFE = 3;

    public static final int MODULE_MUSIC = 4;

    public static final int MODULE_4S = 5;

    public static final int MODULE_ZHIJIA = 6;

    public static final int MODULE_RESCUE = 7;

    public static final int MODULE_RESTAURANT = 8;

    public static final int MODULE_ME_USER = 10;

    public static final int MODULE_ME_CARINFO = 11;

    public static final int MODULE_ME_REMIND_CONTROL = 12;

    public static final int MODULE_ME_SPECIAL_STORE = 13;

    public static final int MODULE_ME_ORDER = 14;

    public static final int MODULE_ME_PIN_MANAGE = 15;

    public static final int MODULE_ME_SETTING = 16;

    public static final int MODULE_CARLIFE_CONTROL = 21;

    public static final int MODULE_CARLIFE_ORDER = 22;

    public static final int MODULE_CARLIFE_PARK = 23;

    public static final int MODULE_CARLIFE_VILLATION = 24;

    public static final int MODULE_CARLIFE_SUBSTIUTE_DRIVING = 25;

    public static final int MODULE_CARLIFE_LOCATION = 26;

    public static final int MODULE_REPAIR_LIST = 27;

    public static final int MODULE_CARLIFE_DVR = 28;

    public static final int MODULE_CARLIFE_COUPON = 29;

    public static final int MODULE_CARLIFE_MAINTAINCOUPON = 30;

    public int imgId;

    public int moduleName;

    public int moduleId;

    public Module(int imgId, int moduleName, int moduleId) {
        this.imgId = imgId;
        this.moduleName = moduleName;
        this.moduleId = moduleId;
    }


}
