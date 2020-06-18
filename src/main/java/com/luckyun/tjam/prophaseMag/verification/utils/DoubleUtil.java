package com.luckyun.tjam.prophaseMag.verification.utils;

import com.luckyun.core.exception.CustomException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *  double的计算不精确，会有类似0.0000000000000002的误差，
 *  正确的方法是使用BigDecimal或者用整型
 *  整型地方法适合于货币精度已知的情况，比如12.11+1.10转成1211+110计算，最后再/100即可
 *
 * @description double运算
 * @package com.mediway.core.util
 * @classname DoubleUtil
 * @author xukun
 * @date 2020/3/11 09:50
 */
public final class DoubleUtil {

    /**
     * 默认除法运算精度
     */
    private static final Integer DEF_DIV_SCALE = 2;

    /**
     * 提供精确的加法运算
     *
     * @description 提供精确的加法运算
     * @methodname add
     * @param value1 被加数
     * @param value2 加数
     *
     * @author xukun
     * @return java.lang.Double 两个参数的和
     * @date 2020/3/11 10:01
     */
    public static double add(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }


    /**
     * 提供精确的减法运算
     *
     * @description 提供精确的减法运算
     * @methodname sub
     * @param value1  被减数
     * @param value2 减数
     *
     * @author xukun
     * @return double 两个参数的差
     * @date 2020/3/11 10:02
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @description 提供精确的乘法运算
     * @methodname mul
     * @param value1 被乘数
     * @param value2 乘数
     *
     * @author xukun
     * @return java.lang.Double 两个参数的积
     * @date 2020/3/11 10:04
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     *  提供（相对）精确的除法运算，
     *  当发生除不尽的情况时， 精确到小数点以后2位，以后的数字四舍五入。
     *
     * @description
     * @methodname divide
     * @param dividend 被除数
     * @param divisor 除数
     *
     * @author xukun
     * @return java.lang.Double 两个参数的商
     * @date 2020/3/11 10:05
     */
    public static double divide(double dividend, double divisor) {
        return divide(dividend, divisor, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，
     * 由scale参数指定精度，以后的数字四舍五入。
     *
     * @description
     * @methodname divide
     * @param dividend 被除数
     * @param divisor 除数
     * @param scale 表示表示需要精确到小数点以后几位
     *
     * @author xukun
     * @return java.lang.Double 两个参数的商
     * @date 2020/3/11 10:07
     */
    public static double divide(double dividend, double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = BigDecimal.valueOf(dividend);
        BigDecimal b2 = BigDecimal.valueOf(divisor);
        try {
            return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("除数不能为0");
        }
    }

    /**
     *  提供指定数值的（精确）小数位四舍五入处理。
     *
     * @description
     * @methodname round
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     *
     * @author xukun
     * @return double 四舍五入后的结果
     * @date 2020/3/11 10:08
     */
    public static double round(double value, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = BigDecimal.valueOf(value);
        BigDecimal one = BigDecimal.valueOf(1L);
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }

}

