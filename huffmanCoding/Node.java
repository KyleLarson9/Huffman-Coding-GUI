package huffmanCoding;

public class Node implements Comparable<Node> {

	char character;
	int frequency;
	
	Node leftNode;
	Node rightNode;
	
	public Node(char character, int frequency) {
		this.character = character;
		this.frequency = frequency;
	}
	
	@Override
	public int compareTo(Node node) {
		return Integer.compare(this.frequency, node.frequency);
	}

}
This is an interactive GUI that I created in Java. It uses my implementation of the Huffman Coding Algorithm - a data compression technique that reduces the size of a string of text by assigning shorter binary codes to more frequently occurring characters. Instead of using the ASCII table, which would assign 8 bits to each character, it reduces the bit representation down to 1 or 2 bits depending on the character's frequency.

Implementing this algorithm not only gave me a better understanding of Trees and HashMaps in Java, but it taught me the importance and power these data compression techniques have, even on short strings of text.