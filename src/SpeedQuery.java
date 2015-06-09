import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	
public class SpeedQuery {
	    
	    static AmazonSimpleDB sdb = null;
		static String loopDomain = "cloud_loopdata";
		static int count = 0;
		
	/*	public static void SimpleDBSample() {
			createClient();
			createQuery();
		}
		
		private static void createClient() {
	        sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);		
		}
		*/
		//private static void createQuery() {
	      public static int createQuery(AmazonSimpleDB sdb){
		   System.out.println("Query 1 - Count high speeds > 100 \n");
			 
			String selectExpression = "select count(*) from `" + loopDomain + "`" + " where speed between '101' and '200' ";
	        System.out.println("Selecting: " + selectExpression + "\n");
	        SelectRequest selectRequest = new SelectRequest(selectExpression);
	        List<Item> list = sdb.select(selectRequest).getItems();
	        for (Item item: list) {
	        	List<Attribute> attrs = item.getAttributes();
	        	for (Attribute attr : attrs) {
	        		System.out.println(" number of speeds > 100  is " + attrs.get(0).getValue());
	        		count = Integer.parseInt(attrs.get(0).getValue());
	        	}
	        }
			return count;	
		}
		public static void main(String[] args){
			SpeedQuery sq = new SpeedQuery();
		//	sq.SimpleDBSample();
		}
}
