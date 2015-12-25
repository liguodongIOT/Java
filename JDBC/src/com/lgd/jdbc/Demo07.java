package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ������÷�
 * @author liguodong
 *
 */

public class Demo07 {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement  statement1 = null;
		PreparedStatement  statement2 = null;
		
		try {
			//1������������
			Class.forName("com.mysql.jdbc.Driver");
			//2�����������ݿ������
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");
			
			
			connection.setAutoCommit(false);//JDBC��Ĭ���Զ��ύ����Ĭ����ture
			
			String sql1 = "insert into user (username,pwd) values(?,?)";
			
			statement1 = connection.prepareStatement(sql1);
			
			statement1.setObject(1, "��������");
			statement1.setObject(2, "666");
			
			System.out.println("����һ����¼");
			
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			String sql2 = "insert into user (username,pwd) values(?,?,?)";
			
			statement2 = connection.prepareStatement(sql2);
			statement2.setObject(1, "��Ī����");
			statement2.setObject(2, "666");		
		
			statement2.executeUpdate();
			
			System.out.println("��������һ����¼");
			
			connection.commit();//�ύ����
			
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				connection.rollback(); //�ع�����
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			
			//ִ��˳��resultset-->statement-->connection�����Ĺر�˳��һ��Ҫ������try-catch��ֿ�д��
			if(statement2!=null){
				try {
					statement2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			if(statement1!=null){
				try {
					statement1.close();
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
