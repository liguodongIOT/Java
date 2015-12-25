package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ʱ�䴦��java.sql.Date, java.sql.Time, java.sql.Timestamp��
 * @author liguodong
 *
 */

public class Demo08 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement  statement = null;
		Statement rs1 = null;
		try {
			//1������������
			Class.forName("com.mysql.jdbc.Driver");
			//2�����������ݿ������
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");
												
			//3������PreparedStatement�Ļ����÷�   ?ռλ��
			String sql1 = "insert into user(username,pwd,regTime,lastLoginTime) values(?,?,?,?)";
			statement = connection.prepareStatement(sql1);
			
			statement.setObject(1, "ģ��");
			statement.setObject(2, "mm");
			//java.sql.Dateû�пչ�����������Ҫ��ֵ��
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			
			//�����Ҫָ�����ڣ�����ʹ��Calendar��DateFormat
			java.sql.Timestamp stamp = new java.sql.Timestamp(System.currentTimeMillis());
			
			statement.setObject(3, date);
			
			statement.setTimestamp(4, stamp);
			
			statement.executeUpdate();

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
