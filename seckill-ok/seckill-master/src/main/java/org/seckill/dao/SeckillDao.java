package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zangyaoyi on 2017/8/22.
 */
public interface SeckillDao {
    /**
     * 鍑忓簱瀛�
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);

    /**
     *鏌ヨ
     * @param seckillID
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 鏍规嵁鍋忕Щ閲忔煡璇㈠垪琛�
     * @param offset
     * @param limit
     * @return
     *
     * java娌℃湁淇濆瓨褰㈠弬鐨勮褰曪紝鎵�浠ffset涓巐imit浼氳浣滀负arg0,arg1浼犻�掞紝瀵艰嚧鍑洪敊锛岀敤mybatis鑷甫鐨凘Param娉ㄨВ杩涜杞箟
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit")int limit);
    void killByProcedure(Map<String,Object> paramMap);
}
