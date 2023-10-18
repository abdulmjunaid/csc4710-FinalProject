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
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");

             
            user users = new user(email,firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, cash_bal,PPS_bal);
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
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setInt(11, users.getCash_bal());		
			preparedStatement.setInt(12, users.getPPS_bal());		

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
        String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());		
		preparedStatement.setString(7, users.getAdress_street());		
		preparedStatement.setString(8, users.getAdress_city());		
		preparedStatement.setString(9, users.getAdress_state());		
		preparedStatement.setString(10, users.getAdress_zip_code());		
		preparedStatement.setInt(11, users.getCash_bal());		
		preparedStatement.setInt(12, users.getPPS_bal());
         
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
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");
            user = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,cash_bal,PPS_bal);
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
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "birthday DATE NOT NULL, " +
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "cash_bal DECIMAL(13,2) DEFAULT 1000,"+ 
					            "PPS_bal DECIMAL(13,2) DEFAULT 0,"+
					            "PRIMARY KEY (email) "+"); "),
					        

					        "drop table if exists Client; ",
					        ("CREATE TABLE if not exists Client( " +
					        	"clientId INTEGER NOT NULL AUTO_INCREMENT, "+
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
					            "PRIMARY KEY(clientId) "+");"),
					        
					        "drop table if exists Quote; ",
					        ("CREATE TABLE if not exists Quote( " +
					        	"quoteId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"clientId INTEGER NOT NULL, "+
					        	"note VARCHAR(255), "+
					        	"status BOOLEAN NOT NULL DEFAULT false, "+
					        	"PRIMARY KEY(quoteId), "+
					        	"FOREIGN KEY (clientId) REFERENCES Client(clientId) "+");"),
					        
					        "drop table if exists Bill; ",
					        ("CREATE TABLE if not exists Bill( " +
					        	"billId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"note VARCHAR(255), "+
					        	"status BOOLEAN NOT NULL DEFAULT false, "+
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

        
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, birthday, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal)"+
        			 "values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202','1000', '0'),"+
			    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345','1000', '0'),"+
			    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', '1234', 'ivan street', 'tata','CO','12561','1000', '0'),"+
			    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', '3214','marko street', 'brat', 'DU', '54321','1000', '0'),"+
			    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202','1000', '0'),"+
			    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000','1000', '0'),"+
			    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680','1000', '0'),"+
			    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579','1000', '0'),"+
			    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu','MH', '09876','1000', '0'),"+
			    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654','1000', '0'),"+
			    			"('root', 'default', 'default','pass1234', '2002-02-03', '0000', 'Default', 'Default', '0', '00000','1000','1000000000');"),
        					
        					("insert into Client(role, email, firstName, lastName, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, creditCard, phoneNumber, password)"+
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
        					
        					("insert into Quote(clientId, note, status)"+
        			 "values (2, 'abc', false),"+
        					"(3, 'def', true),"+
        					"(4, 'ghi', true),"+
        					"(4, 'jkl', false),"+
        					"(6, 'mno', false),"+
        					"(7, 'pqr', false),"+
        					"(6, 'stu', true),"+
        					"(3, 'vwx', false),"+
        					"(9, 'yza', false),"+
        					"(6, 'bcd', true);"),

        					("insert into Bill(quoteId, note, status)"+
					"values (1, 'abc', false),"+
							"(2, 'def', true),"+
							"(3, 'ghi', true),"+
							"(4, 'jkl', false),"+
							"(5, 'mno', false),"+
							"(6, 'pqr', false),"+
							"(7, 'stu', true),"+
							"(8, 'vwx', false),"+
							"(9, 'yza', false),"+
							"(10, 'bcd', true);"),        					

        					
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
