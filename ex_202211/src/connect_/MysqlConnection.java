package connect_;

import java.sql.*;

public class MysqlConnection {
	
	Connection con =null; // 커넥션 con null 초기화 , close 메소드를 따로 만들었기때문에 멤버변수로 선언
	
	Statement stmt = null;
	
	public void connect() // 커넥트 메소드 안에서 처리하기때문에 리턴값은 따로없어도됨.
	{
		// 정보 부분 수정시 이쪽에서 수정
		String url = "jdbc:mysql://localhost/userdata?serverTimezone=Asia/Seoul"; // DB주소
		String user = "root"; // 계정
		String pw = "abcd!1234"; // 패스워드
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 불러오기
			con = DriverManager.getConnection(url, user, pw); // 정보확인 / 로그인
			System.out.println("데이터 베이스 연결성공");
		} 
		catch (ClassNotFoundException e) // 드라이버 불러오기 오류시 출력
		{
			e.printStackTrace();
			System.out.println("드라이버를 찾지 못했습니다.");
		}
		catch (SQLException e) // 정보 불러오지못할경우 출력
		{
			e.printStackTrace();
			System.out.println("데이터 베이스 연결실패");
		}
		
	}
	
	public void close() // close 구문 메소드
	{
		// 필요한 로직
		// 사용이 끝나면 접속을 해제
		try {
			try 
			{
				if(con != null)
				con.close();
				System.out.println("연결종료2");
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (NullPointerException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	// 삽입
    public void insert(String name, String age, String addres, String rank, String jumsu) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("insert into propertysetting (_name,_age,_addres,_rank,_jumsu)values(")
                .append("'"+name+"'"+ ",")
                .append("'" + age + "',")
                .append("'" + addres + "',")
                .append("'" + rank + "',")
                .append("'" + jumsu+"'")
                .append(");")
                .toString();
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 삭제
    public void delete(int id) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("delete from  propertysetting where id = ")
                .append(id)
                .append(";")
                .toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 수정
    public void update(int id, String name, int grade) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("update propertysetting set")
                .append(" name = ")
                .append("'" + name + "',")
                .append(" grade = ")
                .append(grade)
                .append(" where id = ")
                .append(id)
                .append(";").toString();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 모든 검색
    public void selectAll() {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from propertysetting ")
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.print("id");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("grade");
            System.out.print("\n");
            System.out.println("────────────────────────");
            
              while(rs.next()){
                     System.out.print(rs.getInt("id"));
                     System.out.print("\t");
                     System.out.print(rs.getString("name"));
                     System.out.print("\t");
                     System.out.print(rs.getString("grade"));
                     System.out.print("\n");
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    // 검색
    public void select(int id) {
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("select * from propertysetting where")
                .append(" id = ")
                .append(id)
                .append(";").toString();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.print("id");
            System.out.print("\t");
            System.out.print("name");
            System.out.print("\t");
            System.out.print("grade");
            System.out.print("\n");
            System.out.println("────────────────────────");
            
              while(rs.next()){
                     System.out.print(rs.getInt("id"));
                     System.out.print("\t");
                     System.out.print(rs.getString("name"));
                     System.out.print("\t");
                     System.out.print(rs.getString("grade"));
                     System.out.print("\n");
                }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
}