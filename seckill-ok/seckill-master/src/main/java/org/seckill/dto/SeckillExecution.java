package org.seckill.dto;

import com.google.common.base.Objects;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * Created by zangyaoyi on 2017/9/4.
 * 封装秒杀执行后的结果
 */
public class SeckillExecution {
    private long seckillId;
    private int state;
    private String stateInfo;
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getStat();
        this.stateInfo = stateEnum.getStatInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getStat();
        this.stateInfo = stateEnum.getStatInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("seckillId", seckillId)
                .add("state", state)
                .add("stateInfo", stateInfo)
                .add("successKilled", successKilled)
                .toString();
    }
}
