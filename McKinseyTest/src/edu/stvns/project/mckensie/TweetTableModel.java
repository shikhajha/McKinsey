package edu.stvns.project.mckensie;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TweetTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "#","From", "Created At", "Tweet Text" };
	private List<Tweet> tweets = new ArrayList<Tweet>();

	public int getColumnCount() {
		return columnNames.length;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	public int getRowCount() {
		return tweets.size();
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Tweet t = tweets.get(row);
		Object returnObj = null;
		switch (col) {
		case 0:
			returnObj =  String.valueOf(row+1);
			break;
		case 1:
			returnObj =  t.getFrom();
			break;
		case 2:
			returnObj =  t.getCreatedAt();
			break;
		case 3:
			returnObj =  t.getTweetText();
			break;
		default:
			returnObj =  null;
		}
		return returnObj;
	}

}
