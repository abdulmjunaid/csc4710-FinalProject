
public class quote {
	protected int quoteId;
	protected String clientEmail;
	protected Double price;
	protected String timeFrame;
	protected String note;
	protected String status;
	protected String current;
	protected String time;
	
	//constructors
    public quote() {
    }
 
    public quote(int quoteId) 
    {
        this.quoteId = quoteId;
    }
    
    public quote(int quoteId, String clientEmail, Double price, String timeFrame, String note, String status, String current)
    {
    	this(clientEmail, price, timeFrame, note, status, current);
    	this.quoteId = quoteId;
    }
 

    public quote(String clientEmail, Double price, String timeFrame, String note, String status, String current) 
    {
    	this.clientEmail = clientEmail;
    	this.price = price;
    	this.timeFrame = timeFrame;
    	this.note = note;
    	this.status = status;
    	this.current = current;
    }
    
    //getter and setter methods
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
    
    public String getTimeFrame() {
    	return this.timeFrame;
    }
    public void setTimeFrame(String timeFrame) {
    	this.timeFrame = timeFrame;
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
