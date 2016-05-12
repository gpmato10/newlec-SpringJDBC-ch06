package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import vo.Notice;

public class NoticeDao extends JdbcDaoSupport{



	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";

		return getJdbcTemplate().queryForInt(sql, "%" + query + "%");


//		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";
//
//		Class.forName("com.mysql.jdbc.Driver");
//		// 1. 접속
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/newlecspring",
//				"root", "123123");
//		// 2. 실행
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, "%"+query+"%");
//
//		// 3. 결과
//		ResultSet rs = st.executeQuery();
//		rs.next();
//
//		int cnt = rs.getInt("cnt");
//
//		rs.close();
//		st.close();
//		con.close();
//
//		return cnt;
	}
	
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{
		int srow = 0 + (page-1) * 15;
		int erow = 14 + (page-1) * 15;

		String sql = "select * from";
		sql += " notices where " + field + " like ? order by regdate desc limit ?,?";

		return getJdbcTemplate().query(sql, new Object[]{"%" + query + "%", srow, erow}, new RowMapper<Notice>() {
			@Override
			public Notice mapRow(ResultSet resultSet, int i) throws SQLException {
				Notice vo = new Notice();
				vo.setSeq(resultSet.getString("seq"));
				vo.setTitle(resultSet.getString("title"));
				vo.setWriter(resultSet.getString("title"));
				vo.setRegdate(resultSet.getDate("regdate"));
				vo.setHit(resultSet.getInt("hit"));
				vo.setContent(resultSet.getString("content"));
				vo.setFileSrc(resultSet.getString("fileSrc"));

				return vo;
			}
		});



//		int srow = 0 + (page-1) * 15;
//		int erow = 14 + (page-1) * 15;
//
//		String sql = "select * from";
//		sql += " notices where " + field + " like ? order by regdate desc limit ?,?";
//
//		// 0. 드라이버 로드
//		Class.forName("com.mysql.jdbc.Driver");
//		// 1. 접속
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/newlecspring",
//				"root", "123123");
//		// 2. 실행
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, "%"+query+"%");
//		st.setInt(2, srow);
//		st.setInt(3, erow);
//		// 3. 결과
//		ResultSet rs = st.executeQuery();
//
//		List<Notice> list = new ArrayList<Notice>();
//
//		while(rs.next()){
//			Notice n = new Notice();
//			n.setSeq(rs.getString("seq"));
//			n.setTitle(rs.getString("title"));
//			n.setWriter(rs.getString("writer"));
//			n.setContent(rs.getString("content"));
//			n.setRegdate(rs.getDate("regdate"));
//			n.setHit(rs.getInt("hit"));
//			n.setFileSrc(rs.getString("filesrc"));
//
//			list.add(n);
//		}
//
//		rs.close();
//		st.close();
//		con.close();
//
//		return list;
	}
	
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE FROM NOTICES WHERE SEQ=?";
		return getJdbcTemplate().update(sql, seq);

//		// 2. 데이터 베이스 연동을 위한 쿼리와 실행 코드 작성
//		String sql = "DELETE FROM NOTICES WHERE SEQ=?";
//		// 0. 드라이버 로드
//		Class.forName("com.mysql.jdbc.Driver");
//		// 1. 접속
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/newlecspring",
//				"root", "123123");
//		// 2. 실행
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, seq);
//
//		int af = st.executeUpdate();
//
//		return af;
	}
	
	public int update(Notice notice) throws ClassNotFoundException, SQLException{

		String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?, FILESRC=? WHERE SEQ=?";

		return getJdbcTemplate().update(sql, notice.getTitle(), notice.getContent(), notice.getFileSrc(), notice.getSeq());


//		// 2. 데이터 베이스를 연동하기 위한 쿼리와 데이터베이스 연동을 위한 코드를 작성
//		String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?, FILESRC=? WHERE SEQ=?";
//		// 0. 드라이버 로드
//		Class.forName("com.mysql.jdbc.Driver");
//		// 1. 접속
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/newlecspring",
//				"root", "123123");
//		// 2. 실행
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, notice.getTitle());
//		st.setString(2, notice.getContent());
//		st.setString(3, notice.getFileSrc());
//		st.setString(4, notice.getSeq());
//
//		int af = st.executeUpdate();
//
//		return af;
	}


	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM NOTICES WHERE SEQ=?";

		Notice notice = getJdbcTemplate().queryForObject(sql, new Object[] {seq} , new RowMapper<Notice>() {
			@Override
			public Notice mapRow(ResultSet resultSet, int i) throws SQLException {

				Notice vo = new Notice();
				vo.setSeq(resultSet.getString("seq"));
				vo.setTitle(resultSet.getString("title"));
				vo.setWriter(resultSet.getString("title"));
				vo.setRegdate(resultSet.getDate("regdate"));
				vo.setHit(resultSet.getInt("hit"));
				vo.setContent(resultSet.getString("content"));
				vo.setFileSrc(resultSet.getString("fileSrc"));

				return vo;
			}
		});

		return notice;

	}

	public int insert(final Notice n) throws ClassNotFoundException, SQLException {


		return getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO NOTICES(TITLE, CONTENT, WRITER, HIT, FILESRC) \n" +
						"VALUES(?, ?, 'newlec', HIT, ?)";

				PreparedStatement st = connection.prepareStatement(sql);
				st.setString(1, n.getTitle());
				st.setString(2, n.getContent());
				st.setString(3, n.getFileSrc());
				return st;
			}
		});

//		return template.update(sql, new PreparedStatementSetter() {
//			@Override
//			public void setValues(PreparedStatement st) throws SQLException {
//				st.setString(1, n.getTitle());
//				st.setString(2, n.getContent());
//				st.setString(3, n.getFileSrc());
//			}
//		});


//		String sql = "INSERT INTO NOTICES(TITLE, CONTENT, WRITER, HIT, FILESRC) \n" +
//				"VALUES(?, ?, 'newlec', HIT, ?)";
//		// 0. 드라이버 로드
//		Class.forName("com.mysql.jdbc.Driver");
//		// 1. 접속
//		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/newlecspring",
//				"root", "123123");
//		// 2. 실행
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, n.getTitle());
//		st.setString(2, n.getContent());
//		st.setString(3, n.getFileSrc());
//
//		int af = st.executeUpdate();
//
//		st.close();
//		con.close();
//
//		return af;
	}
}
