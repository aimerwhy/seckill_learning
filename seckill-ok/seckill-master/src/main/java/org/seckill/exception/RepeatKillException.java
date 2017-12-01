package org.seckill.exception;

/**
 * Created by zangyaoyi on 2017/9/4.
 * 重复秒杀异常（运行期异常）
 */
public class RepeatKillException extends RuntimeException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
