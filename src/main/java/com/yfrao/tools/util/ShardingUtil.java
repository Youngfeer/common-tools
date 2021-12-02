package com.yfrao.tools.util;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.yst.framework.utils.StringUtils;

/**
 * Created by xzwang on 2019-04-25.
 * <p>
 * 分片工具类
 */
public final class ShardingUtil {

    private static final int TEN = 10;
    //默认分片64
    public static final int SHARDING_SLICE = 64;

    private ShardingUtil() {
    }


    public static void main(String[] args) throws Exception {
        System.out.println(indexSlot("NF0006288"));
    }

    /**
     * 对象离散分布
     *
     * @param value 目标对象
     */
    public static int indexSlot(String value) throws Exception {
        if (value == null || value.length() < 6) {
            throw new Exception("参数不对");
        }
        String userId = value.substring(value.length() - 6);

        return Integer.valueOf(userId) % SHARDING_SLICE;
    }

    /**
     * 对象离散分布
     *
     * @param value 目标对象
     */
    public static <T> int indexSlotWithHash(String value){
        int h = value.hashCode();
        h = h ^ h >> 16;
        return (SHARDING_SLICE - 1) & h;
    }
    /**
     * 校验真实数据库表,例如:xxx_0,xxx_1,...,xxx_15
     *
     * @param tableName 实际表名
     * @param slot      实际表后缀
     */
    public static boolean checkActualTable(String tableName, long slot) {
        int suffixLen = 2;
        if (slot < TEN) {
            suffixLen = 1;
        }

        int suffixIndex = tableName.lastIndexOf("_");
        if (suffixIndex == -1) {
            return false;
        }

        String suffix = tableName.substring(suffixIndex + 1);
        if (suffix.length() != suffixLen || !StringUtils.isNumber(suffix)) {
            return false;
        }

        if (suffix.equals(slot + "")) {
            return true;
        }

        return false;
    }
}
