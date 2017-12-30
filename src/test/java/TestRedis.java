import com.godlumen.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    /**
     * 设置key的有效时间，防止高并发访问
     */
    public void test1(){
        try{
            ValueOperations<String,String> opsForValue= stringRedisTemplate.opsForValue();
            opsForValue.set("test1","test1");
            System.out.println(opsForValue.get("test1"));
            if(stringRedisTemplate.expire("test1",5, TimeUnit.SECONDS)){
                System.out.println("设置过期时间成功，等待。。。");
                Thread.sleep(5001);
            }
            System.out.println(opsForValue.get("test1"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        set.add("test7_1", "2", "1","2","3","4","4","3");
        set.add("test7_2", "2", "6","2","3","7","6","5");
        System.out.println("全部成员"+set.members("test7_1"));
        System.out.println("差集"+set.difference("test7_1", "test7_2"));
        System.out.println("交集"+set.intersect("test7_1", "test7_2"));
        System.out.println("并集"+set.union("test7_1", "test7_2"));
    }
    //Sorted Set 存取数据 排序  相比sets 保存时多一个权重参数score，相当于按照此参数来排序
    @Test
    public void test3(){
        ZSetOperations<String, String> zSet = stringRedisTemplate.opsForZSet();
        zSet.add("test8", "use1", 9);
        zSet.add("test8", "use2", 1);
        zSet.add("test8", "use3", 5);
        zSet.add("test8", "use4", 9);
        //对应的score值增加1
        zSet.incrementScore("test8", "use1", 1);
        System.out.println(zSet.reverseRange("test8", 0, zSet.size("test8")-1));
    }

}
