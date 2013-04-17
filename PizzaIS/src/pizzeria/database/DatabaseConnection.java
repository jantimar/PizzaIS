package pizzeria.database;

import java.sql.*;

public class DatabaseConnection {
	private Connection connection;
	private Statement st;
	private ResultSet rs;
	
	public DatabaseConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String port = "8889"; // defaultne vacsinou 3306
		String dbname = "ispizza";
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:"+port+"/"+dbname+"?user="+user+"&password="+password;
		
		this.connection = DriverManager.getConnection(url);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public ResultSet getResultSet(String query) throws SQLException {
		this.st = this.connection.createStatement();
        this.rs = st.executeQuery(query);
		return this.rs;
	}
	
	public void closeConnection() throws SQLException {
		if (this.rs != null) {
            this.rs.close();
        }
        if (this.st != null) {
            this.st.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
	}
}
