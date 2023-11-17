package com.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDao extends Dao {

	public MovieDao() throws Exception {
		super();
	}

	public List<Movies> displayAllMovies() throws Exception {
		List<Movies> list = new ArrayList<>();
		String sql = "SELECT * FROM movies";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("movie_id");
					String title = rs.getString("title");
					Date uDate = DateUtil.sqlToUtilDate(rs.getDate("releaseDate"));
					Movies m = new Movies(id, title, uDate);
					list.add(m);
				}
			} // rs.close();
		} // stmt.close();
		return list;

	}

}
