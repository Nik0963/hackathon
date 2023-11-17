package com.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class UserDao extends Dao {

	public UserDao() throws Exception {
		super();
	}

	public int save(Users u) throws Exception {
		String sql = "INSERT INTO users VALUES(default, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, u.getfName());
			stmt.setString(2, u.getlName());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getMobile());
			stmt.setDate(6, DateUtil.utilToSqlDate(u.getDob()));
			int count = stmt.executeUpdate();
			return count;
		} // stmt.close();
	}

	public Users findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM users WHERE email=?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String fname = rs.getString("first_name");
					String lname = rs.getString("last_name");
					String password = rs.getString("password");
					String mobile = rs.getString("mobile");
					Date uDate = DateUtil.sqlToUtilDate(rs.getDate("dob"));
					return new Users(id, fname, lname, email, password, mobile, uDate);
				}
			} // rs.close();
		} // stmt.close();
		return null;
	}

	public Users editProfile(int id) throws Exception {

		String editProfile = "UPDATE users SET first_name = ?,last_name=?,mobile=?,dob=? WHERE id=?";
		try (PreparedStatement stmt = con.prepareStatement(editProfile)) {
			System.out.println("Enter id of candidate to update : ");
			
			
			

			stmt.setString(1, fname);
			stmt.setString(2, lname);
			stmt.setDate(3, SDate);
			stmt.setInt(4, id);
			int count = stmt.executeUpdate();

			System.out.println("Rows affected : " + count);
		}

		return null;
	}

}
