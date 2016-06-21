
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lifei on 2016/3/3.
 */
public class JsonTest {
    public static void main(String[] args) {
        List<Map<String,Object>> test=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("url", "1");
        test.add(map);
        test.add(map);
        System.out.println(JSON.toJSON(test));
        TestBean testBean=new TestBean("tseturl");
        List<TestBean> test1=new ArrayList<>();
        test1.add(testBean);
        test1.add(testBean);
        System.out.println(JSON.toJSON(test1));
    }
}
