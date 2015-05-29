package com.bqapp;

import java.util.*;
import java.io.*;
import java.lang.*;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.services.bigquery.*;
import com.google.api.services.bigquery.model.*;
import com.google.api.services.bigquery.Bigquery.*;
import com.google.api.services.bigquery.Bigquery.Jobs.Insert;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.*;
import com.google.api.client.json.*;
import com.google.api.client.json.jackson.*;
import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.appengine.http.*;
import com.google.api.client.extensions.appengine.auth.oauth2.*;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;


public class QueryBig {

	private static final String ACCOUNT_ID = "115999096908-3j05ovljtqh00j0lrphdhuelgp9squtl@developer.gserviceaccount.com";
    private static final String KEY_FILE   = "WEB-INF/eec53c304895.p12";
    private static final String PROJECT_ID = "hip-voyager-95814";

	public String getUsers() throws IOException,InterruptedException {
		String querySql = "select Username, count(SessionId) as Login_count from (select Username, SessionId, count(SessionId) as scount from [SiteAnalytics.User_Activity] group by Username, SessionId order by scount DESC) group by Username order by Login_count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String getProducts() throws IOException,InterruptedException {
		String querySql = "select Product_viewed, count(Product_viewed) as View_count from [SiteAnalytics.User_Activity] group by Product_viewed order by View_count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String getCategories() throws IOException,InterruptedException {
		String querySql = "select Category_viewed, count(Category_viewed) as View_count from [SiteAnalytics.User_Activity] group by Category_viewed order by View_count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String getBrowsers() throws IOException,InterruptedException {
		String querySql = "select Browser, count(Browser) as count from [SiteAnalytics.User_Activity] group by Browser order by count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String getLocations() throws IOException,InterruptedException {
		String querySql = "select Location, count(Location) as count from [SiteAnalytics.User_Activity] group by Location order by count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String getDevice() throws IOException,InterruptedException {
		String querySql = "select Device, count(Device) as count from [SiteAnalytics.User_Activity] group by Device order by count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String getScreen() throws IOException,InterruptedException {
		String querySql = "select Screen_size, count(Screen_size) as count from [SiteAnalytics.User_Activity] group by Screen_size order by count DESC LIMIT 5";
				
		String result = executeQuery(querySql);
		return result;
	}
	
	public String executeQuery(String querySql) throws IOException,InterruptedException {
		Bigquery service = getService();
		JobReference jobId = startQuery(service, PROJECT_ID, querySql);
        Job completedJob = checkQueryResults(service, PROJECT_ID, jobId);     
		String result = formatQueryResults(service, PROJECT_ID, completedJob);
		result = StrRem(result);
		return result;
	}
	
	public static JobReference startQuery(Bigquery bigquery, String projectId,
                                        String querySql) throws IOException {
    
    Job job = new Job();
    JobConfiguration config = new JobConfiguration();
    JobConfigurationQuery queryConfig = new JobConfigurationQuery();
    config.setQuery(queryConfig);

    job.setConfiguration(config);
    queryConfig.setQuery(querySql);

    Insert insert = bigquery.jobs().insert(projectId, job);
    insert.setProjectId(projectId);
    JobReference jobId = insert.execute().getJobReference();

    return jobId;
  }
  
  private static Job checkQueryResults(Bigquery bigquery, String projectId, JobReference jobId)
      throws IOException, InterruptedException {
    long startTime = System.currentTimeMillis();
    long elapsedTime;

    while (true) {
      Job pollJob = bigquery.jobs().get(projectId, jobId.getJobId()).execute();
      elapsedTime = System.currentTimeMillis() - startTime;
      if (pollJob.getStatus().getState().equals("DONE")) {
        return pollJob;
      }
      // Pause execution for one second before polling job status again, to
      // reduce unnecessary calls to the BigQUery API and lower overall
      // application bandwidth.
      Thread.sleep(1000);
    }
  }
  
   private static String formatQueryResults(Bigquery bigquery,
                                          String projectId, Job completedJob) throws IOException {
    GetQueryResultsResponse queryResult = bigquery.jobs()
        .getQueryResults(
            projectId, completedJob
            .getJobReference()
            .getJobId()
        ).execute();
    List<TableRow> rows = queryResult.getRows();
	String result="";
	
    
    for (TableRow row : rows) {
		result+="{ y: '";
		int ctr=0;
      for (TableCell field : row.getF()) {
	  
	  result += field.getV();
	  if (ctr< row.size()){
		result +="', a: ";
	  }
	  ctr++;
      
       }
	   result +="},";
      
    }
	return result;
  }
  
  public static Bigquery getService(){
		Bigquery service = null;
		try {
			  HttpTransport httpTransport = new NetHttpTransport();
              JsonFactory jsonFactory = new JacksonFactory();
              File keyFile = new File(KEY_FILE);

            GoogleCredential.Builder credBuilder = new GoogleCredential.Builder();
            credBuilder.setJsonFactory(jsonFactory);
            credBuilder.setTransport(httpTransport);
            credBuilder.setServiceAccountId(ACCOUNT_ID);
            credBuilder.setServiceAccountPrivateKeyFromP12File(keyFile);
            credBuilder.setServiceAccountScopes(Collections.singleton(BigqueryScopes.BIGQUERY));

            GoogleCredential credentials = credBuilder.build();

            Bigquery.Builder serviceBuilder =    new Bigquery.Builder(httpTransport,jsonFactory, credentials);

            service = serviceBuilder.build();
		}
		catch (Exception ex) {
            System.out.println("Caught exception: " + ex + "\n");
            ex.printStackTrace();
        }
		return service;
	}
  
  public static String StrRem(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length()-1)==',') {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	/*
    public static void main(String[] args) throws IOException,InterruptedException  {
		
		QueryBig query = new QueryBig();
		
        String usersData = query.getUsers();
		String productsData = query.getProducts();
		String categoriesData = query.getCategories();
		String browserData = query.getBrowsers();
		String locationData = query.getLocations();
		String deviceData = query.getDevice();
		String screenData = query.getScreen(); 
		System.out.println(usersData);
		System.out.println(productsData);
		System.out.println(categoriesData);
		System.out.println(browserData);
		System.out.println(locationData);
		System.out.println(deviceData);
		System.out.println(screenData);
        
    } */
	

}