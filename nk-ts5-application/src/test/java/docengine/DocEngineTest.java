package docengine;

import cn.nkpro.ts5.TS5Application;
import cn.nkpro.ts5.data.redis.RedisSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bean on 2020/7/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={TS5Application.class})
public class DocEngineTest {
    @Autowired
    private RedisSupport<String> redisSupport;

    @Test
    public void test1() throws Exception {

    }
}
