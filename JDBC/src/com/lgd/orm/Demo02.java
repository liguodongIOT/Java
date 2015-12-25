package com.lgd.orm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ʹ��List<Object[]>�洢������¼
 * @author liguodong
 *
 */

public class Demo02 {
	public static void main(String[] args) {
		Connection connection = JDBCUtil2.getMysqlConn();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//��Ŷ�����¼
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			preparedStatement = connection.prepareStatement("select enpname,salary,age from emp where id>=?");
			//preparedStatement.setObject(1, 2);
			preparedStatement.setInt(1, 2);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				Object[] objects = new Object[3];//һ��Object�����װ��һ����¼����Ϣ��
				//System.out.println(resultSet.getString(1));
				objects[0] = resultSet.getObject(1);
				objects[1] = resultSet.getObject(2);
				objects[2] = resultSet.getObject(3);
				list.add(objects);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil2.close(resultSet,preparedStatement,connection);
		}	
		
		for (Object[] objects : list) {
			System.out.println(objects[0]+"--"+objects[1]+"--"+objects[2]);
		}
	}
}
