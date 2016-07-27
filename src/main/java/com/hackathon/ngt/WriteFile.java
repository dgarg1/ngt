package com.hackathon.ngt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;



//import org.joda.time.DateTime;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.ui.Model;

public class WriteFile {
    static BufferedWriter bw = null;

	public static boolean writeToFile(Model results) throws IOException {
		FileWriter fw ;
		//DateTime date = new DateTime();
		//String fileName="E:/spring/filename1.txt";
		//String fileName="E:/spring/filename"+ new SimpleDateFormat("MM_dd_yyyy_hh:mm:ss").format(new Date());
		String fileName = "/users/money/testing/filename1.txt";
		try {

			String content = "This is the content to write into file";

			File file = new File(fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			//List<Tweet> tweets = results.getTweets();
			//System.out.println("size is >>>>>>"+ tweets.size());
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			results.asMap().forEach((k,v) -> {try {bw.write(k +">>>>" + v);} catch(Exception e){
					e.printStackTrace();	
				}});
//			 bw.write(pro.getFromUser() + ">>>>" +pro.getText());
			   bw.newLine();
//			for(Tweet pro : tweets) {
//				results.asMap().forEach((k,v) -> bw.write(k +">>>>" + v));
//			  // bw.write(pro.getFromUser() + ">>>>" +pro.getText());
//			   bw.newLine();
//			}

			System.out.println("Done");
			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		 finally{
			 bw.close();
		 }
	}
	
}
