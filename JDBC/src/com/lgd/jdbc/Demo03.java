package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Demo03 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			//1������������
			Class.forName("com.mysql.jdbc.Driver");
			//2�����������ݿ������
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");
												
			//3������PreparedStatement�Ļ����÷�
			String sql1 = "insert into user(username,pwd,regTime) values (?,?,?)";
			statement = connection.prepareStatement(sql1);
			/*statement.setString(1, "�ܽ���");//���������Ǵ�1��ʼ���㣬������0
			statement.setString(2, "fdsf323");						
			statement.execute();*/
			
			/*
			//Ҳ����ʹ��setObject�����������
			statement.setObject(1, "ղķ˹");
			statement.setObject(2, "fdf323");						
			statement.execute();*/
			
			statement.setString(1, "��������");
			statement.setString(2, "fdsfds3");								
			statement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
			statement.execute();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
