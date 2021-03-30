package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AddPhoneNumberDaoOracl implements AddPhoneNumberDao {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl="jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,"C##gjtjdgh","1234");
			 
		
		}catch(ClassNotFoundException e) {
			System.err.println("드라이버 불러오기 실패");
		}finally {
		
		}
		return conn;
	}
	
	@Override
	public List<AddPhoneNumberVo> getList() {
		List<AddPhoneNumberVo> list = new ArrayList<>();
		Connection conn =null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select id , name , hp , tel from PHONE_BOOK";
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id =rs.getLong("id");
				String name =rs.getString("name");
				String hp =rs.getString("hp");
				String tel =rs.getString("tel");
				
				AddPhoneNumberVo vo = new AddPhoneNumberVo(id,name,hp,tel);
				
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
		try {
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(Exception e) {
			
		}
		}
		return list;
	}
	

	@Override
	public List<AddPhoneNumberVo> search(String keyword) {
		List<AddPhoneNumberVo> list = new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = " select  id , name , hp , tel from PHONE_BOOK where name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				AddPhoneNumberVo vo = new AddPhoneNumberVo();
				vo.setId(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setHp(rs.getString(3));
				vo.setTel(rs.getString(4));
				list.add(vo);
			
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			}
		catch(Exception e) {
			
		}
		}
		return list ;
	}

	

	@Override
	public AddPhoneNumberVo get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AddPhoneNumberVo vo) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			conn =getConnection();
			String sql = "insert into  PHONE_BOOK values(seq_phone_book.nextval,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}
		catch(Exception e) {
			
		}
		}
		return insertCount;
	}
	

	@Override
	public int delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM PHONE_BOOK " +
					"WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			//	쿼리 수행
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deletedCount;
	}
	}


