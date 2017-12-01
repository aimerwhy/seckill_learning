# 存储过程
DELIMITER $$  #console ;转换为
$$
#定义存储过程
#in输入参数 On 输出参数
#ROW_COUNT() :返回上一条数据修改类型sql(delete,insert,update)的影响行数
#ROW_COUNT: 0 未修改 ;>0 修改行数 ; <0 错误
CREATE PROCEDURE `seckill`.`execute_seckill`
  (IN v_seckill_id BIGINT, IN v_phone BIGINT,
   IN v_kill_time  TIMESTAMP, OUT r_result INT)
  BEGIN
    DECLARE insert_count INT DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE INTO success_killes
    (seckill_id, user_phone, create_time)
      VALUE (v_seckill_id, v_phone, v_kill_time);
    SELECT ROW_COUNT()
    INTO insert_count;
    IF (insert_count = 0)
    THEN
      ROLLBACK;
      SET r_result = -1;
    ELSEIF (insert_count < 0)
      THEN
        ROLLBACK;
        SET r_result = -2;
    ELSE
      UPDATE seckill.seckill
      SET number = number - 1
      WHERE seckill_id = v_seckill_id
            AND end_time > v_kill_time
            AND start_time < v_kill_time
            AND number > 0;
      SELECT ROW_COUNT()
      INTO insert_count;
      IF (insert_count = 0)
      THEN
        ROLLBACK;
        SET r_result = 0;
      ELSEIF (insert_count < 0)
        THEN
          SET r_result = -2;
      ELSE
        COMMIT;
        SET r_result = 1;
      END IF;
    END IF;
  END;
$$
#存储过程已结束

DELIMITER ;
SET @r_result = -3;
#执行存储过程
CALL execute_seckill(1003, 12312312312, now(), @r_result);
#获取结果
SELECT @r_result;

#存储过程优化：事务行级锁持有时间
#不要过度依赖存储过程
#简单的逻辑可以应用存储过程
#qps：一个商品6000/qps
