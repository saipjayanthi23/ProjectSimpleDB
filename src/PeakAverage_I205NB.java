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

public class PeakAverage_I205NB {
	 static AmazonSimpleDB sdb = null;
		static String loopDomain = "cloud_loopdata";
		static String DetectorDomain = "cloud_detector";
		static String StationDomain = "cloud_stations";
		static float distance =0;
		static int count =0;
		static int sum =0;
		static int average =0;
		static float time =0 ;
		static float distance_total=0;
			
	/*	public static void SimpleDBSample() {
			createClient();
			createQuery();
			createQuery2();

		}
		
		private static void createClient() {
	        sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
			Region usWest2 = Region.getRegion(Regions.US_WEST_2);
			sdb.setRegion(usWest2);		
		}
		
		private static void createQuery() {
	*/
		public static float createQuery1(AmazonSimpleDB sdb) {
	        System.out.println("Query 5 PART- 1 Peek Travel Time between 7AM-9AM for all stations on I-205 NB freeway \n");
			String selectExpression1 = "select length from `" + StationDomain + "`" + " where highwayname LIKE '%I-205%' and shortdirection = 'N'";
	        SelectRequest selectRequest1 = new SelectRequest(selectExpression1);
	        List<Item> list1 = sdb.select(selectRequest1).getItems();
	        for (Item item1: list1) {
	        	distance = Float.parseFloat(item1.getAttributes().get(0).getValue());
	        	distance_total = distance_total+distance;
	        }
	        	System.out.println("Total Distance in miles " + distance_total);      
	        
			
	        String selectExpression2= "select detectorid from `" + DetectorDomain + "`" + " where locationtext LIKE '%NB%'";
	        SelectRequest selectRequest2 = new SelectRequest(selectExpression2);
	        List<Item> list2 = sdb.select(selectRequest2).getItems();
	        
	    	for (Item item2: list2) {
	        	List<Attribute> attrs2 = item2.getAttributes();
	           	for (Attribute attr2 : attrs2) {
	        		String selectExpression3 = "select speed from `" + loopDomain + "`" + " where starttime between '2011-09-22 07:00:00-07' and '2011-09-22 09:00:00-07' and detectorid  = '" + attrs2.get(0).getValue() + "'";
	        	
	                SelectRequest selectRequest3 = new SelectRequest(selectExpression3);
	                List<Item> list3 = sdb.select(selectRequest3).getItems();
	                for (Item item3: list3) {
	                 	List<Attribute> attrs3 = item3.getAttributes();
	                	for (Attribute attr3 : attrs3) {
	                		count = count + 1;
	                		if(!attrs3.get(0).getValue().isEmpty())
	                		sum = sum + Integer.parseInt(attrs3.get(0).getValue());
	                   	 }	  	     
	               }  	
	           	}                    	
	    	}
	                
	    	average = sum/count;
	    	System.out.println("Average speed in miles/hour " + average);
	        time = (distance_total/average)* 60;   
	        System.out.println("Average travel time in minutes " + time);
	        return time;
		}
		//private static void createQuery2() {
		public static float createQuery2(AmazonSimpleDB sdb) {
	        System.out.println("\n Query 5 PART- 2 Peek Travel Time between 4PM - 6PM for all stations on I-205 NB freeway \n");
	        count =0;
	        sum =0;
	        distance_total=0;
			String selectExpression1 = "select length from `" + StationDomain + "`" + " where highwayname LIKE '%I-205%' and shortdirection = 'N'" ;
	        SelectRequest selectRequest1 = new SelectRequest(selectExpression1);
	        List<Item> list1 = sdb.select(selectRequest1).getItems();
	        for (Item item1: list1) {
	        	distance = Float.parseFloat(item1.getAttributes().get(0).getValue());
	        	distance_total = distance_total+distance;    
	        }
	        System.out.println("Total Distance in miles " + distance_total);      
	        
	        String selectExpression2= "select detectorid from `" + DetectorDomain + "`" + " where locationtext LIKE '%NB%'";
	        SelectRequest selectRequest2 = new SelectRequest(selectExpression2);
	        List<Item> list2 = sdb.select(selectRequest2).getItems();
	        
	    	for (Item item2: list2) {
	        	List<Attribute> attrs2 = item2.getAttributes();
	        	for (Attribute attr2 : attrs2) {
	        		String selectExpression3 = "select speed from `" + loopDomain + "`" + " where starttime between '2011-09-22 16:00:00-07' and '2011-09-22 18:00:00-07' and detectorid  = '" + attrs2.get(0).getValue() + "'";
	        	    SelectRequest selectRequest3 = new SelectRequest(selectExpression3);
	                List<Item> list3 = sdb.select(selectRequest3).getItems();
	                for (Item item3: list3) {
	                 	List<Attribute> attrs3 = item3.getAttributes();
	                	for (Attribute attr3 : attrs3) {
	                		count = count + 1;
	                		if(!attrs3.get(0).getValue().isEmpty())
	                		sum = sum + Integer.parseInt(attrs3.get(0).getValue());
	                   	 }	  	     
	               }  	
	           	}                    	
	    	}
	                
	    	average = sum/count;
	    	System.out.println("Average speed in miles/hour " + average);
	        time = (distance_total/average)* 60;   
	        System.out.println("Average travel time in minutes " + time);
	        return time;
		}



public static void main(String[] args){
	PeakAverage_I205NB ptt1 = new PeakAverage_I205NB();
//	ptt1.SimpleDBSample();
}
}

