package com.tool.util;

import java.util.Random;

/**
 * Description : 數學相關工具
 * Author : ChiYuan
 * Date : 2019/04/29 16:00
 */
public class MathUtil {

    /**
     * 在兩數之間取隨機數
     * @param bound1 界1
     * @param bound2 界2
     * @return 隨機數
     */
    public static int getRandomBetween(int bound1, int bound2) {
        if (bound1 < bound2) {
            int temp = bound1;
            bound1 = bound2;
            bound2 = temp;
        }

        Random random = new Random();
        int result = random.nextInt(bound1 - bound2);
        result += bound2;

        return result;
    }


}