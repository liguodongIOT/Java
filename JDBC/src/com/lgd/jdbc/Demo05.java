package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ������
 * @author liguodong
 *
 */

public class Demo05 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement  statement = null;
		ResultSet rs1 = null;
		try {
			//1������������
			Class.forName("com.mysql.jdbc.Driver");
			//2�����������ݿ������
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");
			
			
			connection.setAutoCommit(false);//����Ϊ�ֶ��ύ
			
			statement = connection.createStatement();
			
			long start = System.currentTimeMillis();
			for(int i=0;i<20000;i++)
			{
				statement.addBatch("insert into user(username,pwd,regTime) values ('����"+i+"��',666666,NOW())");
			}
			
			statement.executeBatch();
			
			connection.commit();//�ύ����
			
			long end = System.currentTimeMillis();
			
			System.out.println("�����������ݺ�ʱ�����룩��"+(end-start));
			
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			//ִ��˳��resultset-->statement-->connection�����Ĺر�˳��һ��Ҫ������try-catch��ֿ�д��
			if(rs1!=null){
				try {
					rs1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
