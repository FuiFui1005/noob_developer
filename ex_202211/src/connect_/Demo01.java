package connect_;

public class Demo01 {

	public static void main(String[] args) {
		MysqlConnection con = new MysqlConnection();
		
		con.connect();
		con.insert("문종성2", "34", "경기도", null, null);
		con.close();
	}
}
