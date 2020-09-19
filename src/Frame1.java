import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;


import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Frame1 {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String text_to_translate;
	private String [] token_array;
	private int current_token = -1;
	private JFrame frame;
	private JTextField textField;
	private String [] isl_gif = {"address",
	                             "ahemdabad",
	                             "any questions",
	                             "are you angry",
	                             "are you hungry",
	                             "assam",
	                             "august",
	                             "banana",
	                             "banaras",
	                             "banglore",
	                             "be careful",
	                             "bridge",
	                             "cat",
	                             "christmas",
	                             "church",
	                             "cilinic",
	                             "dasara",
	                             "december",
	                             "did you finish homework",
	                             "do you have money",
	                             "do you want something to drink",
	                             "do you watch TV",
	                             "dont worry",
	                             "flower is beautiful",
	                             "good afternoon",
	                             "good morning",
	                             "good question",
	                             "grapes",
	                             "hello",
	                             "hindu",
	                             "hyderabad",
	                             "i am a clerk",
	                             "i am fine",
	                             "i am sorry",
	                             "i am thinking",
	                             "i am tired",
	                             "i go to a theatre",
	                             "i had to say something but I forgot",
	                             "i like pink colour",
	                             "i love to shop",
	                             "job",
	                             "july",
	                             "june",
	                             "karnataka",
	                             "kerala",
	                             "krishna",
	                             "lets go for lunch",
	                             "mango",
	                             "may",
	                             "mile",
	                             "mumbai",
	                             "nagpur",
	                             "nice to meet you",
	                             "open the door",
	                             "pakistan",
	                             "please call me later",
	                             "police station",
	                             "post office",
	                             "pune",
	                             "punjab",
	                             "saturday",
	                             "shall I help you",
	                             "shall we go together tommorow",
	                             "shop",
	                             "sign language interpreter",
	                             "sit down",
	                             "stand up",
	                             "take care",
	                             "temple",
	                             "there was traffic jam",
	                             "thursday",
	                             "toilet",
	                             "tomato",
	                             "tuesday",
	                             "usa",
	                             "village",
	                             "wednesday",
	                             "what is the problem",
	                             "what is today's date",
	                             "what is your father do",
	                             "what is your name",
	                             "whats up",
	                             "where is the bathroom",
	                             "where is the police station",
	                             "you are wrong"};
	private ArrayList<String> token_list;
	private int string_start = 0;
    
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Frame1() {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 795,571);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Text to Sign Language");
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 10, 761, 223);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel = new JLabel("ENTER TEXT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(293, 33, 132, 40);
		layeredPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField.setBounds(245, 84, 236, 44);
		textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		layeredPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Translate");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton_1.setBounds(282, 139, 154, 40);
		layeredPane.add(btnNewButton_1);
		
		JLabel lblError = new JLabel("Input should only contain alphabets");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblError.setBounds(491, 91, 270, 40);
		lblError.setVisible(false);
		layeredPane.add(lblError);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(10, 259, 761, 259);
		frame.getContentPane().add(layeredPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 497, 259);
		layeredPane_1.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton.setBounds(537, 87, 87, 35);
		btnNewButton.setEnabled(false);
		layeredPane_1.add(btnNewButton);
		
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNewLabel_1.setIcon(null);
				text_to_translate = textField.getText();
				
				if(isStringOnlyAlphabet(text_to_translate.replaceAll("\\s", ""))) {
					lblError.setVisible(false);
					text_to_translate = text_to_translate.trim();
					update_token_array(text_to_translate);
					if(token_array.length != 0) {
						btnNewButton.setEnabled(true);
						current_token = 0;
						Image images = null;
						// System.out.println(token_array[current_token]);
						if(token_array[current_token].contains(".jpg"))
							images=new ImageIcon(this.getClass().getResource(token_array[current_token])).getImage().getScaledInstance(271,259, Image.SCALE_SMOOTH);
						else
							images=new ImageIcon(this.getClass().getResource(token_array[current_token])).getImage();
						lblNewLabel_1.setIcon(new ImageIcon(images));
						current_token++;
					}
				}
				else {
					lblError.setVisible(true);
				}
			}
		});
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Image images = null;
				if(current_token < token_array.length)
				{
					// System.out.println(token_array[current_token]);
					if(token_array[current_token].contains(".jpg"))
						images=new ImageIcon(this.getClass().getResource(token_array[current_token])).getImage().getScaledInstance(271,259, Image.SCALE_SMOOTH);
					else
						images=new ImageIcon(this.getClass().getResource(token_array[current_token])).getImage();
					lblNewLabel_1.setIcon(new ImageIcon(images));
					current_token++;
				}
				else {
					btnNewButton.setEnabled(false);
				}
			}
		});

	
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(554, 150, 1, 1);
		layeredPane_1.add(horizontalBox);
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(10, 243, 761, 10);
		frame.getContentPane().add(separator);
	}
	public String [] get_Isl_gif() {
		return isl_gif;
	}
	public static boolean isStringOnlyAlphabet(String str) 
    { 
		str.replaceAll("\\s", "");
        return ((str != null) 
                && (!str.equals("")) 
                && (str.matches("^[a-zA-Z]*$"))); 
    } 
	private boolean update_token_array(String s){
		s = s.trim();
		string_start = 0;
		update_token_list(s);
		token_array = new String[token_list.size()];
		token_array = token_list.toArray(token_array);
		return true;
	}

	
	public void get_tokens_for_unknown_string(String s) {
		// System.out.println(s);
		if(s.length() == 0)
			return;
		s = s.replaceAll("\\s", "");
		char [] c = s.toCharArray();
		for(int i = 0; i < s.length(); i++)
			token_list.add((c[i] + ".jpg").toLowerCase());
	}
	
	public static Comparator<Integer []> positions = new Comparator<Integer[]>() {

		public int compare(Integer [] s1, Integer [] s2) {

		   int pos1 = s1[0];
		   int pos2 = s2[1];

		   /*For ascending order*/
		   return pos1-pos2;

		   /*For descending order*/
		   //rollno2-rollno1;
	   }};
	public void update_token_list(String query) {
		int i= string_start;
		token_list = new ArrayList<String>();
		ArrayList<Integer []> pairs = new ArrayList<Integer []>();
		for(String a : this.get_Isl_gif()) {
			int res = isSubstring(a, query);
			if(res != -1) {
				Integer temp [] = {res,res+a.length()};
				pairs.add(temp);
			}
		}
		if(pairs.size() != 0)
		{
			Collections.sort(pairs,positions);
						
			for(Integer [] a : pairs) {
				// System.out.println(a[0]+" "+a[1]+" "+i);
				
				get_tokens_for_unknown_string(query.substring(i,a[0]));
				token_list.add(query.substring(a[0].intValue(),a[1].intValue())+".gif");
				i = a[1].intValue();
			}
			get_tokens_for_unknown_string(query.substring(i,query.length()));
		}
		else {
			get_tokens_for_unknown_string(query.substring(i,query.length()));
		}
	}
	
	private int isSubstring(String s1, String s2) 
    { 
        int M = s1.length(); 
        int N = s2.length(); 
      
        /* A loop to slide pat[] one by one */
        for (int i = 0; i <= N - M; i++) { 
            int j; 
      
            /* For current index i, check for 
            pattern match */
            for (j = 0; j < M; j++) 
                if (s2.charAt(i + j) != s1.charAt(j)) 
                    break; 
      
            if (j == M) 
                return i; 
        } 
      
        return -1; 
    } 
}