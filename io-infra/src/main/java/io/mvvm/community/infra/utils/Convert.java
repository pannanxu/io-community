package io.mvvm.community.infra.utils;

/**
 * 类型转换工具
 *
 * @author: Pan
 **/
public interface Convert {

    String  EMPTY  = "";
    Integer ZERO   = 0;
    Long    ZERO_L = 0L;

    /**
     * Object to String
     *
     * @param var        obj
     * @param defaultVal default value
     * @return 如果是空, 则返回 ${defaultVal} 否则返回 val
     */
    static String parseString(Object var, String defaultVal) {
        try {
            return null == var ? defaultVal : var.toString();
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * Object to String
     *
     * @param var        obj
     * @return 如果是空, 则返回 “” 否则返回 val
     */
    static String parseString(Object var) {
        return parseString(var, EMPTY);
    }

    /**
     * Object to Integer
     *
     * @param var        obj
     * @param defaultVal default value
     * @return 如果是空, 则返回 ${defaultVal} 否则返回 val
     */
    static Integer parseInteger(Object var, Integer defaultVal) {
        try {
            return null == var ? defaultVal : Integer.parseInt(parseString(var));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * Object to Integer
     *
     * @param var        obj
     * @return 如果是空, 则返回 0 否则返回 val
     */
    static Integer parseInteger(Object var) {
        return parseInteger(var, ZERO);
    }

    /**
     * Object to Long
     *
     * @param var        obj
     * @param defaultVal default value
     * @return 如果是空, 则返回 ${defaultVal} 否则返回 val
     */
    static Long parseLong(Object var, Long defaultVal) {
        try {
            return null == var ? defaultVal : Long.valueOf(parseString(var));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    /**
     * Object to Long
     *
     * @param var        obj
     * @return 如果是空, 则返回 0 否则返回 val
     */
    static Long parseLong(Object var) {
        return parseLong(var, ZERO_L);
    }

}
