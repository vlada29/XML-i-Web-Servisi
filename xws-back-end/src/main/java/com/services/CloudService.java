package com.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.model.Komentar;
import com.model.Ocena;

@SuppressWarnings("deprecation")
public class CloudService {
	private String ratingsUrl = "https://rating-system-ca802.firebaseio.com/ratings.json?orderBy=%22smestajnaJedinica%22&equalTo=";
	private String commentsUrl = "https://rating-system-ca802.firebaseio.com/comments.json?orderBy=%22smestajnaJedinica%22&equalTo=";
	
	public ArrayList<Ocena> getOceneArrayForSmestaj(Long id){
		ArrayList<Ocena> ocene = new ArrayList<Ocena>();
		Gson g = new Gson();
		try {	
			String data = getDataForOcene(id);
			if(data == null) {
				throw new Exception();
			}
			Type type = new TypeToken<Map<String, Ocena>>(){}.getType();
			Map<String, Ocena> body = g.fromJson(data, type);
			
			for (Map.Entry<String, Ocena> entry : body.entrySet())
			{
			    ocene.add(entry.getValue());
			}
			
		}catch (Exception e) {
			return null;
		}

		return ocene;
	}
	
	public ArrayList<Komentar> getKomentariArrayForSmestaj(Long id){
		ArrayList<Komentar> komentari = new ArrayList<Komentar>();
		Gson g = new Gson();
		try {	
			
			String data = getDataForKomentari(id);
			if(data == null) {
				throw new Exception();
			}
			Type type = new TypeToken<Map<String, Komentar>>(){}.getType();
			Map<String, Komentar> body = g.fromJson(data, type);
			
			for (Map.Entry<String, Komentar> entry : body.entrySet())
			{
				if(entry.getValue().isOdobren()) {
					System.out.println(entry.getValue());
					//komentari.add(entry.getValue());
					Komentar k = new Komentar();
					k.setHjid(entry.getValue().getHjid());
					k.setOdobren(entry.getValue().isOdobren());
					k.setSadrzaj(entry.getValue().getSadrzaj());
					k.setSmestajnaJedinica(entry.getValue().getSmestajnaJedinica());
					k.setUser(entry.getValue().getUser());
					komentari.add(k);
				}
			}
			
		}catch (Exception e) {
			return null;
		}
		return komentari;
	}
	
	public String getOceneJsonForSmestaj(Long id) {
		String ret = "[";
		Gson g = new Gson();
		try {	
			String data = getDataForOcene(id);
			if(data == null) {
				throw new Exception();
			}
			Type type = new TypeToken<Map<String, Ocena>>(){}.getType();
			Map<String, Ocena> body = g.fromJson(data, type);
			
			boolean zarez = false;
			
			for (Map.Entry<String, Ocena> entry : body.entrySet())
			{
				if(zarez) {
					ret+=",";
				}
			    ret+=g.toJson(entry.getValue());
			    zarez=true;
			}
			
		}catch (Exception e) {
			return null;
		}
		ret+="]";
		return ret;
	}
	
	public String getKomentariJsonForSmestaj(Long id) {
		String ret = "[";
		Gson g = new Gson();
		try {	
			String data = getDataForKomentari(id);
			if(data == null) {
				throw new Exception();
			}
			Type type = new TypeToken<Map<String, Komentar>>(){}.getType();
			Map<String, Komentar> body = g.fromJson(data, type);
			
			boolean zarez = false;
			
			for (Map.Entry<String, Komentar> entry : body.entrySet())
			{
				if(entry.getValue().isOdobren()) {
					if(zarez) {
						ret+=",";
					}
					ret+=g.toJson(entry.getValue());
					zarez=true;
				}
			}
			
		}catch (Exception e) {
			return null;
		}
		ret+="]";
		return ret;
	}
	
	public String getKomentariForAdmin() {
		String ret = "[";
		Gson g = new Gson();
		try {	
			String data = getDataForKomentariAdmin();
			System.out.println(data);
			if(data == null) {
				System.out.println("data je null");
				throw new Exception();
			}
			Type type = new TypeToken<Map<String, Komentar>>(){}.getType();
			Map<String, Komentar> body = g.fromJson(data, type);
			
			boolean zarez = false;
			
			for (Map.Entry<String, Komentar> entry : body.entrySet())
			{
				if(!entry.getValue().isOdobren()) {
					if(zarez) {
						ret+=",";
					}
					CommentWrapper cw = new CommentWrapper(entry.getValue(),entry.getKey());
					ret+=g.toJson(cw);
					zarez=true;
				}
			}
			
		}catch (Exception e) {
			return null;
		}
		ret+="]";
		return ret;
	}
	
	private String getDataForOcene(Long id) {
		String ret = "";
		
		try {
			String url = ratingsUrl+id;
			HttpGet request = new HttpGet( url );
			@SuppressWarnings({"resource" })
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute( request );
			org.apache.http.HttpEntity entity = response.getEntity();
			
			Writer writer = new StringWriter();
			InputStream is = entity.getContent();
			char[] buffer = new char[1024];
			Reader reader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );
			int n;
			while( (n=reader.read(buffer)) != -1 ) {
				writer.write( buffer, 0, n );
			}
			
			ret = writer.toString();
		}catch(Exception e) {
			return null;
		}
		return ret;
	}
	
	private String getDataForKomentari(Long id) {
		String ret = "";
		
		try {
			String url = commentsUrl+id;
			HttpGet request = new HttpGet( url );
			@SuppressWarnings({"resource" })
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute( request );
			org.apache.http.HttpEntity entity = response.getEntity();
			
			Writer writer = new StringWriter();
			InputStream is = entity.getContent();
			char[] buffer = new char[1024];
			Reader reader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );
			int n;
			while( (n=reader.read(buffer)) != -1 ) {
				writer.write( buffer, 0, n );
			}
			
			ret = writer.toString();
		}catch(Exception e) {
			return null;
		}
		return ret;
	}
	
	private String getDataForKomentariAdmin() {
		String ret = "";
		
		try {
			String url = "https://rating-system-ca802.firebaseio.com/comments.json";
			HttpGet request = new HttpGet( url );
			@SuppressWarnings({"resource" })
			HttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute( request );
			org.apache.http.HttpEntity entity = response.getEntity();
			
			Writer writer = new StringWriter();
			InputStream is = entity.getContent();
			char[] buffer = new char[1024];
			Reader reader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );
			int n;
			while( (n=reader.read(buffer)) != -1 ) {
				writer.write( buffer, 0, n );
			}
			
			ret = writer.toString();
		}catch(Exception e) {
			return null;
		}
		return ret;
	}
	
	public class CommentWrapper{
		private Komentar comment;
		private String cloudStorageKey;
		
		public CommentWrapper(Komentar comment, String cloudStorageKey) {
			super();
			this.comment = comment;
			this.cloudStorageKey = cloudStorageKey;
		}

		@Override
		public String toString() {
			return "CommentWrapper [comment=" + comment + ", cloudStorageKey=" + cloudStorageKey + "]";
		}
		
		
		
	}
	
}
