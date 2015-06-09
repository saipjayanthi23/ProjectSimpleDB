/*
* Copyright 2010-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License").
	 * You may not use this file except in compliance with the License.
	 * A copy of the License is located at
	 *
	 *  http://aws.amazon.com/apache2.0
	 *
	 * or in the "license" file accompanying this file. This file is distributed
	 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
	 * express or implied. See the License for the specific language governing
	 * permissions and limitations under the License.
*/
	import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer.*;
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

	public class TotalVolume {
	
    static AmazonSimpleDB sdb = null;
	static String loopDomain = "cloud_loopdata";
	static String DetectorDomain = "cloud_detector";
	static int  temp=0;
	static int finalvolume =0;
	
	/*public static void SimpleDBSample() {
		createClient();
		createQuery();
	}
	
	private static void createClient() {
        sdb = new AmazonSimpleDBClient(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sdb.setRegion(usWest2);		
	}
	
	private static void createQuery() {
	*/
	public static int createQuery(AmazonSimpleDB sdb) {
		System.out.println("Query 2 - Total volume for station Foster NB \n ");
	    String selectExpression = "select detectorid from `" + DetectorDomain + "`" + " where locationtext LIKE '%Foster NB%'";
        SelectRequest selectRequest = new SelectRequest(selectExpression);
        List<Item> list = sdb.select(selectRequest).getItems();
        
    	
        for (Item item: list) {
        	List<Attribute> attrs = item.getAttributes();
        	temp =0;
        	System.out.println(attrs);
        	for (Attribute attr : attrs) {
        		String selectExpression1 = "select * from `" + loopDomain + "`" + " where starttime LIKE '2011-09-21%' and detectorid  = '" + attrs.get(0).getValue() + "'";
        		SelectRequest selectRequest1 = new SelectRequest(selectExpression1);
                List<Item> list1 = sdb.select(selectRequest1).getItems();
                for (Item item1: list1) {
                   	List<Attribute> attrs1 = item1.getAttributes();
                	for (Attribute attr1 : attrs1) {
                		if (!attrs1.get(4).getValue().isEmpty()){ 
                	    temp = temp + Integer.parseInt(attrs1.get(4).getValue()); 
                 
                	  }
               	   }
               	}
                  System.out.println("volume for this detector " + temp);
        	}
        	      finalvolume = finalvolume + temp;
        }
                
               System.out.println("\n Total volume for Foster NB station " + finalvolume);
               return finalvolume;
        
	}
	public static void main(String[] args){
		TotalVolume tv = new TotalVolume();
	//	tv.SimpleDBSample();
	}

}


