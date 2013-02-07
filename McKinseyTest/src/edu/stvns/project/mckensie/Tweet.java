package edu.stvns.project.mckensie;

public class Tweet {
	
	private final String from;
	private final String tweetText;
	private final String createdAt;
	
	public Tweet(String from, String tweetText, String createdAt) {
		super();
		this.from = from;
		this.tweetText = tweetText;
		this.createdAt = createdAt;
	}

	public String getFrom() {
		return from;
	}

	public String getTweetText() {
		return tweetText;
	}

	public String getCreatedAt() {
		return createdAt;
	}
	
	

}
