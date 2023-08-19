package huffmanCoding;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class EncodeMessage {
	
	private String message;
	private StringBuilder encodedText;
	
	private HashMap<Character, Integer> frequencyMap;
	private HashMap<Character, String> huffmanCodes;
	private PriorityQueue<Node> priorityQueue;
	  
	private Node rootNode;
	
	public EncodeMessage(String message) {
		this.message = message;
		
		frequencyMap = new HashMap<>();
		huffmanCodes = new HashMap<>();
		
		message = removeSpecialCharacters(message);
		frequencyMap = getFrequencies(message);
		
		//displayFrequencies(frequencyMap);
		
		createPriorityQueue();
		
		String encodedText = buildEncodedMessage(message);
		System.out.println("\n\nEncoded Message: \n" + encodedText);
		
		printInfo(encodedText, message);
	}
	
	private HashMap<Character, Integer> getFrequencies(String message) {
		for(char currentCharacter : message.toCharArray()) {
			currentCharacter = Character.toLowerCase(currentCharacter);
			frequencyMap.put(currentCharacter, frequencyMap.getOrDefault(currentCharacter, 0) + 1);
		}
		return frequencyMap;
	}
	
	private void createPriorityQueue() {
		priorityQueue = new PriorityQueue<>();
		
		for(Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
			priorityQueue.offer(new Node(entry.getKey(), entry.getValue()));
		}
		
		rootNode = createHuffmanTree(priorityQueue);
		huffmanCodes = storeHuffmanCodes(rootNode);
		displayHuffmanCodes(huffmanCodes);
	}
	
	private Node createHuffmanTree(PriorityQueue<Node> priorityQueue) {
		while(priorityQueue.size() > 1) {
			Node leftNode = priorityQueue.poll();
			Node rightNode = priorityQueue.poll();
			Node parentNode = new Node('\0', leftNode.frequency + rightNode.frequency);
			
			parentNode.leftNode = leftNode;
			parentNode.rightNode = rightNode;
			priorityQueue.offer(parentNode);
		}
		
		return priorityQueue.poll();
	}
	
	private HashMap<Character, String> storeHuffmanCodes(Node rootNode) {
		generateHuffmanCode(rootNode, "", huffmanCodes);
		return huffmanCodes;
	}
	
	private void generateHuffmanCode(Node node, String currentCode, HashMap<Character, String> huffmanCodes) {
		if(node == null) 
			return;
		
		if(node.character != '\0')
            huffmanCodes.put(node.character, currentCode);
		
		generateHuffmanCode(node.leftNode, currentCode + "0", huffmanCodes);
		generateHuffmanCode(node.rightNode, currentCode + "1", huffmanCodes);
	}
	
	private void displayHuffmanCodes(HashMap<Character, String> huffmanCodes2) {
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println("Character: " + entry.getKey() + ", Huffman Code: " + entry.getValue());
        }
	}
	
	public HashMap<Character, String> getHuffmanHashMap() {
		 return huffmanCodes;
	}
	
	public HashMap<Character, Integer> getFrequencyHashMap() {
		return frequencyMap;
	}
	
	private String buildEncodedMessage(String text) {
	    encodedText = new StringBuilder();
	    for (char character : text.toCharArray()) {
	        String code = huffmanCodes.get(character);
	        if (code != null) {
	            encodedText.append(code + " ");

	        }
	    }
	    return encodedText.toString();
	}
	
	private void printInfo(String encodedText, String oringinalMessage) {
		double totalBinaryDigits = 0;
		for(char character : encodedText.toCharArray()) {
			if(character != ' ') {
				totalBinaryDigits++;
			}
		}
		double efficiency = (double)((totalBinaryDigits/(oringinalMessage.length() * 8)) * 100);
		System.out.println("\nBinary digits used: " + totalBinaryDigits);
		System.out.println("\nBinary digits using ASCII tabls: " + oringinalMessage.length() * 8);
		System.out.println("\nTherefore, using Huffman coding the message is: " + efficiency + "% more efficient");
	}

	private String removeSpecialCharacters(String message) {
		return message.replaceAll("[^a-zA-Z]", "").toLowerCase();

	}
	
	public String getEncodedMessage() {
		return encodedText.toString();
	}
	 
	public String getEfficiencyInfo() {
		double totalBinaryDigits = 0;
		
		for(char character : encodedText.toString().toCharArray()) {
			if(character != ' ')
				totalBinaryDigits++;
		}
		double efficiency = ((totalBinaryDigits / (message.length() * 8)) * 100);
        return "\n\nBinary digits used: " + totalBinaryDigits +
               "\nBinary digits using ASCII tables: " + (message.length() * 8) +
               "\nTherefore, using Huffman coding the message is: " + efficiency + "% more efficient";
	}

	
}
