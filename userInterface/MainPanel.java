package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import helperMethods.JComponentHelpers;
import huffmanCoding.EncodeMessage;

// random text button

public class MainPanel extends JPanel implements ActionListener {
	
	private MainManager manager;
	private JComponentHelpers componentHelpers;
	private EncodeMessage encodeMessage;
	
	private JTextArea messageTextArea;
	private JTextArea displayTextArea;
	
	private JLabel enterTextLabel;
	
	private JButton getCodesButton;
	private JButton randomTextButton;
	private JButton clearButton;
	
	private Color white = new Color(255, 255, 255);
    private Color green = new Color(0, 168, 120);
    
    private String message; 
    
	private final static float SCALAR = 1.0f;
    private final static int WIDTH = (int) (600 * SCALAR);
    private final static int HEIGHT = (int) (480 * SCALAR);

	public MainPanel(MainManager manager) {
		this.manager = manager;
		setLayout(null);
		
		componentHelpers = new JComponentHelpers();
		
		setSize();
		createComponents();
	}
	
	private void createComponents() {
		createTextAreas();
		createLabels();
		createButtons();
	}
	
	private void createLabels() {
		enterTextLabel = new JLabel();
		componentHelpers.labelFunctionality(enterTextLabel, 10, 10, 30, 20, "Enter Text:");
		this.add(enterTextLabel);
	}
	
	private void createTextAreas() {
		messageTextArea = new JTextArea();
		componentHelpers.textAreaFunctionality(messageTextArea, Color.LIGHT_GRAY, Color.black);
		JScrollPane scrollPane = new JScrollPane(messageTextArea);
		scrollPane.setBounds(10, 40, 220, 100);
		this.add(scrollPane);
		
		displayTextArea = new JTextArea();
		componentHelpers.textAreaFunctionality(displayTextArea, Color.black, Color.white);
		displayTextArea.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(displayTextArea);
		scrollPane2.setBounds(240, 10, 330, 260);
		this.add(scrollPane2);
	}
	
	private void createButtons() {		
		getCodesButton = new JButton();
		componentHelpers.buttonFunctionality(getCodesButton, 10, 154, 80, 30, "Display", white, green);
		getCodesButton.addActionListener(this);
		this.add(getCodesButton);
		
		randomTextButton = new JButton();
		componentHelpers.buttonFunctionality(randomTextButton, 100, 154, 100, 30, "Random Text", white, green);
		randomTextButton.addActionListener(this);
		//this.add(randomTextButton);
		
		clearButton = new JButton();
		componentHelpers.buttonFunctionality(clearButton, 528, 280, 40, 20, "Clear", white, green);
		clearButton.addActionListener(this);
		this.add(clearButton);
	}
	
	private void setSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getCodesButton) {
			displayTextArea.setText("");
			displayInformation();
			
	    } else if (e.getSource() == randomTextButton) {
	        String randomText = generateRandomText();
	        
	        displayTextArea.setText(randomText);
	    } else if(e.getSource() == clearButton) {
	        displayTextArea.setText("");
	    }
	}
	
	
	
	private void displayInformation() {
		String message = messageTextArea.getText();
        encodeMessage = new EncodeMessage(message);
        HashMap<Character, String> huffmanCodes = encodeMessage.getHuffmanHashMap();
        HashMap<Character, Integer> frequency = encodeMessage.getFrequencyHashMap();
        int maxCharacterLength = 0;

        displayTextArea.append("Original Message: " + message + "\n\n");
        displayTextArea.append("Character:     Frequency:    Code:\n");
        
        for(Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            char character = entry.getKey();
            int charFrequency = frequency.get(character);
            String huffmanCode = entry.getValue();

            String formattedLine = String.format("%-14s %-14d %-14s\n", character, charFrequency, huffmanCode);
            //displayTextArea.append(formattedLine);
            displayTextArea.append("                " + character + "                " + charFrequency + "                "
             + huffmanCode + "\n");
        }
        
        displayTextArea.append("\n\n");
        
        String encodedMessage = encodeMessage.getEncodedMessage();
        
        displayTextArea.append("Encoded Message: " + encodedMessage);
        
        String efficiencyInfo = encodeMessage.getEfficiencyInfo();
        
        displayTextArea.append(efficiencyInfo);

	}
	
	
	private String generateRandomText() {
	    
	    return "Random text: " + Math.random();
	}

}

