import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;

/**
 * Created by lifei on 2015/7/22.
 */
public class Log4jdbcTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext-dao.xml");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate=applicationContext.getBean(NamedParameterJdbcTemplate.class);
        namedParameterJdbcTemplate.queryForList("select * from ip_code limit 200", new HashMap<String, Object>());
    }
}
