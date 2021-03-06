package mileStone3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


/**
 * This is the GUI for the recommend system,
 * since we haven's decided every details for our final project
 * and there are some similarities between last homework and the project
 * We create this interface first, and modify it according to the 
 * final project.
 * @author Shan Luo
 *
 */
public class UserInterface {


	private JFrame frame;
	private ActionListener listener;
	private JPanel radioButton;
	private JPanel choicePanel;
	private Box inputPanel;
	private JRadioButton book;
	private JRadioButton movie;
	private JLabel userId;
	private JLabel itemId;
	private JLabel itemNumber;
	private JTextField userIdField;
	private JTextField itemIdField;
	private JTextField itemNumberField;
	private JRadioButton prediction;
	private JRadioButton recommendate;
	private JTextArea result = new JTextArea();
	private JPanel panel1;
	private JPanel buttonPanel;
	private JButton clear;
	private JButton load;
	private JButton start;
	private Image background;


	public UserInterface () throws IOException{
		frame = new JFrame("Recommend Systm");
		createComponents();
		doLayout();
		frame.setVisible(true);
	}

	public void doLayout() throws IOException{
		frame.pack();
		frame.setSize(800, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setLayout(new BorderLayout());
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(inputPanel, BorderLayout.WEST);
		frame.add(result, BorderLayout.EAST);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		addMapPanel();
		this.updateTextArea();

	}

	private void createComponents(){
		radioButton = createRadioButtons();
		choicePanel = createChoicePanel();
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(radioButton);
		panel1.add(choicePanel);
		inputPanel = createInputPanel();
		buttonPanel = createButtonPanel();

	}

	private JPanel createRadioButtons(){
		book = new JRadioButton("Book");
		book.addActionListener(listener);
		book.setSelected(true);

		movie = new JRadioButton("Movie");
		movie.addActionListener(listener);

		ButtonGroup group = new ButtonGroup();
		group.add(book);
		group.add(movie);

		JPanel panel = new JPanel();
		panel.add(book);
		panel.add(movie);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Item"));

		return panel;
	}

	private Box createInputPanel(){
		Box panel = new Box(BoxLayout.Y_AXIS);
		userId = new JLabel("User Id:");
		itemId = new JLabel("Item Id:");
		itemNumber = new JLabel("Item Number:");
		userIdField = new JTextField();
		userIdField.setPreferredSize(new Dimension(10,100));
		itemIdField = new JTextField();
		itemIdField.setPreferredSize(new Dimension(10,100));
		itemNumberField = new JTextField();
		itemNumberField.setPreferredSize(new Dimension(10,100));
		panel.add(userId);
		panel.add(userIdField);
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(itemId);
		panel.add(itemIdField);
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(new JLabel(" "));
		panel.add(itemNumber);
		panel.add(itemNumberField);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Input"));



		return panel;
	}

	private JPanel createChoicePanel(){
		prediction = new JRadioButton("Predict");
		prediction.addActionListener(listener);
		prediction.setSelected(true);

		recommendate = new JRadioButton("Recommendate");
		recommendate.addActionListener(listener);

		ButtonGroup group = new ButtonGroup();
		group.add(prediction);
		group.add(recommendate);

		JPanel panel = new JPanel();
		panel.add(prediction);
		panel.add(recommendate);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Choice"));

		return panel;
	}

	private JPanel createButtonPanel(){
		clear = new JButton("Clear");
		clear.addActionListener(listener);

		load = new JButton("Load");
		load.addActionListener(listener);

		start = new JButton("Start");
		start.addActionListener(listener);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(clear);
		panel.add(load);
		panel.add(start);

		return panel;
	}
	
	public void addMapPanel() throws IOException {
		try {
			String latitude = "10";
			String longitude = "15";
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center="
					+ latitude
					+ ","
					+ longitude
					+ "&zoom=11&size=612x612&scale=2&maptype=roadmap";
			String destinationFile = "image.jpg";
			// read the map image from Google
			// then save it to a local file: image.jpg
			//
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		// create a GUI component that loads the image: image.jpg
		//
		ImageIcon imageIcon = new ImageIcon((new ImageIcon("image.jpg"))
				.getImage().getScaledInstance(630, 600,
						java.awt.Image.SCALE_SMOOTH));
		frame.add(new JLabel(imageIcon), BorderLayout.CENTER);
		// show the GUI window
	}
	
	private void updateTextArea(){
		result.append("Welcome to the recommend system!");
		result.setBorder(new TitledBorder(new EtchedBorder(), "Result"));
	}


	public static void main(String[] args) throws IOException{
		UserInterface u = new UserInterface();
	}

}
