package com.nowcoder;

import com.nowcoder.service.LikeService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 单元测试
 * Created by nowcoder on 2016/8/28.
 */
@RunWith(SpringJUnit4ClassRunner.class) /*用spring的Junit4版本的代码去跑这个单元测试*/
@SpringApplicationConfiguration(classes = WendaApplication.class)   /*加载spring默认的配置，用来初始化spring上下文的环境 */
public class LikeServiceTests {
    @Autowired
    LikeService likeService;    /*引入待测试的LikeService*/

    /**
     * 【@Before和@After 对每个测试用例都初始化一次。相当于执行每个测试用例前都复原环境】
     * 要执行单元测试，要在测试之前将数据准备好。
     * 所有留了@Before注解。要在这个函数里面将初始化的工作都写上。
     * 初始化一些数据，或者初始化一些表。
     */
    @Before
    public void setUp() {
       System.out.println("setUp");
    }

    /**
     * 在进行数据库相关的测试的时候，经常会初始化一些数据，或者初始化一些表。
     * 所以在@After里面，要将Before里面初始化的表删掉。
     * 或者说业务里面，增加了一些数据在表里面。或者删除了一些数据在表里面。这时就需要将表里产生的数据删掉。
     */
    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

    /**
     * 【@AfterClass和@BeforeClass：所有的测试用例，仅初始化一次。相当于等所有的测试用例都执行完了，再复原环境】
     * 在所有单元测试执行之前，会执行BeforeClass
     * 注意：要写成static函数。因为这个是class级别的
     */
    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    /*在所有单元测试执行完了以后，会执行AfterClass*/
    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    /**
     * 1.调用这个待测试的方法
     * 2.调用方法后，验证数据是否真的写到数据库里面
     */
    @Test   /*表明这是一个单元测试的入口*/
    public void testLike() {
        System.out.println("testLike");
        likeService.like(123, 1, 1);
        /*Assert断言。设置一个预期返回值，与函数执行的返回值进行对比。预期的返回值是你自己脑子里就知道的。*/
        Assert.assertEquals(1, likeService.getLikeStatus(123, 1, 1));

        /*一般是一个函数，一个方法来测试。这是是偷懒了*/
        likeService.disLike(123, 1, 1);
        Assert.assertEquals(-1, likeService.getLikeStatus(123, 1, 1));
    }

    @Test
    public void testXXX() {
        System.out.println("testXXX");
    }


    /**
     * 我这个测试方法。正常情况下，我期待能返回一个非法参数的异常。
     * 如果没有返回这个非法参数的异常，就会报错。
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        System.out.println("testException");
        throw new IllegalArgumentException("异常发生了");//抛出一个非法参数的异常。
    }
}
