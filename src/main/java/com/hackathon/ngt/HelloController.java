package com.hackathon.ngt;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
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

    @Inject
    public HelloController(Facebook facebook, Twitter twitter, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.twitter = twitter;
		this.connectionRepository = connectionRepository;
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

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        SearchResults results = twitter.searchOperations().search("#cognizant");
        System.out.println(results.toString());
        try {
        	boolean test = WriteFile.writeToFile(results);
        	System.out.println(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "hellotwitter";
    }
}
