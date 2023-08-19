package helperMethods;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class JComponentHelpers {

	private Font mainFont = new Font("Arial", Font.BOLD, 12);
    private Font smallFont = new Font("Arial", Font.PLAIN, 10);
    
    private Color green = new Color(0, 168, 120);

	public void labelFunctionality(JLabel label, int xPos, int yPos, int width, int height, String name) {
		label.setFont(mainFont);
		label.setText(name);
		FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
		int textWidth = fontMetrics.stringWidth(label.getText());
		int textHeight = fontMetrics.getHeight();
		label.setBounds(xPos, yPos, textWidth, textHeight);
	}
	
	public void buttonFunctionality(JButton button, int xPos, int yPos, int width, int height, String name, Color foreground, Color background) {
		button.setFocusable(false);
		button.setFont(mainFont);
		button.setText(name);
		button.setBounds(xPos, yPos, width, height);
		button.setForeground(foreground);
		button.setBackground(background);
		button.setBorder(BorderFactory.createBevelBorder(10, background, foreground));
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(background);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(background);
				
			}
		});
	}
	
	public void textAreaFunctionality(JTextArea textArea, Color backgroundColor, Color foregroundColor) {
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(backgroundColor);
		textArea.setForeground(foregroundColor);
		
	}
}
