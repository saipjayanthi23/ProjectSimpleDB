import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Date;



import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.BatchPutAttributesRequest;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.DeleteAttributesRequest;
import com.amazonaws.services.simpledb.model.DeleteDomainRequest;
import com.amazonaws.services.simpledb.model.GetAttributesRequest;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.ListDomainsResult;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.ReplaceableItem;
import com.amazonaws.services.simpledb.model.SelectRequest;
	
public class SingleStation {

    static AmazonSimpleDB sdb = null;
	static String loopDomain = "cloud_loopdata";
	static String DetectorDomain = "cloud_detector";
	static String StationDomain = "cloud_stations";
	static float distance =0;
	//static String start,end;
	//Date date = new Date();
	static int count =0;
	static int sum =0;
	static int average =0;
	static float time =0 ;
	static float finaltime = 0;
	
		
/*	public static void SimpleDBSample() {
		createClient();
		createQuery();

	}
	
	private static void createClient() {
        sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sdb.setRegion(usWest2);		
	}*/
	
//	private static void createQuery() {
	public static float createQuery(AmazonSimpleDB sdb) {
		String selectExpression1 = "select length from `" + StationDomain + "`" + " where locationtext LIKE '%Foster NB%'";
	    //    System.out.println("Selecting: " + selectExpression1 + "\n");
	        SelectRequest selectRequest1 = new SelectRequest(selectExpression1);
	        List<Item> list1 = sdb.select(selectRequest1).getItems();
	        for (Item item1: list1) {
	        	distance = Float.parseFloat(item1.getAttributes().get(0).getValue());        	
	        	System.out.println("Distance in miles " + distance);      
	        	
	        }
	        
	        
		String selectExpression = "select detectorid from `" + DetectorDomain + "`" + " where locationtext LIKE '%Foster NB%'";
       // System.out.println("Selecting: " + selectExpression + "\n");
        SelectRequest selectRequest = new SelectRequest(selectExpression);
        List<Item> list = sdb.select(selectRequest).getItems();
    	int cnt = 0;        
        for (Item item: list) {
        	cnt = cnt + 1;
        	List<Attribute> attrs = item.getAttributes();
        	System.out.println(attrs);    
        //	do {
        	for (Attribute attr2 : attrs) {
        		String selectExpression3 = "select speed from `" + loopDomain + "`" + " where starttime between '2011-09-22 12:00:00-00' and '2011-09-22 12:05:00-00' and detectorid  = '" + attrs.get(0).getValue() + "'";
        		//String selectExpression3 = "select speed from `" + loopDomain + "`" + " where detectorid  = '" + attrs.get(0).getValue() + "'";
        		//System.out.println("Selecting: " + selectExpression3 + "\n");
                SelectRequest selectRequest3 = new SelectRequest(selectExpression3);
                List<Item> list3 = sdb.select(selectRequest3).getItems();
                for (Item item3: list3) {
                 	List<Attribute> attrs3 = item3.getAttributes();
                 	//System.out.println(attrs3);
                 	count = count + 1;
            		if(!attrs3.get(0).getValue().isEmpty())
            		sum = sum + Integer.parseInt(attrs3.get(0).getValue());
            
                }
        	}
        	
      //  	}while(!start.equals(end));
        	average = sum/count;
        	System.out.println("Average speed in miles/sec " + average);
            time = (distance/average)* 3600;   
            System.out.println("Average travel time in seconds " + time);
            finaltime = finaltime + time;
        }
        finaltime = finaltime/cnt;
        System.out.println("Travel time in 5 min " + finaltime);
        return finaltime;
	}
	
	public static void main(String[] args){
		
		SingleStation ss = new SingleStation();
		//ss.SimpleDBSample();
	}
}
