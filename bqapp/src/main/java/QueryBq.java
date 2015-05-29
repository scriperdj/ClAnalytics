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


public class QueryBq {
	public String getData(){
		final String ACCOUNT_ID = "115999096908-3j05ovljtqh00j0lrphdhuelgp9squtl@developer.gserviceaccount.com";
        final String KEY_FILE   = "WEB-INF/eec53c304895.p12";
        final String PROJECT_ID = "hip-voyager-95814";
		
		File keyFile = new File(KEY_FILE);
		return "Working!!";
	}
}