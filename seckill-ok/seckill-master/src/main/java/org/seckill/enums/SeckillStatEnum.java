package org.seckill.enums;


/**
 * Created by zangyaoyi on 2017/9/4.
 */
public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    End(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");
    private int stat;
    private String statInfo;

    SeckillStatEnum(int stat, String statInfo) {
        this.stat = stat;
        this.statInfo = statInfo;
    }

    public int getStat() {
        return stat;
    }

    public String getStatInfo() {
        return statInfo;
    }

    public static SeckillStatEnum statOf(int index) {
        for (SeckillStatEnum statEnum : values()) {
            if (statEnum.getStat() == index) {
                return statEnum;
            }
        }
        return null;
    }
}
