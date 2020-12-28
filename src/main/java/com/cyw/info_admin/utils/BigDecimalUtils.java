package com.cyw.info_admin.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    /**
     * 加法计算
     */
    public static BigDecimal add(BigDecimal x, BigDecimal y) {
        if (x == null) {
            return y;
        }
        if (y == null) {
            return x;
        }
        return x.add(y);
    }

    /**
     * 减法计算
     */
    public static BigDecimal subtract(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.subtract(y);
    }

    /**
     * 乘法计算
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.multiply(y);
    }

    /**
     * 除法计算[小数点后保留20位]
     */
    public static BigDecimal divide(BigDecimal x, BigDecimal y) {
        if (x == null || y == null || y.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        // 结果为0.000..时，不用科学计数法展示
        // 舍入模式为：银行家模式
        return stripTrailingZeros(x.divide(y, 3, BigDecimal.ROUND_HALF_EVEN));
    }

    /**
     * 去除小数点后的0[如: 输入1.000返回1]
     */
    public static BigDecimal stripTrailingZeros(BigDecimal x) {
        if (x == null) {
            return null;
        }
        return x.stripTrailingZeros();
    }
}
