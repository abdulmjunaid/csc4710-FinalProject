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
         
        return user;
    }
    
    public int submitRequest(quote quoteReq) throws SQLException {
    	int quoteId;
    	connect_func();         
		String sql = "insert into Quote(clientEmail, status, current, note, time) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, quoteReq.getClientEmail());
			preparedStatement.setString(2, quoteReq.getStatus());
			preparedStatement.setString(3, quoteReq.getCurrent());
			preparedStatement.setString(4, quoteReq.getNote());
			preparedStatement.setString(5, quoteReq.getTime());

		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        sql = "SELECT quoteId FROM Quote WHERE clientEmail = ? and time = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, quoteReq.getClientEmail());
        preparedStatement.setString(2, quoteReq.getTime());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        quoteId = resultSet.getInt("quoteId");
        resultSet.close();
        
        sql = "insert into QuoteHistory(email, status, note, time, quoteId) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, quoteReq.getClientEmail());
			preparedStatement.setString(2, quoteReq.getStatus());
			preparedStatement.setString(3, quoteReq.getNote());
			preparedStatement.setString(4, quoteReq.getTime());
			preparedStatement.setInt(5, quoteId);

		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        return quoteId;
    }
    
    public int issueBill(quote quotes, String currentUser) throws SQLException {
    	int billId;
    	connect_func(); 
    	System.out.println("1");
		String sql = "insert into Bill(time, quoteId, clientEmail, price, note, status, current) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, quotes.getTime());
			preparedStatement.setInt(2, quotes.getQuoteId());
			preparedStatement.setString(3, quotes.getClientEmail());
			preparedStatement.setDouble(4, quotes.getPrice());
			preparedStatement.setString(5, quotes.getNote());
			preparedStatement.setString(6, "open");
			preparedStatement.setString(7, quotes.getClientEmail());

		preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("2");
        sql = "SELECT billId FROM bill WHERE clientEmail = ? and time = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, quotes.getClientEmail());
        preparedStatement.setString(2, quotes.getTime());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        billId = resultSet.getInt("billId");
        resultSet.close();
        bill bills = getBill(billId);
        
        sql = "insert into BillHistory(email, status, price, note, time, quoteId, billId) values (?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, currentUser);
			preparedStatement.setString(2, "open");
			preparedStatement.setDouble(3, bills.getPrice());
			preparedStatement.setString(4, bills.getNote());
			preparedStatement.setString(5, quotes.getTime());
			preparedStatement.setInt(6, bills.getQuoteId());
			preparedStatement.setInt(7, bills.getBillId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
        System.out.println("3");
        return billId;
    }
    
    public quote getQuote(int quoteId) throws SQLException {
    	quote quotes = null;
        String sql = "SELECT * FROM Quote WHERE quoteId = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteId);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	String clientEmail = resultSet.getString("clientEmail");
            Double price = resultSet.getDouble("price");
            String timeFrame = resultSet.getString("timeFrame");
            String note = resultSet.getString("note"); 
            String status = resultSet.getString("status"); 
            String current = resultSet.getString("current"); 
            String time = resultSet.getString("time"); 

            quotes = new quote( quoteId,  clientEmail,  price,  timeFrame,  note,  status,  current);
        }
        resultSet.close();
         
        return quotes;
    }
    
    public bill getBill(int billId) throws SQLException {
    	bill bills = null;
        String sql = "SELECT * FROM bill WHERE billId = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, billId);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int quoteId = resultSet.getInt("quoteId");
        	String clientEmail = resultSet.getString("clientEmail");
            Double price = resultSet.getDouble("price");
            String note = resultSet.getString("note"); 
            String status = resultSet.getString("status"); 
            String current = resultSet.getString("current"); 
            String time = resultSet.getString("time"); 

            bills = new bill(billId, quoteId,  clientEmail,  price,  note,  status,  current);
        }
        resultSet.close();
         
        return bills;
    }
    
    
    public void denyQuote(quote quotes, String currentUser) throws SQLException {
    	String sql = "update Quote set status=?, note=?, time=? where quoteId=?";
        connect_func();
        System.out.println("hji");
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, "rejected");
        preparedStatement.setString(2, "Rejected by " + currentUser);
        preparedStatement.setString(3, quotes.getTime());
        preparedStatement.setInt(4, quotes.getQuoteId());
        preparedStatement.executeUpdate();
        preparedStatement.close();    
        
        sql = "insert into QuoteHistory(email, status, price, timeFrame, note, time, quoteId) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		preparedStatement.setString(2, "rejected");
		preparedStatement.setDouble(3, quotes.getPrice());
		preparedStatement.setString(4, quotes.getTimeFrame());
		preparedStatement.setString(5, "Rejected by " + currentUser);
		preparedStatement.setString(6, quotes.getTime());
		preparedStatement.setInt(7, quotes.getQuoteId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void acceptQuote(quote quotes, String currentUser) throws SQLException {
    	String sql = "update Quote set status=?, note=?, time=? where quoteId=?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, "accepted");
        preparedStatement.setString(2, "accepted by " + currentUser);
        preparedStatement.setString(3, quotes.getTime());
        preparedStatement.setInt(4, quotes.getQuoteId());
        preparedStatement.executeUpdate();
        preparedStatement.close();    
        
        sql = "insert into QuoteHistory(email, status, price, timeFrame, note, time, quoteId) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		preparedStatement.setString(2, "accepted");
		preparedStatement.setDouble(3, quotes.getPrice());
		preparedStatement.setString(4, quotes.getTimeFrame());
		preparedStatement.setString(5, "accepted by " + currentUser);
		preparedStatement.setString(6, quotes.getTime());
		preparedStatement.setInt(7, quotes.getQuoteId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void payBill(bill bills, String currentUser) throws SQLException {
    	String sql = "update Bill set status=?, note=?, time=? where billId=?";
        connect_func();
        user client = getUser(currentUser);
        String card = client.getCreditCard();
        String last4 = card.substring(card.length() - 4);
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, "accepted");
        preparedStatement.setString(2, "paid using card ending in " + last4);
        preparedStatement.setString(3, bills.getTime());
        preparedStatement.setInt(4, bills.getBillId());
        preparedStatement.executeUpdate();
        preparedStatement.close();    
        
        sql = "insert into BillHistory(email, status, price, note, time, quoteId, billId) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		preparedStatement.setString(2, "accepted");
		preparedStatement.setDouble(3, bills.getPrice());
		preparedStatement.setString(4, "paid by " + currentUser);
		preparedStatement.setString(5, bills.getTime());
		preparedStatement.setInt(6, bills.getQuoteId());
		preparedStatement.setInt(7, bills.getBillId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void rejectBill(bill bills, String currentUser) throws SQLException {
    	String sql = "update Bill set status=?, note=?, time=?, current=? where billId=?";
        connect_func();
        System.out.println("test");
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, "open");
        preparedStatement.setString(2, bills.getNote());
        preparedStatement.setString(3, bills.getTime());
        preparedStatement.setString(4, "davidsmith@treecutters.com");
        preparedStatement.setInt(5, bills.getBillId());
        preparedStatement.executeUpdate();
        preparedStatement.close();    
        
        sql = "insert into BillHistory(email, status, price, note, time, quoteId, billId) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		preparedStatement.setString(2, "open");
		preparedStatement.setDouble(3, bills.getPrice());
		preparedStatement.setString(4, bills.getNote());
		preparedStatement.setString(5, bills.getTime());
		preparedStatement.setInt(6, bills.getQuoteId());
		preparedStatement.setInt(7, bills.getBillId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void replyBill(bill bills, String currentUser) throws SQLException {
    	String sql = "update Bill set status=?, note=?, time=?, current=?, price=? where billId=?";
        connect_func();
        System.out.println("test");
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, "open");
        preparedStatement.setString(2, bills.getNote());
        preparedStatement.setString(3, bills.getTime());
        preparedStatement.setString(4, bills.getClientEmail());
        preparedStatement.setDouble(5, bills.getPrice());
        preparedStatement.setInt(6, bills.getBillId());
        preparedStatement.executeUpdate();
        preparedStatement.close();    
        
        sql = "insert into BillHistory(email, status, price, note, time, quoteId, billId) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		preparedStatement.setString(2, "open");
		preparedStatement.setDouble(3, bills.getPrice());
		preparedStatement.setString(4, bills.getNote());
		preparedStatement.setString(5, bills.getTime());
		preparedStatement.setInt(6, bills.getQuoteId());
		preparedStatement.setInt(7, bills.getBillId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void submitQuote(quote quotes, String currentUser) throws SQLException {
    	String sql = "update Quote set status=?, price=?, timeFrame=?, note=?, time=?, current=? where quoteId=?";
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, "open");
        preparedStatement.setDouble(2, quotes.getPrice());
        preparedStatement.setString(3, quotes.getTimeFrame());
        preparedStatement.setString(4, quotes.getNote());
        preparedStatement.setString(5, quotes.getTime());
        if (currentUser.equals("davidsmith@treecutters.com")) {
        	preparedStatement.setString(6, quotes.getClientEmail());
        }
        else {
        	preparedStatement.setString(6, "davidsmith@treecutters.com");
        }
        preparedStatement.setInt(7, quotes.getQuoteId());
        preparedStatement.executeUpdate();
        preparedStatement.close();    
        
        sql = "insert into QuoteHistory(email, status, price, timeFrame, note, time, quoteId) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, currentUser);
		preparedStatement.setString(2, "open");
		preparedStatement.setDouble(3, quotes.getPrice());
		preparedStatement.setString(4, quotes.getTimeFrame());
		preparedStatement.setString(5, quotes.getNote());
		preparedStatement.setString(6, quotes.getTime());
		preparedStatement.setInt(7, quotes.getQuoteId());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public List<tree> getTrees(int quoteId) throws SQLException {
    	List<tree> listTrees = new ArrayList<tree>(); 
    	
    	String sql = "SELECT * FROM tree WHERE quoteId = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quoteId);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
        	int treeId = resultSet.getInt("treeId");
            String firstPic = resultSet.getString("firstPic");
            String secondPic = resultSet.getString("secondPic");
            String thirdPic = resultSet.getString("thirdPic");
            Double size = resultSet.getDouble("size"); 
            Double height = resultSet.getDouble("height");
            Double distance = resultSet.getDouble("distance"); 
             
            tree trees = new tree( treeId,  quoteId,  firstPic,  secondPic,  thirdPic,  size,  height,  distance);
            listTrees.add(trees);
        }        
        resultSet.close();
        disconnect();  
        
    	return listTrees;
    }
    
    public void addTree(tree trees) throws SQLException {
    	connect_func();         
		String sql = "insert into Tree(quoteId, firstPic, secondPic, thirdPic, size, height, distance) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1, trees.getQuoteId());
			preparedStatement.setString(2, trees.getFirstPic());
			preparedStatement.setString(3, trees.getSecondPic());
			preparedStatement.setString(4, trees.getThirdPic());
			preparedStatement.setDouble(5, trees.getSize());
			preparedStatement.setDouble(6, trees.getHeight());
			preparedStatement.setDouble(7, trees.getDistance());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public List<quote> listQuotes(String email, String status) throws SQLException {
    	List<quote> listQuote = new ArrayList<quote>();         
    	
    	if (email.equals("davidsmith@treecutters.com")) {
    		String sql = "SELECT * FROM quote WHERE status = ?";
	    	connect_func();
	    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, status);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	int quoteId = resultSet.getInt("quoteId");
	            String clientEmail = resultSet.getString("clientEmail");
	            Double price = resultSet.getDouble("price");
	            String timeFrame = resultSet.getString("timeFrame");
	            String note = resultSet.getString("note"); 
	            String s = resultSet.getString("status"); 
	            String current = resultSet.getString("current"); 
	             
	            quote quote = new quote(quoteId, clientEmail,price, timeFrame, note,  s,  current);
	            listQuote.add(quote);
	        }        
	        resultSet.close();
	        disconnect();  
    	}
    	
    	else {
	    	String sql = "SELECT * FROM quote WHERE clientEmail = ? and status = ?";
	    	connect_func();
	    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, status);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	int quoteId = resultSet.getInt("quoteId");
	            String clientEmail = resultSet.getString("clientEmail");
	            Double price = resultSet.getDouble("price");
	            String timeFrame = resultSet.getString("timeFrame");
	            String note = resultSet.getString("note"); 
	            String s = resultSet.getString("status"); 
	            String current = resultSet.getString("current"); 
	             
	            quote quote = new quote(quoteId, clientEmail, price, timeFrame, note,  s,  current);
	            listQuote.add(quote);
	        }        
	        resultSet.close();
	        disconnect();  
    	}
        
        return listQuote;
    }
    
    public List<bill> listBills(String email, String status) throws SQLException {
    	List<bill> listBill = new ArrayList<bill>();         
    	
    	if (email.equals("davidsmith@treecutters.com")) {
    		String sql = "SELECT * FROM bill WHERE status = ?";
	    	connect_func();
	    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, status);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	int billId = resultSet.getInt("billId");
	        	int quoteId = resultSet.getInt("quoteId");
	            String clientEmail = resultSet.getString("clientEmail");
	            Double price = resultSet.getDouble("price");
	            String note = resultSet.getString("note"); 
	            String s = resultSet.getString("status"); 
	            String current = resultSet.getString("current"); 
	             
	            bill bill = new bill(billId, quoteId, clientEmail,price, note,  s,  current);
	            listBill.add(bill);
	        }        
	        resultSet.close();
	        disconnect();  
    	}
    	
    	else {
	    	String sql = "SELECT * FROM bill WHERE clientEmail = ? and status = ?";
	    	connect_func();
	    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	        preparedStatement.setString(1, email);
	        preparedStatement.setString(2, status);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
	        	int billId = resultSet.getInt("billId");
	        	int quoteId = resultSet.getInt("quoteId");
	            String clientEmail = resultSet.getString("clientEmail");
	            Double price = resultSet.getDouble("price");
	            String note = resultSet.getString("note"); 
	            String s = resultSet.getString("status"); 
	            String current = resultSet.getString("current"); 
	             
	            bill bill = new bill(billId, quoteId, clientEmail,price, note,  s,  current);
	            listBill.add(bill);
	        }        
	        resultSet.close();
	        disconnect();  
    	}
        
        return listBill;
    }
    
    public List<quote> listPendingQuotes() throws SQLException {
    	List<quote> listQuote = new ArrayList<quote>();         
    	
		String sql = "SELECT * FROM quote WHERE quoteId NOT IN (\n"
				+ "    SELECT quoteId FROM bill\n"
				+ ") and status = 'accepted';";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
        	int quoteId = resultSet.getInt("quoteId");
            String clientEmail = resultSet.getString("clientEmail");
            Double price = resultSet.getDouble("price");
            String timeFrame = resultSet.getString("timeFrame");
            String note = resultSet.getString("note"); 
            String s = resultSet.getString("status"); 
            String current = resultSet.getString("current"); 
             
            quote quote = new quote(quoteId, clientEmail,price, timeFrame, note,  s,  current);
            listQuote.add(quote);
        }        
        resultSet.close();
        disconnect();  
        
        return listQuote;
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
    
    public List<user> listBigClients() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "WITH ClientTreeCount AS (\n"
        		+ "    SELECT\n"
        		+ "        u.*,\n"
        		+ "        COUNT(t.treeId) AS tree_count,\n"
        		+ "        RANK() OVER (ORDER BY COUNT(t.treeId) DESC) AS rnk\n"
        		+ "    FROM\n"
        		+ "        User u\n"
        		+ "        JOIN Quote q ON u.email = q.clientEmail\n"
        		+ "        JOIN tree t ON q.quoteId = t.quoteId\n"
        		+ "        JOIN Bill b ON q.quoteId = b.quoteId\n"
        		+ "    GROUP BY\n"
        		+ "        u.email\n"
        		+ ")\n"
        		+ "SELECT\n"
        		+ "    *\n"
        		+ "FROM\n"
        		+ "    ClientTreeCount\n"
        		+ "WHERE\n"
        		+ "    rnk = 1;";      
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
    
    public List<quote> oneTreeQuotes() throws SQLException {
    	List<quote> listQuote = new ArrayList<quote>();         
    	
		String sql = "SELECT\n"
				+ "    q.*\n"
				+ "FROM\n"
				+ "    Quote q\n"
				+ "    JOIN tree t ON q.quoteId = t.quoteId\n"
				+ "WHERE\n"
				+ "    q.status = 'accepted'\n"
				+ "GROUP BY\n"
				+ "    q.quoteId\n"
				+ "HAVING\n"
				+ "    COUNT(t.treeId) = 1;";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
        	int quoteId = resultSet.getInt("quoteId");
            String clientEmail = resultSet.getString("clientEmail");
            Double price = resultSet.getDouble("price");
            String timeFrame = resultSet.getString("timeFrame");
            String note = resultSet.getString("note"); 
            String s = resultSet.getString("status"); 
            String current = resultSet.getString("current"); 
             
            quote quote = new quote(quoteId, clientEmail,price, timeFrame, note,  s,  current);
            listQuote.add(quote);
        }        
        resultSet.close();
        disconnect();  
    	
        return listQuote;
    }
    
    public List<user> prospectiveClients() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT\n"
        		+ "    u.*\n"
        		+ "FROM\n"
        		+ "    User u\n"
        		+ "WHERE\n"
        		+ "    u.role = 'client'\n"
        		+ "    AND EXISTS (\n"
        		+ "        SELECT 1\n"
        		+ "        FROM Quote q\n"
        		+ "        WHERE q.clientEmail = u.email\n"
        		+ "    ) AND NOT EXISTS (\n"
        		+ "    SELECT 1\n"
        		+ "    FROM Quote q\n"
        		+ "    WHERE q.clientEmail = u.email AND q.status = 'accepted'\n"
        		+ ");";      
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
    
    public List<tree> highestTrees() throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "WITH HighestTrees AS (\n"
        		+ "    SELECT t.*, DENSE_RANK() OVER (ORDER BY t.height DESC) AS rnk\n"
        		+ "    FROM User u\n"
        		+ "    JOIN Quote q ON u.email = q.clientEmail\n"
        		+ "    JOIN tree t ON q.quoteId = t.quoteId\n"
        		+ "    JOIN Bill b ON q.quoteId = b.quoteId\n"
        		+ "    WHERE t.treeId IS NOT NULL\n"
        		+ ")\n"
        		+ "SELECT *\n"
        		+ "FROM HighestTrees\n"
        		+ "WHERE rnk = 1;";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int treeId = resultSet.getInt("treeId");
        	int quoteId = resultSet.getInt("quoteId");
        	String firstPic = resultSet.getString("firstPic");
        	String secondPic = resultSet.getString("secondPic");
        	String thirdPic = resultSet.getString("thirdPic");
        	Double size = resultSet.getDouble("size");
        	Double height = resultSet.getDouble("height");
        	Double distance = resultSet.getDouble("distance");

             
            tree trees = new tree(treeId,  quoteId,  firstPic,  secondPic,  thirdPic,  size,  height,  distance);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    public List<bill> overdueBills() throws SQLException {
    	List<bill> listBill = new ArrayList<bill>();         
    	
		String sql = "SELECT distinct b.*\n"
				+ "FROM bill b, billhistory bh\n"
				+ "where Convert(bh.time, datetime) + Interval 1 week < now() \n"
				+ "and b.billId = bh.billId\n"
				+ "and b.status = 'open';";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
        	int billId = resultSet.getInt("billId");
        	int quoteId = resultSet.getInt("quoteId");
            String clientEmail = resultSet.getString("clientEmail");
            Double price = resultSet.getDouble("price");
            String note = resultSet.getString("note"); 
            String s = resultSet.getString("status"); 
            String current = resultSet.getString("current"); 
             
            bill bill = new bill(billId, quoteId, clientEmail,price, note,  s,  current);
            listBill.add(bill);
        }        
        resultSet.close();
        disconnect();  
    	
        return listBill;
    }
    
    public List<user> BadClients() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "with firstBill as(\n"
        		+ "    SELECT distinct bh.*\n"
        		+ "	FROM BillHistory bh\n"
        		+ "	JOIN (\n"
        		+ "		SELECT billId, MIN(time) AS firstEntryTime\n"
        		+ "		FROM BillHistory\n"
        		+ "		GROUP BY billId\n"
        		+ "	) firstEntries ON bh.billId = firstEntries.billId AND bh.time = firstEntries.firstEntryTime),\n"
        		+ "BadBills as(\n"
        		+ "	Select distinct b.* \n"
        		+ "	from firstBill fb, bill b\n"
        		+ "	where (Convert(fb.time, datetime) + Interval 1 week < now() \n"
        		+ "	and b.status = \"open\"\n"
        		+ "	and fb.billId = b.billId) or \n"
        		+ "	(Convert(fb.time, datetime) + Interval 1 week < Convert(b.time, datetime)\n"
        		+ "	and b.status = \"accepted\"\n"
        		+ "	and fb.billId = b.billId)\n"
        		+ "    ),\n"
        		+ "GoodBills as(\n"
        		+ "	Select distinct b.* \n"
        		+ "	from firstBill fb, bill b\n"
        		+ "	where (Convert(fb.time, datetime) + Interval 1 week > now() \n"
        		+ "	and b.status = \"open\"\n"
        		+ "	and fb.billId = b.billId) or \n"
        		+ "	(Convert(fb.time, datetime) + Interval 1 week > Convert(b.time, datetime)\n"
        		+ "	and b.status = \"accepted\"\n"
        		+ "	and fb.billId = b.billId)\n"
        		+ "    )\n"
        		+ "Select distinct u.* \n"
        		+ "from user u, BadBills bb, GoodBills gb\n"
        		+ "where u.email = bb.clientEmail and u.email not in \n"
        		+ "(Select clientEmail\n"
        		+ "from GoodBills);";      
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
    
    public List<user> GoodClients() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "with firstBill as(\n"
        		+ "    SELECT distinct bh.*\n"
        		+ "	FROM BillHistory bh\n"
        		+ "	JOIN (\n"
        		+ "		SELECT billId, MIN(time) AS firstEntryTime\n"
        		+ "		FROM BillHistory\n"
        		+ "		GROUP BY billId\n"
        		+ "	) firstEntries ON bh.billId = firstEntries.billId AND bh.time = firstEntries.firstEntryTime),\n"
        		+ "BadBills as(\n"
        		+ "	Select distinct b.* \n"
        		+ "	from firstBill fb, bill b\n"
        		+ "	where (Convert(fb.time, datetime) + Interval 1 Day < now() \n"
        		+ "	and b.status = \"open\"\n"
        		+ "	and fb.billId = b.billId) or \n"
        		+ "	(Convert(fb.time, datetime) + Interval 1 Day < Convert(b.time, datetime)\n"
        		+ "	and b.status = \"accepted\"\n"
        		+ "	and fb.billId = b.billId)\n"
        		+ "    ),\n"
        		+ "GoodBills as(\n"
        		+ "	Select distinct b.* \n"
        		+ "	from firstBill fb, bill b\n"
        		+ "	where (Convert(fb.time, datetime) + Interval 1 Day > now() \n"
        		+ "	and b.status = \"open\"\n"
        		+ "	and fb.billId = b.billId) or \n"
        		+ "	(Convert(fb.time, datetime) + Interval 1 Day > Convert(b.time, datetime)\n"
        		+ "	and b.status = \"accepted\"\n"
        		+ "	and fb.billId = b.billId)\n"
        		+ "    )\n"
        		+ "Select distinct u.* \n"
        		+ "from user u, BadBills bb, GoodBills gb\n"
        		+ "where u.email = gb.clientEmail and u.email not in \n"
        		+ "(Select clientEmail\n"
        		+ "from BadBills);";      
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
    
    public List<tree> treeDate(String email) throws SQLException {
        List<tree> listTree = new ArrayList<tree>();        
        String sql = "with firstBill as(\n"
        		+ "    SELECT bh.*\n"
        		+ "    FROM BillHistory bh\n"
        		+ "    JOIN (\n"
        		+ "        SELECT billId, MIN(time) AS firstEntryTime\n"
        		+ "        FROM BillHistory\n"
        		+ "        GROUP BY billId\n"
        		+ "    ) firstEntries ON bh.billId = firstEntries.billId AND bh.time = firstEntries.firstEntryTime)\n"
        		+ "select t.*, fb.time\n"
        		+ "from tree t, firstBill fb, quote q\n"
        		+ "where t.quoteId = fb.quoteId and q.quoteId = t.quoteId and q.clientEmail = (?)";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	int treeId = resultSet.getInt("treeId");
        	int quoteId = resultSet.getInt("quoteId");
        	String firstPic = resultSet.getString("firstPic");
        	String secondPic = resultSet.getString("secondPic");
        	String thirdPic = resultSet.getString("thirdPic");
        	Double size = resultSet.getDouble("size");
        	Double height = resultSet.getDouble("height");
        	Double distance = resultSet.getDouble("distance");
        	String date = resultSet.getString("time");

             
            tree trees = new tree(treeId,  quoteId,  firstPic,  secondPic,  thirdPic,  size,  height,  distance, date);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    public Double totalDueAmount(String email) throws SQLException {
        Double totalDueAmount = 0.0;      
        String sql = "select sum(b.price)\n"
        		+ "from bill b\n"
        		+ "where clientEmail = (?)";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            totalDueAmount = resultSet.getDouble("sum(b.price)");
        }        
        resultSet.close();
        disconnect();        
        return totalDueAmount;
    }
    
    public Double totalPaidAmount(String email) throws SQLException {
        Double totalPaidAmount = 0.0;      
        String sql = "select sum(b.price) p\n"
        		+ "from bill b\n"
        		+ "where clientEmail = (?) and status = 'accepted' ";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	totalPaidAmount = resultSet.getDouble("p");
        }        
        resultSet.close();
        disconnect();        
        return totalPaidAmount;
    }
    
    public int treeCount(String email) throws SQLException {
        int treeCount = 0;      
        String sql = "with TreeCount as(\n"
        		+ "	select t.quoteId, count(t.treeId) c\n"
        		+ "	from quote q, tree t\n"
        		+ "	where q.quoteId = t.quoteId and q.clientEmail = (?)\n"
        		+ "	group by t.quoteId)\n"
        		+ "select sum(tc.c) t\n"
        		+ "from TreeCount tc;";      
        connect_func();      
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
        	treeCount = resultSet.getInt("t");
        }        
        resultSet.close();
        disconnect();        
        return treeCount;
    }
    
    public List<user> listEasyClients() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "with QuoteCount as(\n"
        		+ "	Select distinct qh.quoteId, round((count(qh.quoteId)/15)) c\n"
        		+ "    from quote q, quoteHistory qh\n"
        		+ "    group by qh.quoteId\n"
        		+ "),\n"
        		+ "EasyQuotes as(\n"
        		+ "	Select distinct q.*, c\n"
        		+ "    from quote q, QuoteCount qc\n"
        		+ "    where (q.quoteId = qc.quoteId and q.status = 'accepted' and qc.c = 3) or\n"
        		+ "    (q.quoteId = qc.quoteId and q.status = 'open' and qc.c < 3)\n"
        		+ "),\n"
        		+ "BadQuotes as(\n"
        		+ "	Select distinct q.*, c\n"
        		+ "    from quote q, QuoteCount qc\n"
        		+ "    where (q.quoteId = qc.quoteId and q.status = 'accepted' and qc.c > 3) or\n"
        		+ "    (q.quoteId = qc.quoteId and q.status = 'open' and qc.c >= 3) or\n"
        		+ "    (q.quoteId = qc.quoteId and q.status = 'rejected')\n"
        		+ ")\n"
        		+ "Select distinct u.* \n"
        		+ "from user u, EasyQuotes eq, BadQuotes bq\n"
        		+ "where u.email = eq.clientEmail and u.email not in \n"
        		+ "(Select clientEmail\n"
        		+ "from BadQuotes);";      
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
					            "CHECK (role in ('client', 'david', 'root')), "+
					            "PRIMARY KEY(email) "+");"),
					        
					        "drop table if exists Quote; ",
					        ("CREATE TABLE if not exists Quote( " +
					        	"quoteId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"clientEmail VARCHAR(50) NOT NULL, "+
					        	"price DOUBLE, "+
					        	"timeFrame VARCHAR(255), "+
					        	"note VARCHAR(255), "+
					        	"status VARCHAR(8) NOT NULL DEFAULT 'request', "+
					        	"current VARCHAR(255) NOT NULL, "+
					        	"time VARCHAR(255) NOT NULL, "+
					        	"CHECK (status in ('request', 'open', 'accepted', 'rejected')), "+
					        	"PRIMARY KEY(quoteId), "+
					        	"FOREIGN KEY (current) REFERENCES User(email), "+
					        	"FOREIGN KEY (clientEmail) REFERENCES User(email) "+");"),
					        
					        "drop table if exists QuoteHistory; ",
					        ("CREATE TABLE if not exists QuoteHistory( " +
					        	"time VARCHAR(255) NOT NULL, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"email VARCHAR(50) NOT NULL, "+
					        	"price DOUBLE, "+
					        	"timeFrame VARCHAR(255), "+
					        	"note VARCHAR(255), "+
					        	"status VARCHAR(8) NOT NULL DEFAULT 'open', "+
					        	"CHECK (status in ('request', 'open', 'accepted', 'rejected')), "+
					        	"PRIMARY KEY(time, quoteId), "+
					        	"FOREIGN KEY (quoteId) REFERENCES Quote(quoteId), "+
					        	"FOREIGN KEY (email) REFERENCES User(email) "+");"),
					        
					        "drop table if exists Bill; ",
					        ("CREATE TABLE if not exists Bill( " +
					        	"billId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"clientEmail VARCHAR(50) NOT NULL, "+
					        	"price DOUBLE NOT NULL, "+
					        	"note VARCHAR(255), "+
					        	"current VARCHAR(255) NOT NULL, "+
					        	"status VARCHAR(8) NOT NULL DEFAULT 'open', "+
					        	"time VARCHAR(255) NOT NULL, "+
					        	"CHECK (status in ('open', 'accepted')), "+
					        	"PRIMARY KEY(billId), "+
					        	"FOREIGN KEY (quoteId) REFERENCES Quote(quoteId) "+");"),
					        
					        "drop table if exists BillHistory; ",
					        ("CREATE TABLE if not exists BillHistory( " +
					        	"time VARCHAR(255) NOT NULL, "+
					        	"billId INTEGER NOT NULL, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"email VARCHAR(50) NOT NULL, "+
					        	"price DOUBLE NOT NULL, "+
					        	"note VARCHAR(255), "+
					        	"status VARCHAR(8) NOT NULL DEFAULT 'open', "+
					        	"CHECK (status in ('open', 'accepted', 'rejected')), "+
					        	"PRIMARY KEY(time, billId), "+
					        	"FOREIGN KEY (email) REFERENCES User(email), "+
					        	"FOREIGN KEY (billId) REFERENCES Bill(billId), "+
					        	"FOREIGN KEY (quoteId) REFERENCES Quote(quoteId) "+");"),
					        
					        "drop table if exists Tree; ",
					        ("CREATE TABLE if not exists tree( " +
					        	"treeId INTEGER NOT NULL AUTO_INCREMENT, "+
					        	"quoteId INTEGER NOT NULL, "+
					        	"firstPic VARCHAR(255) NOT NULL, "+
					        	"secondPic VARCHAR(255) NOT NULL, "+
					        	"thirdPic VARCHAR(255) NOT NULL, "+
					        	"size DOUBLE NOT NULL, "+
					        	"height DOUBLE NOT NULL, "+
					        	"distance DOUBLE NOT NULL, "+
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
        					
        					("insert into Quote(time, clientEmail, price, timeFrame, note, status, current)"+
        			 "values ('2023-09-12 08:03:03', 'tatum@gmail.com', 300, 'monday', 'abc', 'open', 'tatum@gmail.com'),"+
        					"('2023-09-29 08:03:03', 'alvaro@gmail.com', 400, 'tuesday and thursday', 'def', 'accepted', 'alvaro@gmail.com'),"+
        					"('2023-09-16 08:03:03', 'stella@gmail.com', 200, 'friday', 'ghi', 'rejected', 'stella@gmail.com'),"+
        					"('2023-09-22 08:03:03', 'stella@gmail.com', 200, 'tuesday', 'jkl', 'open', 'stella@gmail.com'),"+
        					"('2023-08-21 08:03:03', 'andi@gmail.com', 400, 'thursday and friday', 'mno', 'accepted', 'andi@gmail.com'),"+
        					"('2023-07-19 08:03:03', 'reid@gmail.com', 300, 'tuesday', 'pqr', 'accepted', 'reid@gmail.com'),"+
        					"('2023-07-29 08:03:03', 'andi@gmail.com', 400, 'monday', 'stu', 'rejected', 'davidsmith@treecutters.com'),"+
        					"('2023-09-29 08:03:03', 'alvaro@gmail.com', 200, 'monday', 'vwx', 'open', 'davidsmith@treecutters.com'),"+
        					"('2023-08-29 08:03:03', 'sonny@gmail.com', 300, 'tuesday', 'yza', 'open', 'davidsmith@treecutters.com'),"+
        					"('2023-08-19 08:03:03', 'sonny@gmail.com', 600, 'tuesday and wednesday', 'yza', 'open', 'sonny@gmail.com'),"+
        					"('2023-09-32 08:03:03', 'margo@gmail.com', 300, 'tuesday', 'yza', 'accepted', 'margo@gmail.com'),"+
        					"('2023-07-31 08:03:03', 'ray@gmail.com', 200, 'wednesday', 'yza', 'accepted', 'davidsmith@treecutters.com'),"+
        					"('2023-09-19 08:03:03', 'alvaro@gmail.com', 250, 'thursday', 'def', 'accepted', 'alvaro@gmail.com'),"+
        					"('2023-08-29 08:03:03', 'tatum@gmail.com', 250, 'friday', 'def', 'open', 'tatum@gmail.com'),"+
        					"('2023-09-12 08:03:03', 'andi@gmail.com', 200, 'friday', 'bcd', 'accepted', 'davidsmith@treecutters.com');"),
        					
        					("insert into QuoteHistory(time, quoteId, email, price, timeFrame, note, status)"+
        			 "values ('2023-09-29 08:03:03', 1,'tatum@gmail.com', 400, 'monday', 'abc', 'request'),"+
        					"('2023-09-30 09:25:00', 1,'davidsmith@treecutters.com', 300, 'tuesday', 'def', 'open'),"+
        					"('2023-10-02 02:32:21', 1,'tatum@gmail.com', 350, 'tuesday', 'ghi', 'open'),"+
        					
        					"('2023-10-05 12:53:12', 2,'alvaro@gmail.com', 400, 'thursday and friday', 'mno', 'request'),"+
        					"('2023-10-07 09:43:12', 2,'davidsmith@treecutters.com', 400, 'thursday and friday', 'pqr', 'open'),"+
        					"('2023-10-08 12:53:12', 2,'alvaro@gmail.com', 400, 'thursday and friday', 'mno', 'accepted'),"+
        					
							"('2023-10-12 11:23:43', 3,'stella@gmail.com', 200, 'monday', 'vwx', 'request'),"+
        					"('2023-10-12 12:56:23', 3,'davidsmith@treecutters.com', 400, 'monday', 'stu', 'open'),"+
        					"('2023-10-12 15:23:43', 3,'stella@gmail.com', 200, 'monday', 'vwx', 'open'),"+
        					"('2023-10-14 09:45:45', 3,'davidsmith@treecutters.com', 400, 'monday', 'yza', 'open'),"+
        					"('2023-10-17 07:29:20', 3,'stella@gmail.com', 400, 'tuesday and monday', 'yza', 'rejected'),"+
        					
							"('2023-10-17 16:33:03', 4,'stella@gmail.com', 300, 'wednesday', 'yza', 'request'),"+
        					"('2023-10-18 18:43:34', 4,'davidsmith@treecutters.com', 400, 'tuesday', 'yza', 'open'),"+
        					
							"('2023-07-15 12:53:12', 6,'reid@gmail.com', 400, 'thursday and friday', 'mno', 'request'),"+
							"('2023-07-16 09:43:12', 6,'davidsmith@treecutters.com', 400, 'thursday and friday', 'pqr', 'open'),"+
							"('2023-07-19 12:53:12', 6,'reid@gmail.com', 400, 'thursday and friday', 'mno', 'accepted'),"+
        					
							"('2023-10-22 09:09:23', 5,'andi@gmail.com', 300, 'friday', 'def', 'request'),"+
        					"('2023-10-23 04:52:12', 5,'davidsmith@treecutters.com', 500, 'thursday', 'def', 'open'),"+
        					"('2023-10-24 09:09:23', 5,'andi@gmail.com', 300, 'friday', 'def', 'open'),"+
        					"('2023-10-24 10:23:17', 5,'davidsmith@treecutters.com', 450, 'friday', 'def', 'open'),"+
        					"('2023-10-26 12:12:12', 5,'andi@gmail.com', 450, 'friday', 'bcd', 'accepted');"),

        					("insert into Bill(time, quoteId, clientEmail, price, note, status, current)"+
					 "values ('2023-10-06 08:03:03', 1, 'tatum@gmail.com', 300, 'abc', 'accepted', 'tatum@gmail.com'),"+
							"('2023-10-08 15:43:12', 2, 'alvaro@gmail.com', 400, 'def', 'accepted', 'alvaro@gmail.com'),"+
							"('2023-10-20 08:03:03', 6, 'reid@gmail.com', 300, 'pqr', 'open', 'davidsmith@treecutters.com'),"+
							"('2023-10-30 08:03:03', 9, 'sonny@gmail.com', 300, 'yza', 'accepted', 'sonny@gmail.com'),"+
							"('2023-12-06 08:03:03', 15, 'andi@gmail.com', 200, 'bcd', 'open', 'davidsmith@treecutters.com'),"+
							"('2023-12-06 08:03:03', 10, 'sonny@gmail.com', 600, 'yza', 'open', 'sonny@gmail.com'),"+
							"('2023-12-18 08:03:03', 11, 'margo@gmail.com', 300, 'yza', 'accepted', 'margo@gmail.com'),"+
							"('2023-09-19 08:03:03', 12, 'ray@gmail.com', 200, 'yza', 'accepted', 'ray@gmail.com'),"+
							"('2023-09-20 08:03:03', 13, 'alvaro@gmail.com', 250, 'def', 'open', 'alvaro@gmail.com'),"+
							"('2023-09-21 08:03:03', 14, 'tatum@gmail.com', 250, 'def', 'accepted', 'tatum@gmail.com');"),        					

        					("insert into BillHistory(time, billId, quoteId, email, price, note, status)"+
        			 "values ('2023-10-04 08:03:03', 1, 1,'davidsmith@treecutters.com', 400, 'abc', 'open'),"+
        					"('2023-10-05 09:25:00', 1, 1,'tatum@gmail.com', 300, 'def', 'open'),"+
        					"('2023-10-06 02:32:21', 1, 1,'davidsmith@treecutters.com', 350, 'ghi', 'open'),"+
        					"('2023-10-06 18:54:45', 1, 1,'tatum@gmail.com', 350, 'jkl', 'accepted'),"+
        					
        					"('2023-10-08 12:53:12', 2, 2,'davidsmith@treecutters.com', 400, 'mno', 'open'),"+
        					"('2023-10-08 15:43:12', 2, 2,'alvaro@gmail.com', 400, 'pqr', 'accepted'),"+
        					
        					"('2023-10-18 12:56:23', 3, 6,'davidsmith@treecutters.com', 400, 'stu', 'open'),"+
        					"('2023-10-23 15:23:43', 3, 6,'reid@gmail.com', 200, 'vwx', 'open'),"+
        					"('2023-10-19 09:45:45', 3, 6,'davidsmith@treecutters.com', 400, 'yza', 'open'),"+
        					"('2023-10-20 07:29:20', 3, 6,'reid@gmail.com', 350, 'yza', 'open'),"+
        					
        					"('2023-10-20 18:43:34', 4, 9,'davidsmith@treecutters.com', 400, 'yza', 'open'),"+
        					"('2023-10-30 16:33:03', 4, 9,'sonny@gmail.com', 300, 'yza', 'accepted'),"+
        					
        					"('2023-12-03 12:52:12', 5, 15,'davidsmith@treecutters.com', 500, 'def', 'open'),"+
        					"('2023-12-04 13:09:23', 5, 15,'andi@gmail.com', 300, 'def', 'open'),"+
        					"('2023-12-05 13:23:17', 5, 15,'davidsmith@treecutters.com', 450, 'def', 'open'),"+
        					"('2023-12-06 14:12:12', 5, 15,'andi@gmail.com', 450, 'bcd', 'open'),"+  
        					
							"('2023-12-03 12:52:12', 7, 11,'davidsmith@treecutters.com', 500, 'def', 'open'),"+
							"('2023-12-06 13:09:23', 7, 11,'margo@gmail.com', 300, 'def', 'open'),"+
							"('2023-12-09 13:23:17', 7, 11,'davidsmith@treecutters.com', 450, 'def', 'open'),"+
							"('2023-12-18 14:12:12', 7, 11,'margo@gmail.com', 450, 'bcd', 'accepted'),"+  
        					
        					"('2023-11-29 12:52:12', 6, 10,'davidsmith@treecutters.com', 500, 'def', 'open'),"+
                			"('2023-12-04 13:09:23', 6, 10,'sonny@gmail.com', 300, 'def', 'open'),"+
                			"('2023-12-05 13:23:17', 6, 10,'davidsmith@treecutters.com', 450, 'def', 'open'),"+
                			"('2023-12-06 14:12:12', 6, 10,'sonny@gmail.com', 450, 'bcd', 'open');"), 
        					
        					
        					
        					("insert into Tree(quoteId, firstPic, secondPic, thirdPic, size, height, distance)"+
        			 "values (1, 'picture3', 'picture2', 'picture3', 2, 5, 4), "+
        							
        					"(2, 'picture3', 'picture3', 'picture3', 3, 3, 7), "+
        					"(2, 'picture3', 'picture3', 'picture3', 1, 2, 2), "+
        					"(2, 'picture3', 'picture3', 'picture3', 3, 6, 3), "+
        					
        					"(3, 'picture3', 'picture3', 'picture3', 4, 4, 4), "+
        					
        					"(4, 'picture3', 'picture3', 'picture3', 5, 5, 2), "+
        					"(4, 'picture3', 'picture3', 'picture3', 5, 7, 2), "+
        					
        					"(5, 'picture3', 'picture3', 'picture3', 7, 8, 4), "+
        					
        					"(6, 'picture3', 'picture3', 'picture3', 2, 12, 7), "+
        					
        					"(7, 'picture3', 'picture3', 'picture3', 8, 3, 8), "+
        					"(7, 'picture3', 'picture3', 'picture3', 7, 9, 7), "+
        					"(7, 'picture3', 'picture3', 'picture3', 3, 2, 1), "+
        					
        					"(8, 'picture3', 'picture3', 'picture3', 3, 6, 3), "+
        					
        					"(9, 'picture3', 'picture3', 'picture3', 8, 9, 2), "+
        					
        					"(10, 'picture3', 'picture3', 'picture3', 9, 2, 8); ")
        			
        								 
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
    
    
   
    
    
    
    
    
	
	

}
