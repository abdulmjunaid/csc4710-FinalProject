import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String role = resultSet.getString("role");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String creditCard = resultSet.getString("creditCard");
            String phoneNumber = resultSet.getString("phoneNumber");
            String password = resultSet.getString("password");

             
            user users = new user(role, email,firstName, lastName, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, creditCard, phoneNumber, password);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(role,email,firstName,lastName,adress_street_num,adress_street,adress_city,adress_state,adress_zip_code,creditCard,phoneNumber,password) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getRole());
			preparedStatement.setString(2, users.getEmail());
			preparedStatement.setString(3, users.getFirstName());
			preparedStatement.setString(4, users.getLastName());
			preparedStatement.setString(5, users.getAdress_street_num());		
			preparedStatement.setString(6, users.getAdress_street());		
			preparedStatement.setString(7, users.getAdress_city());		
			preparedStatement.setString(8, users.getAdress_state());		
			preparedStatement.setString(9, users.getAdress_zip_code());		
			preparedStatement.setString(10, users.getCreditCard());		
			preparedStatement.setString(11, users.getPhoneNumber());	
			preparedStatement.setString(12, users.getPassword());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set role=?, firstName=?, lastName =?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, creditCard=?, phoneNumber =?, password=? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
        preparedStatement.setString(2, users.getRole());
		preparedStatement.setString(3, users.getFirstName());
		preparedStatement.setString(4, users.getLastName());
		preparedStatement.setString(5, users.getAdress_street_num());		
		preparedStatement.setString(6, users.getAdress_street());		
		preparedStatement.setString(7, users.getAdress_city());		
		preparedStatement.setString(8, users.getAdress_state());		
		preparedStatement.setString(9, users.getAdress_zip_code());		
		preparedStatement.setString(10, users.getCreditCard());		
		preparedStatement.setString(11, users.getPhoneNumber());
		preparedStatement.setString(12, users.getPassword());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String role = resultSet.getString("role");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String creditCard = resultSet.getString("creditCard");
            String phoneNumber = resultSet.getString("phoneNumber");
            String password = resultSet.getString("password");
            user = new user(role, email, firstName, lastName, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, creditCard, phoneNumber, password);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {
        					
        					"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",

					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					        	"role VARCHAR(20) DEFAULT 'client', "+
					        	"email VARCHAR(50) NOT NULL, "+
					        	"firstName VARCHAR(50) NOT NULL, "+
					        	"lastName VARCHAR(50) NOT NULL, "+
					        	"adress_street_num VARCHAR(4), "+ 
					            "adress_street VARCHAR(30), "+ 
					            "adress_city VARCHAR(20), " + 
					            "adress_state VARCHAR(2), "+ 
					            "adress_zip_code VARCHAR(5), "+ 
					            "creditCard VARCHAR(20), "+
					            "phoneNumber VARCHAR(10), "+
					            "password VARCHAR(50) NOT NULL, "+
					            "PRIMARY KEY(email) "+");"),
					        
					        "drop table if exists Quote; ",
					        ("CREATE TABLE if not exists Quote( " +
					        	"quoteId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"email VARCHAR(50) NOT NULL, "+
					        	"note VARCHAR(255), "+
					        	"dstatus BOOLEAN NOT NULL DEFAULT false, "+
					        	"cstatus BOOLEAN NOT NULL DEFAULT false, "+
					        	"PRIMARY KEY(quoteId), "+
					        	"FOREIGN KEY (email) REFERENCES User(email) "+");"),
					        
					        "drop table if exists Bill; ",
					        ("CREATE TABLE if not exists Bill( " +
					        	"billId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"note VARCHAR(255), "+
					        	"dstatus BOOLEAN NOT NULL DEFAULT false, "+
					        	"cstatus BOOLEAN NOT NULL DEFAULT false, "+
					        	"PRIMARY KEY(billId), "+
					        	"FOREIGN KEY (quoteId) REFERENCES Quote(quoteId) "+");"),
					        
					        "drop table if exists Tree; ",
					        ("CREATE TABLE if not exists tree( " +
					        	"treeId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"firstPic VARCHAR(255), "+
					        	"secondPic VARCHAR(255), "+
					        	"thirdPic VARCHAR(255), "+
					        	"size INTEGER NOT NULL, "+
					        	"height INTEGER NOT NULL, "+
					        	"distance INTEGER NOT NULL, "+
					        	"PRIMARY KEY(treeId), "+
					        	"FOREIGN KEY (quoteId) REFERENCES Quote(quoteId) "+");"),
					        
        					};

        
        String[] TUPLES = {
        		
        					("insert into User(role, email, firstName, lastName, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, creditCard, phoneNumber, password)"+
        			 "values ('david', 'davidsmith@treecutters.com', 'David', 'Smith', '1234', 'main st', 'Detroit', 'MI', '48202', '378282246310005', '3130233245', 'david1234'), "+
        					"('client', 'tatum@gmail.com', 'Tatum', 'Brandt', '2341', 'elm st', 'Detroit', 'MI', '48201', '312382246310005', '3120433265', 'tatum1234'), "+
        					"('client', 'alvaro@gmail.com', 'Alvaro', 'Oliver', '9982', 'west rd', 'Dearborn', 'MI', '48301', '234382246399805', '3764533265', 'alvaro1234'), "+
        					"('client', 'stella@gmail.com', 'Stella', 'Marquez', '7485', 'oak ave', 'Detroit', 'MI', '48202', '712987246310325', '1320439295', 'stella1234'), "+
        					"('client', 'ray@gmail.com', 'Ray', 'Gonzalez', '2783', 'ridge rd', 'Novi', 'MI', '49810', '612399846310305', '3920453263', 'ray1234'), "+
        					"('client', 'andi@gmail.com', 'Andi', 'Carson', '8349', 'ford rd', 'Detroit', 'MI', '48203', '712389946410005', '2324439265', 'andi1234'), "+
        					"('client', 'reid@gmail.com', 'Reid', 'Stone', '7485', 'noth ave', 'Detroit', 'MI', '48202', '417882988940005', '7344598260', 'reid1234'), "+
        					"('client', 'margo@gmail.com', 'Margo', 'Dunn', '8923', 'main rd', 'Troy', 'MI', '48222', '389382987990305', '7345533005', 'margo1234'), "+
        			 		"('client', 'sonny@gmail.com', 'Sonny', 'Vu', '5876', 'south st', 'Troy', 'MI', '48222', '312382987310005', '7134533265', 'sonny1234'), "+
        					"('root', 'root', 'default', 'default', '0000', 'default', 'default', '00', '00000', '000000000000000', '0000000000', 'pass1234'); "),
        					
        					("insert into Quote(email, note, dstatus, cstatus)"+
        			 "values ('tatum@gmail.com', 'abc', false, false),"+
        					"('alvaro@gmail.com', 'def', true, true),"+
        					"('stella@gmail.com', 'ghi', true, true),"+
        					"('stella@gmail.com', 'jkl', false, true),"+
        					"('andi@gmail.com', 'mno', false, false),"+
        					"('reid@gmail.com', 'pqr', true, false),"+
        					"('andi@gmail.com', 'stu', true, true),"+
        					"('alvaro@gmail.com', 'vwx', false, false),"+
        					"('sonny@gmail.com', 'yza', false, false),"+
        					"('andi@gmail.com', 'bcd', true, true);"),

        					("insert into Bill(quoteId, note, dstatus, cstatus)"+
					"values (1, 'abc', false, false),"+
							"(2, 'def', true, true),"+
							"(3, 'ghi', true, true),"+
							"(4, 'jkl', false, true),"+
							"(5, 'mno', true, false),"+
							"(6, 'pqr', false,false),"+
							"(7, 'stu', true, true),"+
							"(8, 'vwx', true, false),"+
							"(9, 'yza', false, true),"+
							"(10, 'bcd', true, true);"),        					

        					
        					("insert into Tree(quoteId, size, height, distance)"+
        			"values (1, 2, 5, 4), "+
        					"(2, 3, 3, 7), "+
        					"(3, 4, 4, 4), "+
        					"(4, 5, 5, 2), "+
        					"(5, 7, 8, 4), "+
        					"(6, 2, 2, 7), "+
        					"(7, 8, 3, 8), "+
        					"(8, 3, 6, 3), "+
        					"(9, 8, 9, 2), "+
        					"(10, 9, 2, 8); ")
        			
        								 
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
