package com.lgd.orm;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * ʹ��Map<Integer,Map<String,Object>>����װ������¼
 * @author liguodong
 *
 */
public class Demo05 {
	public static void main(String[] args) {
		Connection connection = JDBCUtil2.getMysqlConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Map<Integer,Map<String,Object>> maps = new HashMap<Integer,Map<String,Object>>();
		
		try {
			preparedStatement = connection.prepareStatement("select id,enpname,salary,age from emp where id>=?");
			//preparedStatement.setObject(1, 2);
			preparedStatement.setInt(1, 2);
			resultSet = preparedStatement.executeQuery();
			
			
			while(resultSet.next())
			{
				Map<String,Object> map = new HashMap<String, Object>();//ʹ��һ��map��װ��һ����¼����Ϣ��
				//System.out.println(resultSet.getString(1));
				map.put("empname", resultSet.getObject(2));
				map.put("salary", resultSet.getObject(3));
				map.put("age", resultSet.getObject(4));
				maps.put((Integer)resultSet.getObject(1),map);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil2.close(resultSet,preparedStatement,connection);
		}
		
		//����Map<Integer,Map<String,Object>>
		for (Integer id : maps.keySet()) {
			for (String key : maps.get(id).keySet()) {
				System.out.print(key+":"+maps.get(id).get(key)+"\t");
			}	
			System.out.println();
		}
	}
}
