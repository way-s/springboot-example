package com.example.schedule.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: mxx
 * @Description:
 */
public class DataUtil {

    public static String sdf(Date data) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data);
    }
}
