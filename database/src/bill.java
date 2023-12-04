
public class bill {
	protected int billId;
	protected int quoteId;
	protected String clientEmail;
	protected Double price;
	protected String note;
	protected String status;
	protected String current;
	protected String time;
	
	//constructors
    public bill() {
    }
 
    public bill(int billId) 
    {
        this.billId = billId;
    }
    
    public bill(int billId, int quoteId, String clientEmail, Double price, String note, String status, String current)
    {
    	this(quoteId, clientEmail, price, note, status, current);
    	this.billId = billId;
    }
 

    public bill(int quoteId, String clientEmail, Double price, String note, String status, String current) 
    {
    	this.quoteId = quoteId;
    	this.clientEmail = clientEmail;
    	this.price = price;
    	this.note = note;
    	this.status = status;
    	this.current = current;
    }
    
    //getter and setter methods
    public int getBillId() {
    	return this.billId;
    }
    public void setBillId(int billId) {
    	this.billId = billId;
    }
    
    public int getQuoteId() {
    	return this.quoteId;
    }
    public void setQuoteId(int quoteId) {
    	this.quoteId = quoteId;
    }
    
    public String getClientEmail() {
    	return this.clientEmail;
    }
    public void setClientEmail(String clientEmail) {
    	this.clientEmail = clientEmail;
    }
    
    public Double getPrice() {
    	return this.price;
    }
    public void setPrice(Double price) {
    	this.price = price;
    }
    
    public String getNote() {
    	return this.note;
    }
    public void setNote(String note) {
    	this.note = note;
    }
    
    public String getStatus() {
    	return this.status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public String getCurrent() {
    	return this.current;
    }
    public void setCurrent(String current) {
    	this.current = current;
    }
    
    public String getTime() {
    	return this.time;
    }
    public void setTime(String time) {
    	this.time = time;
    }
    
    
}
