package org.seckill.exception;

/**
 * Created by zangyaoyi on 2017/9/4.
 * 秒杀业务异常
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
