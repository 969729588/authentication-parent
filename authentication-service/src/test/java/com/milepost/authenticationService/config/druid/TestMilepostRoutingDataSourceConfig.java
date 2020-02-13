package com.milepost.authenticationService.config.druid;

import com.milepost.authenticationService.AuthenticationServiceApplication;
import com.milepost.test.BaseTest;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruifu Hua on 2020/1/15.
 */
public class TestMilepostRoutingDataSourceConfig extends BaseTest<AuthenticationServiceApplication>{

    private DataSource mainDs;
    private DataSource milepostRoutingDataSource;

    @Before
    public void init(){
        mainDs = getBean("mainDs", DataSource.class);
        milepostRoutingDataSource = getBean(DataSource.class);
    }

    @Test
    public void test1(){
        System.out.println(mainDs);
        System.out.println(milepostRoutingDataSource);
    }

//    /**
//     * 通过类型注入标有@Primary注解的主数据源
//     */
//    @Autowired
//    private DataSource dataSourceMain;
//
//    /**
//     * 这里不能使用@Autowired，因为它是按照类型注入的，不能指定bean的名称
//     */
//    @Resource(name="dataSourceOne")
//    private DataSource dataSourceOne;
//
//    @Resource(name="dataSourceTwo")
//    private DataSource dataSourceTwo;
//
//    /**
//     * 从druid的DataSource中获取的Connection，使用完一定要调用close方法关闭，
//     * 此处是调用druid的关闭方法，不是真正关闭，而是放回连接池，下次还可以从连接池中获取。
//     * @throws SQLException
//     */
//    @Test
//    public void test2() throws SQLException {
//        for(int i=0; i<100; i++){
//            Connection connection = dataSourceMain.getConnection();
//            System.out.println(i + "--" + connection);
//            if(i%5==0){
//                connection.close();
//            }
//        }
//    }
//
//    /**
//     * 获取到spring.datasource.druid.max-active指定的数目时就卡死了。
//     * @throws SQLException
//     */
//    @Test
//    public void test1() throws SQLException {
//        for(int i=0; i<100; i++){
//            System.out.println(i + "--" + dataSourceMain.getConnection());
//        }
//    }
//
//    /**
//     * 测试多数据源
//     * @throws SQLException
//     */
//    @Test
//    public void testDruidMultipleDs() throws SQLException {
//        System.out.println(getAll(dataSourceMain.getConnection()));
//        System.out.println(getAll(dataSourceOne.getConnection()));
//        System.out.println(getAll(dataSourceTwo.getConnection()));
//    }
//
//    public List<Map<String, Object>> getAll(Connection connection){
//
//        List<Map<String, Object>> record = new ArrayList<>();
//
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            String sql = "SELECT id, first_name, last_name FROM person ORDER BY id ";
//            preparedStatement = connection.prepareStatement(sql);
//
//            resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                String id = resultSet.getString(1);
//                String firstName = resultSet.getString(2);
//                String lastName = resultSet.getString(3);
//                Map<String, Object> map = new HashMap();
//                map.put("id", id);
//                map.put("firstName", firstName);
//                map.put("lastName", lastName);
//                record.add(map);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally{
//            try {
//                if(resultSet != null){
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                if(preparedStatement != null){
//                    preparedStatement.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            try {
//                if(connection != null){
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return record;
//    }

}
