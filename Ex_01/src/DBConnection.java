import java.sql.*;

public class DBConnection {

	private Connection con;
	private Statement stmt;

	public void DBopen() {
		try {
			String url = "jdbc:mysql://localhost/userdata?serverTimezone=Asia/Seoul";
			String user = "root";
			String passwd = "abcd!1234";

			con = DriverManager.getConnection(url, user, passwd);
			System.out.println("DB연결 성공");

			stmt = con.createStatement();
			System.out.println("Statement객체 생성 성공");

//			stmt.executeUpdate("use coffee"); //coffee라는 데이터 베이스에 접속
//			stmt.executeUpdate("insert into propertysetting (_name,_age,_addres,_rank,_jumsu)values('문종성5','34','경기도','null','null');"); //Example 테이블을 조회
			System.out.println(" 테이블을 조회했습니다.");

		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			System.out.print("사유 : " + e.getMessage());
		}
	}

	public boolean mysqlinsert(String name, String age, String addres, String rank, String jumsu) {
		int rowcount = 0;
		rowcount = mysqlselect(name);
		if (rowcount > 0) {
			System.out.println(name + " 누가 이미 데이터가 존재합니다.");
			return false;
		}
		String sql = "insert into propertysetting (_name,_age,_addres,_rank,_jumsu)values('" + name + "','" + age
				+ "','" + addres + "','" + rank + "','" + jumsu + "');";

		try {
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} // Example 테이블을 조회

		return true;

	}

	public boolean mysqldelet(String name) {

		String sql = "DELETE FROM PropertySetting where _name ='" + name + "'";

		try {
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} // Example 테이블을 조회
		return true;
	}

	public boolean mysqlupdate(String name, String age, String addres, String rank, String jumsu) {

		String sql = "update propertysetting set _age='" + age + "', _addres='" + addres + "', _rank='" + rank
				+ "', _jumsu='" + jumsu + "' where _name='" + name + "';";

		try {
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} // Example 테이블을 조회
		return true;
	}

	public int mysqlselect(String name) {
		ResultSet rs;
		int rowcount = 0;
		String sql;

		sql = "select _name, _age, _addres, _rank, _jumsu from propertysetting";
		if (!name.equals("")) {
			sql += " where _name = '" + name + "'";
		}

		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("name = " + rs.getString(1) + " age = " + rs.getString(2) + " addres = "
						+ rs.getString(3) + " rank = " + rs.getString(4) + " jumsu = " + rs.getString(5));
				rowcount++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowcount;
	}

	public void DBclose() {

		try {
			stmt.close();
			System.out.println("연결종료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBConnection DB = new DBConnection();

		DB.DBopen();
		DB.mysqlinsert("박희수", "34", "경기도", "", "");
//		DB.mysqlupdate("지민구","31", "이천", "", "");
//		DB.mysqldelet("지민구");
//		DB.mysqlselect();
		DB.DBclose();
	}

}