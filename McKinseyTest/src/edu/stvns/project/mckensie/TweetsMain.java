package edu.stvns.project.mckensie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumn;

public class TweetsMain extends JPanel {
	/**
	 * 
	 */
	
	public static final int MAX_RESULTS = 50*2;
	private static final long serialVersionUID = 1L;
	private TweetFetcher tweetFethcer = null;
	private TweetTableModel model;
	private JTable table = null;

	public TweetsMain() {
		super(new BorderLayout(2,2));
		
		JPanel labels = new JPanel(new BorderLayout(2,2));
		JLabel label = new JLabel("Search");
		labels.add(label, BorderLayout.WEST);
		

		final JTextField searchField = new JTextField();
		add(searchField, BorderLayout.EAST);

		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					final String searchKey = searchField.getText();
					SwingUtilities.invokeLater(new Runnable(){

						@Override
						public void run() {
							model.setTweets(tweetFethcer.getTweets(searchKey , MAX_RESULTS));
							table.repaint();
							TweetsMain.this.repaint();
							
						}
						
					});
				}
			}
		});
		
		labels.add(searchField);
		
		add(labels,BorderLayout.NORTH);

		tweetFethcer = new TweetFetcher();
		// Create and set up the window.
		JFrame frame = new JFrame("Get Tweets");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setOpaque(true); // content panes must be opaque
		frame.setContentPane(this);

		model = new TweetTableModel();
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(800, 600));
		table.setFillsViewportHeight(true);
		
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i <= 2) {
		        column.setMaxWidth(150);
		        column.setPreferredWidth(80);
		    }
		}

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane, BorderLayout.SOUTH);

		// Display the window.
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		new TweetsMain();
	}

}
