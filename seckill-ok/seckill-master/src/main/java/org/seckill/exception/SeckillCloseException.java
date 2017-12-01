package org.seckill.exception;

/**
 * Created by zangyaoyi on 2017/9/4.
 * 秒杀关闭异常
 */
public class SeckillCloseException extends RuntimeException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
