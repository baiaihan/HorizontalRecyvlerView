package com.baiaihan.pageandautohorizontalrecycleview.utils;


public class PageUtils {
    public static int getPageSize(int totalCount, int rows, int colums) {
        return totalCount / (rows * colums) + (totalCount % (rows * colums) == 0 ? 0 : 1);
    }
}
