package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * ʱ�䴦��ȡ��ָ��ʱ��ε�����
 * @author liguodong
 */

public class Demo09 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement  statement = null;
		ResultSet rs1 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");			
			statement = connection.prepareStatement("select * from user where lastLoginTime>? and lastLoginTime<? order by lastLoginTime");
			
			java.sql.Timestamp startDate = new java.sql.Timestamp(strDate("2015-5-4 20:30:30"));
			java.sql.Timestamp endDate = new java.sql.Timestamp(strDate("2015-5-4 21:0:30"));
			
			System.out.println(startDate+"--"+endDate);
			statement.setObject(1, startDate);
			statement.setObject(2, endDate);
			
			rs1 = statement.executeQuery();
			while (rs1.next()) {
				//ȡ��ʱ��ȿ����������������������֡�
				System.out.println(rs1.getInt("id")+"---"+rs1.getString("username")+"---"+rs1.getDate("regTime")+"---"+rs1.getTimestamp("lastLoginTime"));	
				//System.out.println(rs1.getInt(1)+"---"+rs1.getString(2)+"---"+rs1.getDate(4)+"---"+rs1.getTimestamp(5));
			}
			

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
	
	/**
	 * ���ַ������������ת��Ϊlong���֣���ʽ��yyyy-MM-dd hh:mm:ss��
	 * @param dateString
	 * @return
	 */
	public static long strDate(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(dateString).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
