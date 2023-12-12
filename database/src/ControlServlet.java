import java.io.IOException;
import java.time.LocalTime;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private user User;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	 case "/viewQuotes":
        		 System.out.println("The action is: viewQuotes");
        		 quotesPage(request, response, "");
        		 break;
        	 case "/viewBills":
        		 System.out.println("The action is: viewBills");
        		 billsPage(request, response, "");
        		 break;
        	 case "/main":
        		 redirect(request, response);
        		 break;
        	 case "/requestQuote":
        		 requestQuotePage(request, response, "");
        		 break;
        	 case "/reviewRequest":
        		 reviewRequestPage(request, response, "");
        		 break;
        	 case "/submitRequest":
        		 submitRequest(request, response);
        		 break;
        	 case "/acceptQuote":
        		 System.out.println("accept");
        		 acceptQuote(request, response);
        		 break;
        	 case "/denyQuote":
        		 System.out.println("Deny");
        		 denyQuote(request, response);
        		 break;
        	 case "/sendQuote":
        		 sendQuotePage(request, response, "");
        		 break;
        	 case "/submitQuote":
        		 submitQuote(request, response);
        		 break;
        	 case "/reviewQuote":
        		 reviewQuotePage(request, response, "");
        		 break;
        	 case "/reviewBill":
        		 reviewBillPage(request, response, "");
        		 break;
        	 case "/davidReviewBill":
        		 davidReviewBillPage(request, response, "");
        		 break;
        	 case "/rejectBillPage":
        		 rejectBillPage(request, response, "");
        		 break;
        	 case "/replyBillPage":
        		 replyBillPage(request, response, "");
        		 break;
        	 case "/payBill":
        		 payBill(request, response);
        		 break;
        	 case "/rejectBill":
        		 rejectBill(request, response);
        		 break;
        	 case "/replyBill":
        		 replyBill(request, response);
        		 break;
        	 case "/issueBillPage":
        		 issueBillPage(request, response, "");
        		 break;
        	 case "/issueBill":
        		 issueBill(request, response);
        		 break;
        	 case "/statistics":
        		 statistics(request, response, "");
        		 
	    	}
        	
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
			request.setAttribute("bigClientList", userDAO.listBigClients());
			request.setAttribute("oneTreeQuotes", userDAO.oneTreeQuotes());
			request.setAttribute("prospectiveClients", userDAO.prospectiveClients());
			request.setAttribute("highestTrees", userDAO.highestTrees());
			request.setAttribute("overdueBills", userDAO.overdueBills());
			request.setAttribute("badClients", userDAO.BadClients());
			request.setAttribute("goodClients", userDAO.GoodClients());
			request.setAttribute("easyClients", userDAO.listEasyClients());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void quotesPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	if(currentUser.equals("davidsmith@treecutters.com")) {
	    		session = request.getSession();
	    		session.setAttribute("listRequestedQuote", userDAO.listQuotes(currentUser, "request"));
	    		session.setAttribute("listOpenQuote", userDAO.listQuotes(currentUser, "open"));
				session.setAttribute("listAcceptedQuote", userDAO.listQuotes(currentUser, "accepted"));
				session.setAttribute("listRejectedQuote", userDAO.listQuotes(currentUser, "rejected"));
				session.setAttribute("firstName", User.getFirstName());
				session.setAttribute("lastName", User.getLastName());
		    	request.getRequestDispatcher("davidquotes.jsp").forward(request, response);
	    	}
	    	else {
	    		session = request.getSession();
	    		session.setAttribute("listRequestedQuote", userDAO.listQuotes(currentUser, "request"));
	    		session.setAttribute("listOpenQuote", userDAO.listQuotes(currentUser, "open"));
				session.setAttribute("listAcceptedQuote", userDAO.listQuotes(currentUser, "accepted"));
				session.setAttribute("listRejectedQuote", userDAO.listQuotes(currentUser, "rejected"));
				session.setAttribute("firstName", User.getFirstName());
				session.setAttribute("lastName", User.getLastName());
		    	request.getRequestDispatcher("quotes.jsp").forward(request, response);
	    	}
	    }
	    
	    private void billsPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	if(currentUser.equals("davidsmith@treecutters.com")) {
	    		session = request.getSession();
	    		session.setAttribute("listOpenBill", userDAO.listBills(currentUser, "open"));
				session.setAttribute("listAcceptedBill", userDAO.listBills(currentUser, "accepted"));
				session.setAttribute("listIssueBills", userDAO.listPendingQuotes());
				session.setAttribute("firstName", User.getFirstName());
				session.setAttribute("lastName", User.getLastName());
		    	request.getRequestDispatcher("davidbills.jsp").forward(request, response);
	    	}
	    	else {
	    		session = request.getSession();
	    		session.setAttribute("listOpenBill", userDAO.listBills(currentUser, "open"));
				session.setAttribute("listAcceptedBill", userDAO.listBills(currentUser, "accepted"));
				session.setAttribute("firstName", User.getFirstName());
				session.setAttribute("lastName", User.getLastName());
		    	request.getRequestDispatcher("bills.jsp").forward(request, response);
	    	}
	    }
	    
	    private void requestQuotePage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("request quote");
			request.setAttribute("client", currentUser);
	    	request.getRequestDispatcher("requestquote.jsp").forward(request, response);
	    }
	    
	    private void reviewRequestPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("review request");
	    	System.out.println();
	    	session = request.getSession();
	    	int quoteId = Integer.valueOf(request.getParameter("requestId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listQuote", userDAO.getQuote(quoteId));
			session.setAttribute("listTrees", userDAO.getTrees(quoteId));
	    	request.getRequestDispatcher("reviewrequest.jsp").forward(request, response);
	    	
	    }
	    
	    private void denyQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("deny request");
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
	    	quote quotes = userDAO.getQuote(quoteId);
	    	quotes.setTime(LocalTime.now().toString());
			userDAO.denyQuote(quotes, currentUser);
			
			quotesPage(request, response, "");
	    }
	    
	    private void acceptQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("accept request");
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
	    	quote quotes = userDAO.getQuote(quoteId);
	    	quotes.setTime(LocalTime.now().toString());
			userDAO.acceptQuote(quotes, currentUser);
			
			quotesPage(request, response, "");
	    }
	    
	    private void payBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("pay bill");
	    	int billId = Integer.valueOf(request.getParameter("billId"));
	    	bill bills = userDAO.getBill(billId);
	    	bills.setTime(LocalTime.now().toString());
			userDAO.payBill(bills, currentUser);
			
			billsPage(request, response, "");
	    }
	    
	    private void rejectBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("reject bill");
	    	int billId = Integer.valueOf(request.getParameter("billId"));
	    	String note = String.valueOf(request.getParameter("note"));
	    	bill bills = userDAO.getBill(billId);
	    	bills.setNote(note);
	    	bills.setTime(LocalTime.now().toString());
			userDAO.rejectBill(bills, currentUser);
			
			billsPage(request, response, "");
	    }
	    
	    private void replyBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("reject bill");
	    	int billId = Integer.valueOf(request.getParameter("billId"));
	    	Double price = Double.valueOf(request.getParameter("price"));
	    	String note = String.valueOf(request.getParameter("note"));
	    	bill bills = userDAO.getBill(billId);
	    	bills.setNote(note);
	    	bills.setPrice(price);
	    	bills.setTime(LocalTime.now().toString());
			userDAO.replyBill(bills, currentUser);
			
			billsPage(request, response, "");
	    }
	    
	    private void issueBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	    	System.out.println("issue bill");
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
	    	Double price = Double.valueOf(request.getParameter("price"));
	    	String note = String.valueOf(request.getParameter("note"));
	    	quote quote = userDAO.getQuote(quoteId);
	    	quote.setNote(note);
	    	quote.setPrice(price);
	    	quote.setTime(LocalTime.now().toString());
			userDAO.issueBill(quote, currentUser);
			
			billsPage(request, response, "");
	    }
	    
	    private void sendQuotePage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("initial quote");
	    	session = request.getSession();
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listQuote", userDAO.getQuote(quoteId));
	    	request.getRequestDispatcher("initialquote.jsp").forward(request, response);
	    }
	    
	    private void rejectBillPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("reject bill");
	    	session = request.getSession();
	    	int billId = Integer.valueOf(request.getParameter("billId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listBill", userDAO.getBill(billId));
	    	request.getRequestDispatcher("rejectbill.jsp").forward(request, response);
	    }
	    
	    private void replyBillPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("reply bill");
	    	session = request.getSession();
	    	int billId = Integer.valueOf(request.getParameter("billId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listBill", userDAO.getBill(billId));
	    	request.getRequestDispatcher("replybill.jsp").forward(request, response);
	    }
	    
	    private void issueBillPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("issue bill");
	    	session = request.getSession();
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listQuote", userDAO.getQuote(quoteId));
	    	request.getRequestDispatcher("issuebill.jsp").forward(request, response);
	    }
	    
	    private void submitRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String firstPic, secondPic, thirdPic;
	    	Double size, height, distance;
	    	String tree = request.getParameter("total");
	    	int count = Integer.valueOf(tree.substring(7));
	    	
	    	String note = request.getParameter("note");
	    	
	    	quote quoteReq = new quote();
	    	tree trees  = new tree();
	    	quoteReq.setClientEmail(currentUser);
	    	quoteReq.setStatus("request");
	    	quoteReq.setCurrent("davidsmith@treecutters.com");
	    	quoteReq.setTime(LocalTime.now().toString());
	    	quoteReq.setNote(note);
	    	int quoteId = userDAO.submitRequest(quoteReq);
	    	System.out.println(quoteId);
	    	
	    	for(int i=1; i<=count; i++) {
	    		firstPic = request.getParameter("firstPic"+i);
	    		secondPic = request.getParameter("secondPic"+i);
	    		thirdPic = request.getParameter("thirdPic"+i);
	    		size = Double.valueOf(request.getParameter("size"+i));
	    		height = Double.valueOf(request.getParameter("height"+i));
	    		distance = Double.valueOf(request.getParameter("distance"+i));
	    		
	    		trees.setFirstPic(firstPic);
	    		trees.setSecondPic(secondPic);
	    		trees.setThirdPic(thirdPic);
	    		trees.setSize(size);
	    		trees.setHeight(height);
	    		trees.setDistance(distance);
	    		trees.setQuoteId(quoteId);
	    		
	    		userDAO.addTree(trees);
	    	}
	    	
	    	quotesPage(request, response, "");
	    }
	    
	    private void reviewQuotePage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("review quote");
	    	System.out.println();
	    	session = request.getSession();
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listQuote", userDAO.getQuote(quoteId));
	    	request.getRequestDispatcher("reviewquote.jsp").forward(request, response);
	    	
	    }
	    
	    private void statistics(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("statistics");
	    	System.out.println();
	    	session = request.getSession();
	    	String email = String.valueOf(request.getParameter("email"));
	    	user client = userDAO.getUser(email);
	    	session.setAttribute("firstName", client.getFirstName());
	    	session.setAttribute("lastName", client.getLastName());
			session.setAttribute("email", email);
			session.setAttribute("treeStats", userDAO.treeDate(email));
			session.setAttribute("totalDueAmount", userDAO.totalDueAmount(email));
			session.setAttribute("totalPaidAmount", userDAO.totalPaidAmount(email));
			session.setAttribute("treeCount", userDAO.treeCount(email));
	    	request.getRequestDispatcher("statistics.jsp").forward(request, response);
	    	
	    }
	    
	    private void reviewBillPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("review bill");
	    	System.out.println();
	    	session = request.getSession();
	    	int billId = Integer.valueOf(request.getParameter("billId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listBill", userDAO.getBill(billId));
	    	request.getRequestDispatcher("reviewbill.jsp").forward(request, response);
	    	
	    }
	    
	    private void davidReviewBillPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("review bill");
	    	System.out.println();
	    	session = request.getSession();
	    	int billId = Integer.valueOf(request.getParameter("billId"));
			session.setAttribute("client", currentUser);
			session.setAttribute("listBill", userDAO.getBill(billId));
	    	request.getRequestDispatcher("davidreviewbill.jsp").forward(request, response);
	    	
	    }
	    
	    private void submitQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoteId = Integer.valueOf(request.getParameter("quoteId"));
	    	Double price = Double.valueOf(request.getParameter("price"));
	    	String timeFrame = request.getParameter("timeframe");
	    	String clientEmail = request.getParameter("clientEmail");
	    	String note = request.getParameter("note");
	    	
	    	quote quotes = new quote();
	    	quotes.setQuoteId(quoteId);
	    	quotes.setClientEmail(clientEmail);
	    	quotes.setPrice(price);
	    	quotes.setTimeFrame(timeFrame);
	    	quotes.setStatus("open");
	    	quotes.setTime(LocalTime.now().toString());
	    	quotes.setNote(note);
	    	
	    	userDAO.submitQuote(quotes, currentUser);
	    	quotesPage(request, response, "");
	    }

	    
	    protected void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	if (currentUser.equals("davidsmith@treecutters.com")) {
	    		System.out.println("redirecting to david");
	    		request.getRequestDispatcher("davidsmith.jsp").forward(request, response);
	    	}
	    	else {
	    		request.getRequestDispatcher("activitypage.jsp").forward(request, response);		 			 
	    	}
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 currentUser = email;
			 	 User = userDAO.getUser(currentUser);
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if (email.equals("davidsmith@treecutters.com") && password.equals("david1234")) {
	    		 currentUser = email;
			 	 User = userDAO.getUser(currentUser);
			 	 session = request.getSession();
	    		 System.out.println("Login Successful! Redirecting to David");
	    		 session.setAttribute("firstName", User.getFirstName());
				 session.setAttribute("lastName", User.getLastName());
				 request.getRequestDispatcher("davidsmith.jsp").forward(request, response);
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 currentUser = email;
			 	 User = userDAO.getUser(currentUser);
			 	 session = request.getSession();
			 	 System.out.println("Login Successful! Redirecting to Client");
	    		 session.setAttribute("firstName", User.getFirstName());
				 session.setAttribute("lastName", User.getLastName());
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);	 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String role = request.getParameter("role");
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code"); 
	   	 	String creditCard = request.getParameter("creditCard"); 	 
	   	 	String phoneNumber = request.getParameter("phoneNumber"); 
	   	 	String password = request.getParameter("password");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(role, email,firstName, lastName, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, creditCard, phoneNumber, password);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


