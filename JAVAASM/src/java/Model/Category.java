package Model;
/**
 *
 * @author Marson(TingLe)
 */
public class Category {
    private String  catID;     //CategoryID
    private String  catName;   //CategoryName
    
    public Category(){
        
    }
    
    public Category(String catID, String catName){
        this.catID = catID;
        this.catName = catName;
    }

    public String getCatID() {
        return catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    
   
    
}
