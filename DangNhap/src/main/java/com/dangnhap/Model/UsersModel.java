package com.dangnhap.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dangnhap.connection.MySqlConnection;
import com.dangnhap.pojo.Users;

public class UsersModel {
	public Users getUsers(String email, String password) {
		Users users = null;
		Connection con = MySqlConnection.getConnection();
		String sql = "select * from users where email = ? and password = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				users = new Users();
				users.setId(result.getLong("id"));
				users.setEmail(result.getString("email"));
				users.setFullname(result.getString("fullname"));
				users.setAvatar(result.getString("avatar"));
				users.setRole_id(result.getLong("role_id"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
}
