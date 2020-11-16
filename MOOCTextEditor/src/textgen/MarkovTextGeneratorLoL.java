package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method

		if (!wordList.isEmpty()) {
			return;
		}

		// create list words that contains all words
		String[] words = sourceText.split("[\\s]+");

		// code with Pattern class
		// ArrayList<String> words = new ArrayList<>();
		// Pattern tokSplitter = Pattern.compile("[a-zA-Z']+");
		// Matcher m = tokSplitter.matcher(sourceText);
		// find words and save them in an ArrayList `words`
		// while (m.find()) {
		// words.add(m.group());
		// }

		// set "starter" to be the first word in the text
		starter = words[0];
		// set "prevWord" to be starter
		String prevWord = starter;
		// for each word "w" in the source text starting at the second word
		for (int i = 1; i < words.length; i++) {
			boolean isFound = false;
			String w = words[i];
			// check prevWord is already a node in the list
			// loop through the list
			for (ListNode n : wordList) {
				// check if prevWord exist
				if (n.getWord().equals(prevWord)) {
					// if so, add current word as next word in the node
					n.addNextWord(w);
					// set as found
					isFound = true;
				}
			}
			// if no prevWord is found in the list,
			if (isFound == false) {
				// create a new list node with prevWord
				ListNode n = new ListNode(prevWord);
				// add current word in next word list
				n.addNextWord(w);
				// add it to "wordList"
				wordList.add(n);
			}
			// set current word as prevWord
			prevWord = w;
		}
		// add starter to be a next word for the last word in the source text
		ListNode n = new ListNode(words[words.length - 1]);
		n.addNextWord(starter);
		wordList.add(n);
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		// if generator has not yet been trained, return an empty string
		if (wordList.isEmpty() || numWords == 0) {
			return "";
		}
		// TODO: Implement this method
		// set "currWord" to be the starter word
		// the first word of text will be assigned to "starter" via train
		String currWord = starter;
		// set "output" to be ""
		String output = "";
		// add "currWord(first word of text)" to output
		output += currWord;
		// set count
		while (numWords > 1) {
			ListNode n = findNode(currWord);
			if (n == null) {
				continue;
			}
			String w = n.getRandomNextWord(rnGenerator);
			output += "\s" + w;
			currWord = w;
			numWords--;
		}
		return output;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.
	public ListNode findNode(String word) {
		ListNode node = null;
		for (ListNode n : wordList) {
			if (n.getWord().equals(word)) {
				node = n;
			}
		}
		return node;
	}
	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(5));
		String textString2 = "You say yes, I say no, "
				+ "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. "
				+ "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. "
				+ "Why, why, why, why, why, why, " + "Do you say goodbye. "
				+ "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. "
				+ "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(5));
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class
		int random = generator.nextInt(nextWords.size());
		return nextWords.get(random);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
