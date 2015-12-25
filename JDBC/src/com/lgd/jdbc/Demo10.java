package com.lgd.jdbc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * CLOB�ı�������ʹ��
 * ���������ַ������ַ������ݲ��뵽���ݿ��CLOB�ֶΡ���CLOB�ֶ�ֵȡ����
 * @author liguodong
 *
 */

public class Demo10 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement  ps = null;
		ResultSet rs = null;
		Reader reader = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","liguodong");			
			
			/////////����///////////
			
			//ps = conn.prepareStatement("insert into user(username,myInfo) values(?,?)");
	
			/*ps.setString(1, "���õ��");
			//���ı��ļ��ֽ����뵽���ݿ���
			ps.setClob(2, new FileReader(new File("d:/rose.txt")));		
			ps.executeUpdate();
			
			ps.setString(1, "���õ��");
			//�������е��ַ������뵽���ݿ��CLOB�ֶ���
			ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("˾ְ����������������MVP".getBytes()))));
			ps.executeUpdate();*/
			
			/////////����///////////			
			ps = conn.prepareStatement("select * from user where id=?");
			
			ps.setObject(1, 2006);
			rs = ps.executeQuery();
			
			while(rs.next()){
				java.sql.Clob clob = rs.getClob("myInfo");
				
				reader = clob.getCharacterStream();
				int temp=0;
				while((temp=reader.read())!=-1){
					System.out.print((char)temp);
				}
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(reader!=null){
				try {
					reader.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//ִ��˳��resultset-->statement-->connection�����Ĺر�˳��һ��Ҫ������try-catch��ֿ�д��
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
