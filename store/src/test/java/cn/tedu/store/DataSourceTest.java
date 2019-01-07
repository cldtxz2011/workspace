package cn.tedu.store;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {
	@Autowired
	DataSource datasource;
	
	@Test
	public void ssd() throws SQLException {
		System.out.println(datasource.getConnection());
	}

}
