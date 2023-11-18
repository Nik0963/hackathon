package com.app;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SharesDao extends Dao {

	public SharesDao() throws Exception {
		super();
	}

	public static List<Reviews> displayReviewsSharedWithMe() throws Exception {
		List<Reviews> list = new ArrayList<Reviews>();

		return list;
	}

	public int shareReview(Reviews r, List<Users> u_id) throws Exception {
		String sql = "INSERT INTO shares VALUES(? ,?)";
		int count = 0;
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, r.getReviewId());
			for (int i = 0; i < u_id.size(); i++) {
				stmt.setInt(2, u_id.get(i).getUserId());
				count += stmt.executeUpdate();
			}
		} // stmt.close();
		return count;
	}

}
