package com.bqapp;

import java.util.*;
import java.io.*;
import java.lang.*;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.services.bigquery.*;
import com.google.api.services.bigquery.model.*;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.*;
import com.google.api.client.json.*;
import com.google.api.client.json.jackson.*;
import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.appengine.http.*;
import com.google.api.client.extensions.appengine.auth.oauth2.*;

public class BigQueryLoad {

    public static TableSchema generateSchemas() {
        List<TableFieldSchema> schemaList = new ArrayList<TableFieldSchema>();
        schemaList.add(new TableFieldSchema().setName("Username")
                                             .setType("STRING"));
        schemaList.add(new TableFieldSchema().setName("Browser")
                                             .setType("STRING"));
        schemaList.add(new TableFieldSchema().setName("Device")
                                             .setType("STRING"));
		schemaList.add(new TableFieldSchema().setName("Screen_size")
                                             .setType("STRING"));
		schemaList.add(new TableFieldSchema().setName("Location")
                                             .setType("STRING"));
		schemaList.add(new TableFieldSchema().setName("Category_viewed")
                                             .setType("STRING"));		 
		schemaList.add(new TableFieldSchema().setName("Product_viewed")
                                             .setType("STRING"));
		schemaList.add(new TableFieldSchema().setName("SessionId")
                                             .setType("STRING"));
		schemaList.add(new TableFieldSchema().setName("Timestamp")
                                             .setType("STRING"));									 
											 
											 

        TableSchema schema = new TableSchema();
        schema.setFields(schemaList);

        return schema;
    }
	
	public static String generateData(String csv) {
		String data="";
		String line="";
		try{
			BufferedReader br = new BufferedReader(new FileReader(csv));
			while ((line = br.readLine()) != null) {
				data += line + "\n";
			}
		} 
		catch (Exception e){
		}
				
		return data;
    }

    public static void main(String[] args) {
		final String csvFile = "G:/JAVA/GoogleCloud/CSV/Mock-load-data.csv";
        final String ACCOUNT_ID = "946148164742-0b1nb855vk77m5abldpsn2hskddejcor@developer.gserviceaccount.com";
        final String KEY_FILE   = "C:/Users/scriperdj/Downloads/test-db-fe21f4a5888d.p12";
        final String PROJECT_ID = "hip-voyager-95814";
        final String DATASET_ID = "SiteAnalytics";
        final String TABLE_ID   = "User_Activity";

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

            Bigquery.Builder serviceBuilder = 
                new Bigquery.Builder(httpTransport,
                                     jsonFactory,
                                     credentials);


            Bigquery service = serviceBuilder.build();

            if (service == null || service.jobs() == null) {
                throw new Exception("Service is null");
            }

            DatasetReference datasetRef = new DatasetReference();
            datasetRef.setProjectId(PROJECT_ID);
            datasetRef.setDatasetId(DATASET_ID);

            Dataset outputDataset = new Dataset();
            outputDataset.setDatasetReference(datasetRef);

            Dataset dataset = service.datasets().insert(PROJECT_ID,
                                                        outputDataset).execute();

            TableReference destinationTable = new TableReference();
            destinationTable.setProjectId(PROJECT_ID);
            destinationTable.setDatasetId(DATASET_ID);
            destinationTable.setTableId(TABLE_ID);

            JobConfigurationLoad jobLoad = new JobConfigurationLoad();
            jobLoad.setSchema(generateSchemas());
            jobLoad.setSourceFormat("CSV");
            jobLoad.setDestinationTable(destinationTable);
            jobLoad.setCreateDisposition("CREATE_IF_NEEDED");

            JobConfiguration jobConfig = new JobConfiguration();
            jobConfig.setLoad(jobLoad);

            JobReference jobRef = new JobReference();
            jobRef.setProjectId(PROJECT_ID);

            Job outputJob = new Job();
            outputJob.setConfiguration(jobConfig);
            outputJob.setJobReference(jobRef);

            String data = generateData(csvFile);
			
            ByteArrayContent contents = 
                new ByteArrayContent("application/octet-stream",
                                     data.getBytes());

            Job job = service.jobs().insert(PROJECT_ID, 
                                            outputJob,
                                            contents).execute();

            if (job == null) {
                throw new Exception("Job is null");
            }

            while (true) {
                String status = job.getStatus().getState();

                if (status != null || ("DONE").equalsIgnoreCase(status)) {
                    break;
                }

                Thread.sleep(1000);
            }

            ErrorProto errorResult = job.getStatus().getErrorResult();

            if (errorResult != null) {
                throw new Exception("Error running job: " + errorResult);
            }                   
        }
        catch (Exception ex) {
            System.out.println("Caught exception: " + ex + "\n");
            ex.printStackTrace();

            System.exit(1);
        }

        System.exit(0);
    }

}