package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zangyaoyi on 2017/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-services.xml"
})
public class SeckillServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillServiceTest.class);

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        LOGGER.info("list:{}", list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        LOGGER.info("seckill:{}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        LOGGER.info("exposer:{}", exposer);
    }

    @Test
    public void seckillLogic() {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            LOGGER.info("exposer:{}", exposer);
            long phone = 13300000001L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                LOGGER.info("result:{}", seckillExecution);
            } catch (SeckillCloseException e) {
                LOGGER.error(e.getMessage());
            } catch (RepeatKillException e) {
                LOGGER.error(e.getMessage());
            } catch (SeckillException e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            LOGGER.info("exposer:{}", exposer);
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000;
        long phone = 13300000001L;
        String md5 = "2ce058181f12dec7676776584c2e6db4";
        SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
        LOGGER.info("result:{}", seckillExecution);
    }

}