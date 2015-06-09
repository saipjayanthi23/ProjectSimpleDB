
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

	
public class RouteFinding {
	
    static AmazonSimpleDB sdb = null;
	static String StationsDomain = "cloud_stations";
	static String start, end, output;
	
	
	/*public static void SimpleDBSample() {
		createClient();
		createQuery();
	}
	
	private static void createClient() {
        sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sdb.setRegion(usWest2);		
	}*/
	
	//private static void createQuery() {
    
	public static String createQuery(AmazonSimpleDB sdb) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("1046->");
        System.out.println("Query 6 - Route from Johnson Creek to Columbia Blvd on I-205 NB \n ");
        String selectExpression2 = "select * from `" + StationsDomain + "`" + " where locationtext like 'Columbia to I-205 NB%'"; 
        SelectRequest selectRequest2 = new SelectRequest(selectExpression2);
        List<Item> list2 = sdb.select(selectRequest2).getItems();
        for (Item item2: list2) {
        	List<Attribute> attrs2 = item2.getAttributes();
        	end = attrs2.get(4).getValue();
        }

		String selectExpression = "select * from `" + StationsDomain + "`" + " where locationtext like 'Johnson Cr NB%'"; 
        SelectRequest selectRequest = new SelectRequest(selectExpression);
        List<Item> list = sdb.select(selectRequest).getItems();
        for (Item item: list) {
        	List<Attribute> attrs = item.getAttributes();
        	start = attrs.get(8).getValue();
        	System.out.println("Downstream Route from 1046 to "+ end + "\n\nNext Downstream station id -  " + start);
        	
        	do
        	{
        		String selectExpression1 = "select downstream from `" + StationsDomain + "`" + " where stationid = '" + start + "'";
        	    SelectRequest selectRequest1 = new SelectRequest(selectExpression1);
                List<Item> list1 = sdb.select(selectRequest1).getItems();
                for (Item item1: list1) {
                	List<Attribute> attrs1 = item1.getAttributes();
                	for (Attribute attr1 : attrs1) {
                		System.out.println("Next Downstream station id -  " + attrs1.get(0).getValue());
                		
                	}
                	stringBuilder.append(start);
                	stringBuilder.append("->");
                	start = attrs1.get(0).getValue();
                }
                
        	}while(!start.equals(end));
        }
        stringBuilder.append(end);
        String finalstring = stringBuilder.toString();
        System.out.println(finalstring);
        return finalstring;
                  
	}
	public static void main(String[] args){
		RouteFinding rf = new RouteFinding();
	//	rf.SimpleDBSample();
	}

}






