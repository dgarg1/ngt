package com.hackathon.ngt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

	private Facebook facebook;
	private Twitter twitter;
	private ConnectionRepository connectionRepository;
	private StreamService streamService;

    @Inject
    public HelloController(Facebook facebook, Twitter twitter, ConnectionRepository connectionRepository, StreamService streamService) {
        this.facebook = facebook;
        this.twitter = twitter;
		this.connectionRepository = connectionRepository;
		this.streamService = streamService;
    }

    @RequestMapping(value="/test", method=RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "hello";
    }
    
    //@RequestMapping(method=RequestMethod.GET)
    public String test() {
    	
    	if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
    		return "test";
        }
		return "test";
    	
    }
    
    @RequestMapping(value="/tweety", method=RequestMethod.GET)
    public String helloTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        List<StreamListener> listeners = new ArrayList<StreamListener>();
        model.addAttribute(twitter.userOperations().getUserProfile());
        try {
        	//for (int i = 0; i < 1000; i++) {
        		//System.out.println("moti" + i);
    			//Model returnedmodel = streamService.streamApi(model, 1);
				
			//}
			Model returnedmodel = streamService.streamApi(model, 1);
			System.out.println(returnedmodel);
			//returnedmodel.asMap().forEach((k,v) -> System.out.println((k + "  " + v)));
			
			try {
				WriteFile.writeToFile(returnedmodel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
//        model.addAttribute("friends", friends);
//        SearchResults results = twitter.searchOperations().search("#cognizant");
//        System.out.println(results.toString());
//        System.out.println("gettttttt the things" + twitter.streamingOperations().filter("#cognizant", listeners));
//        try {
//        	boolean test = WriteFile.writeToFile(results);
//        	System.out.println(test);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        System.out.println("");
        return "hellotwitter";
    }
}
