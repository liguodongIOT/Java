package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Demo04 {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement  statement = null;
		ResultSet rs1 = null;
		try {
			//1������������
			Class.forName("com.mysql.jdbc.Driver");
			//2�����������ݿ������
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");
												
			//3������PreparedStatement�Ļ����÷�   ?ռλ��
			
			String sql1 = "select * from user where id>?";
			statement = connection.prepareStatement(sql1);
			statement.setObject(1, 2);//�Ѵ���2�ļ�¼��ȡ����
			
			rs1 = statement.executeQuery();
			
			while(rs1.next()){
				System.out.println(rs1.getInt(1)+"---"+rs1.getString(2)+"---"+rs1.getString(3));
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
}
