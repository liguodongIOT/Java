package com.lgd.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * ʹ��JDBCUtil������򻯲���
 * @author liguodong
 *
 */

public class Demo12 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement  ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConn();
			
			ps = conn.prepareStatement("insert into user(username) values(?)");
			ps.setString(1, "�����");
			ps.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(rs, ps, conn);			
		}
	}

}
