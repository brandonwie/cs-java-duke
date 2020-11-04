import java.io.File;
import java.lang.Character;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
  public String sliceString(String message, int whichSlice, int totalSlices) {
    String slicedStr = "";
    for (int i = whichSlice; i < message.length(); i += totalSlices) {
      char c = message.charAt(i);
      slicedStr += c;
    }
    return slicedStr;
  }

  public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
    int[] keys = new int[klength];
    for (int i = 0; i < keys.length; i++) {
      CaesarCracker cc = new CaesarCracker(mostCommon);
      String sliced = sliceString(encrypted, i, klength);
      int key = cc.getKey(sliced);
      keys[i] = key;
    }

    return keys;
  }

  /**
   *
   * @param fr read a dictionary file
   * @return HashSet that has words in the dictionary (lower cases)
   */
  public HashSet<String> readDictionary(FileResource fr) {
    HashSet<String> set = new HashSet<>();
    for (String line : fr.lines()) {
      // lower cases
      line = line.trim();
      set.add(line.toLowerCase());
    }
    return set;
  }

  public int countWords(String message, HashSet<String> dictionary) {
    int count = 0;
    String[] splitMsg = message.split("\\W+");
    for (String word : splitMsg) {
      if (dictionary.contains(word.toLowerCase())) {
        count++;
      }
    }
    return count;
  }

  public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
    int max = 0;
    int count = 0;
    String answer = "";
    ArrayList<Integer> answerKey = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
      int[] keys = tryKeyLength(encrypted, i, 'e');
      // for each key length create Vigernere Cipher class and decrypt
      VigenereCipher vc = new VigenereCipher(keys);
      String decrypted = vc.decrypt(encrypted);
      // count how many words in dictionary
      count = countWords(decrypted, dictionary);
      if (count > max) {
        answerKey.clear();
        max = count;
        answer = decrypted;
        for (int j : keys) {
          answerKey.add(j);
        }
      }
    }
    System.out.println(max + " valid words found");
    System.out.println("Key Found: " + answerKey);
    System.out.println("Key Size: " + answerKey.size());
    return answer;
  }

  public String breakForRandomLanguage(String encrypted, HashSet<String> dictionary, char c) {
    int max = 0;
    int count = 0;
    String answer = "";
    ArrayList<Integer> answerKey = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
      int[] keys = tryKeyLength(encrypted, i, c);
      // for each key length create Vigernere Cipher class and decrypt
      VigenereCipher vc = new VigenereCipher(keys);
      String decrypted = vc.decrypt(encrypted);
      // count how many words in dictionary
      count = countWords(decrypted, dictionary);
      if (count > max) {
        answerKey.clear();
        max = count;
        answer = decrypted;
        for (int j : keys) {
          answerKey.add(j);
        }
      }
    }
    System.out.println(max + " valid words found");
    System.out.println("Key Found: " + answerKey);
    System.out.println("Key Size: " + answerKey.size());
    return answer;
  }

  public char mostCommonCharIn(HashSet<String> words) {
    HashMap<Character, Integer> charMap = new HashMap<>();
    for (String word : words) {
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (!charMap.containsKey(c)) {
          charMap.put(c, 1);
        } else {
          charMap.put(c, charMap.get(c) + 1);
        }
      }
    }
    int max = 0;
    char maxCountChar = 'e';
    for (char c : charMap.keySet()) {
      if (charMap.get(c) > max) {
        max = charMap.get(c);
        maxCountChar = c;
      }
    }
    return maxCountChar;
  }

  public HashMap<String, HashSet<String>> mountDictionaries() {
    HashMap<String, HashSet<String>> multipleDictionaries = new HashMap<>();
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      String fname = f.getName();
      FileResource fr = new FileResource(f);
      HashSet<String> dict = readDictionary(fr);
      multipleDictionaries.put(fname, dict);
    }
    return multipleDictionaries;
  }

  public void breakForAllLang(String encrypted, HashMap<String, HashSet<String>> languages) {
    for (String lang : languages.keySet()) {
      char c = mostCommonCharIn(languages.get(lang));
      System.out.println("Language: " + lang);
      breakForRandomLanguage(encrypted, languages.get(lang), c);
    }
  }

  public void breakVigenere() {
    FileResource fr = new FileResource();
    String input = fr.asString();
    FileResource dict = new FileResource();
    HashSet<String> dictRead = readDictionary(dict);
    String decrypted = breakForLanguage(input, dictRead);
    System.out.println(decrypted);
  }

  public void breakVigenereAllLang() {
    FileResource fr = new FileResource();
    String encrypted = fr.asString();
    HashMap<String, HashSet<String>> multipleDict = mountDictionaries();
    breakForAllLang(encrypted, multipleDict);
  }

}
