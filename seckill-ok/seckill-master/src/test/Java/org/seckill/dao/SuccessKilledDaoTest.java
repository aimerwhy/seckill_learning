package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
/*
  * 配置spring与junit整合，junit启动加载springIOC容器
  * ctrl+shift+t创建
  * */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Autowired
    private SuccessKilledDao successKilledDao;
    /*
    * 第一次返回1，第二次返回0
    * */
    @Test
    public void insertSuccessKilled() throws Exception {
        /*
        * 循环单挑插入1000次
        * */
        long beginTime=System.currentTimeMillis();
        long id=3000L;
        long phone = 18700000000L;
        for(int i=0;i<1;i++){
            id+=i;
            phone+=i;
            int insertCount=successKilledDao.insertSuccessKilled(id,phone);
            System.out.println("insertCount:"+insertCount);
        }
        long endTime=System.currentTimeMillis();
        System.out.println("begin:"+beginTime);
        System.out.println("end："+endTime);
        System.out.println("diff:"+(endTime-beginTime));


      /*  System.out.println(new Date(1503909096825L));
        System.out.println(new Date(1503909139484L));
        long beginTime=System.currentTimeMillis();
        System.out.println("begin:"+beginTime);
        List<SeckillInsertModel> list=new ArrayList<SeckillInsertModel>();
        SeckillInsertModel seckillInsertModel;
        for(int i=0;i<1000;i++){
            seckillInsertModel=new SeckillInsertModel();
            long id=1000L+i;
            long phone = 18700000000L+i;
            seckillInsertModel.setSeckillId(id);
            seckillInsertModel.setUserPhone(phone);
            list.add(seckillInsertModel);
            // int insertCount=successKilledDao.insertSuccessKilled(id,phone);
            // System.out.println("insertCount:"+insertCount);
        }
        int insertCount=successKilledDao.batchInsert(list);
        System.out.println(insertCount);
        long endTime=System.currentTimeMillis();
        System.out.println("end："+endTime);
        System.out.println("diff:"+(endTime-beginTime));*/

    }

    @Test
    public void queryByIDWithSeckill() throws Exception {
        long id=1000L;
        long phone=18700000000L;
        SuccessKilled successKilled=successKilledDao.queryByIDWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}