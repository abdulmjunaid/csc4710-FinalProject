
public class tree {
	protected int treeId;
	protected int quoteId;
	protected String firstPic;
	protected String secondPic;
	protected String thirdPic;
	protected Double size;
	protected Double height;
	protected Double distance;
	protected String date;
	
	
	//constructors
    public tree() {
    }
 
    public tree(int treeId) 
    {
        this.treeId = treeId;
    }
    
    public tree(int treeId, int quoteId, String firstPic, String secondPic, String thirdPic, Double size, Double height, Double distance)
    {
    	this(quoteId, firstPic, secondPic, thirdPic, size, height, distance);
    	this.treeId = treeId;
    }
 

    public tree(int quoteId, String firstPic, String secondPic, String thirdPic, Double size, Double height, Double distance)
    {
    	this.quoteId = quoteId;
    	this.firstPic = firstPic;
    	this.secondPic = secondPic;
    	this.thirdPic = thirdPic;
    	this.size = size;
    	this.height = height;
    	this.distance = distance;
    }
    
    public tree(int treeId, int quoteId, String firstPic, String secondPic, String thirdPic, Double size, Double height, Double distance, String date)
    {
    	this(quoteId, firstPic, secondPic, thirdPic, size, height, distance);
    	this.treeId = treeId;
    	this.date = date;
    }
    
    //getter and setter methods
    public int getTreeId() {
    	return this.treeId;
    }
    public void setTreeId(int treeId) {
    	this.treeId = treeId;
    }

    public int getQuoteId() {
    	return this.quoteId;
    }
    public void setQuoteId(int quoteId) {
    	this.quoteId = quoteId;
    }

    public String getFirstPic() {
    	return this.firstPic;
    }
    public void setFirstPic(String firstPic) {
    	this.firstPic = firstPic;
    }
    
    public String getSecondPic() {
    	return this.secondPic;
    }
    public void setSecondPic(String secondPic) {
    	this.secondPic = secondPic;
    }
    
    public String getThirdPic() {
    	return this.thirdPic;
    }
    public void setThirdPic(String thirdPic) {
    	this.thirdPic = thirdPic;
    }
    
    public Double getSize() {
    	return this.size;
    }
    public void setSize(Double size) {
    	this.size = size;
    }
    
    public Double getHeight() {
    	return this.height;
    }
    public void setHeight(Double height) {
    	this.height = height;
    }
    
    public Double getDistance() {
    	return this.distance;
    }
    public void setDistance(Double distance) {
    	this.distance = distance;
    }
    
    public String getDate() {
    	return this.date;
    }
    public void setDate(String date) {
    	this.date = date;
    }
    
  }
