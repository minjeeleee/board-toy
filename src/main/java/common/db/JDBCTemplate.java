package common.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class JDBCTemplate {
   
   private static JDBCTemplate instance;
   private static PoolDataSource pds;
   
   private JDBCTemplate() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         final String DB_URL="jdbc:oracle:thin:@localhost:1521:xe";
   	     final String DB_USER = "BOARD_TOY";
   	     final String DB_PASSWORD = "1234";
   	     final String CONN_FACTORY_CLASS_NAME="oracle.jdbc.pool.OracleDataSource";
	   	   
  	    pds = PoolDataSourceFactory.getPoolDataSource();
        pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
        pds.setURL(DB_URL);
        pds.setUser(DB_USER);
        pds.setPassword(DB_PASSWORD);
        pds.setConnectionPoolName("JDBC_UCP_POOL");
        
        pds.setInitialPoolSize(5);

        pds.setMinPoolSize(5);

        pds.setMaxPoolSize(20);

        pds.setTimeoutCheckInterval(5);

        pds.setInactiveConnectionTimeout(10);
         
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
   }

  
   public static JDBCTemplate getInstance() {
      if(instance == null) {
         instance = new JDBCTemplate();
      }
      
      return instance;
   }
   
   public Connection getConnection() {
     	  
      Connection conn = null;
      
      try {
         conn = pds.getConnection();
         conn.setAutoCommit(false);
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return conn;
   }
   
   public void commit(Connection conn) {
      try {
         conn.commit();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void rollback(Connection conn) {
      try {
         conn.rollback();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void close(Connection conn) {
      try {
         if(conn != null && !conn.isClosed()) {
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void close(Statement stmt) {
      try {
         if(stmt != null && !stmt.isClosed()) {
            stmt.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void close(ResultSet rset) {
      try {
         if(rset != null && !rset.isClosed()) {
            rset.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public void close(Statement stmt, Connection conn) {
      close(stmt);
      close(conn);
   }
   
   public void close(ResultSet rset, Statement stmt) {
      close(rset);
      close(stmt);
   }
   
   public void close(ResultSet rset, Statement stmt, Connection conn) {
      close(rset);
      close(stmt);
      close(conn);
   }
   
}