package oficiali.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class ConexaoApi {
    
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;

    public ConexaoApi() throws ClassNotFoundException, SQLException {
        this.conn = getConnection(); 
        conn.setAutoCommit(false);
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/oficial_i","root","123");
    }
    
    public void setQuery(String query) throws SQLException {
        stmt = conn.prepareStatement(query);
    }
    
    public void setDate(int i, Date param) throws SQLException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(param);
		java.sql.Date dataAux = new java.sql.Date(cal.getTimeInMillis());
        stmt.setDate(i, dataAux);
    }
    
    public void setInt(int i, int param) throws SQLException {
        stmt.setInt(i, param);
    }
    
    public void setString(int i, String param) throws SQLException {
        stmt.setString(i, param);
    }
    
    public void executeQuery() throws SQLException {
        rs = stmt.executeQuery();
    }
    
    public void execute() throws SQLException {
        stmt.execute();
    }
    
    public int executeUpdate() throws SQLException {
        return stmt.executeUpdate();
    }
    
    public ResultSet getResultSet() {
        return rs;
    }
    
    public boolean next() throws SQLException {
        return rs.next();
    }
    
    public String getString(String field) throws SQLException {
        return rs.getString(field);
    }
    
    public int getInt(String field) throws SQLException {
        return rs.getInt(field);
    }
    
    public void close() throws SQLException {
        rs.close();
        stmt.close();
        conn.close();
    }
    
    public void commit() throws SQLException {
        conn.commit();
    }
}
