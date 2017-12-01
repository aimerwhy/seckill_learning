package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by zangyaoyi on 2017/9/12.
 */
public class RedisDao {
    private JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        //redis鎿嶄綔
        Jedis jedis = jedisPool.getResource();
        //娌℃湁瀹炵幇鍐呴儴搴忓垪鍖�
        //get -->byte[] -->鍙嶅簭鍒楀寲-->Object(Seckill)
        //鑷畾涔夊簭鍒楀寲
        //protostuff:pojo
        try {
            String key = "seckill:" + seckillId;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                //seckill琚弽搴忓垪
                return seckill;
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    public String putSeckill(Seckill seckill) {
        //set object(seckill)->搴忓垪鍖�->byte[]
        Jedis jedis = jedisPool.getResource();

        try {
            String key = "seckill:" + seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            int timeout = 60 * 60;//绉�
            return jedis.setex(key.getBytes(), timeout, bytes);
        } finally {
            jedis.close();
        }
    }
}
