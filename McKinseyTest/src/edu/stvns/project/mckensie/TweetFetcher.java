package edu.stvns.project.mckensie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TweetFetcher {
	private final String urlstr = "http://search.twitter.com/search.json?q=";

	public List<Tweet> getTweets(String searchKey, int maxResults) {

		URL url = null;
		String searchUrl = null;
		BufferedReader br = null;
		List<Tweet> tweetsList = new ArrayList<Tweet>();
		try {
			StringBuilder buff = new StringBuilder();

			while (searchKey != null && !searchKey.isEmpty()
					&& tweetsList.size() < maxResults) {
				searchUrl = urlstr + searchKey;

				url = new URL(searchUrl);
				br = new BufferedReader(new InputStreamReader(url
						.openConnection().getInputStream()));
				int c;
				while ((c = br.read()) != -1) {
					buff.append((char) c);
				}
				System.out.println(buff.toString());

				JSONObject js = new JSONObject(buff.toString());
				searchKey = (String) js.get("next_page");
				System.out.println(searchKey);
				JSONArray tweets = js.getJSONArray("results");
				JSONObject tweet;
				for (int i=0; i < tweets.length(); i++) {
					tweet = tweets.getJSONObject(i);
					Tweet t = new Tweet(tweet.getString("from_user"),
							tweet.getString("text"),
							tweet.getString("created_at"));
					if(tweetsList.size() < maxResults)
					{
						tweetsList.add(t);
					}
				}
			}
		} catch (Exception e) {
			tweetsList.add(new Tweet("No Result", "No Result", ""));
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return tweetsList;
	}

}
