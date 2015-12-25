package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {
			//1������������
			Class.forName("com.mysql.jdbc.Driver");
			//2�����������ݿ������
			//���Ӷ����ڲ���ʵ������Socket������һ��Զ�̵����ӡ��ȽϺ�ʱ�䣬����Connection��������һ��Ҫ�㣡
			//���������У�Ϊ�����Ч�ʣ�����ʹ�����ӳ����������Ӷ���
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");
			System.out.println(connection);
			
									
			//3������ָ��SQL����Լ�SQLע������
			//ʵ��Ӧ���бȽ���
			//1.�������������
			//2.���׷���SQLע���Σ��
			statement = connection.createStatement();
			
			/*String sqlone = "insert into user(username,pwd,regTime) values ('����',54423,NOW())";
			statement.execute(sqlone);*/
			
			/*String nameString = "�ΰ�";
			String sqltwo = "insert into user(username,pwd,regTime) values('"+nameString+"',54ds23,NOW())";
			statement.execute(sqltwo);*/
			
			//����SQLע��
			String idString = "5 or 1=1";
			String sql1 = "delete from user where id="+idString;
			statement.execute(sql1);//������ɾ������Ԫ��
			
			
			
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
