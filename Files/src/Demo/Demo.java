package Demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import java.util.Scanner;
import java.util.TreeMap;

public class Demo {

	public static void main(String[] args) throws IOException {

		File f = new File("voina.txt");
		Scanner sc = new Scanner(f, "UTF-8");
		
		TreeMap<String, Integer> words = new TreeMap<>();
		
		String s = "";
		int wordsCount = 0;
		int characters = 0;
		while(sc.hasNext()) {
			wordsCount++;
			characters += sc.next().length();
			s = sc.next();
			if (words.containsKey(s)) {
				words.put(s, words.get(s)+1);
			} else {
				words.put(s, 1);
			}
		}
		
		File file = new File("words");
		file.mkdir();
		
		String mostPopularWord = "";
		int z = 0;
		
		int warCount = 0;
		int mirCount = 0;
		
		String longestWord = "";
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		
		for (Iterator<Entry<String,Integer>> iterator = words.entrySet().iterator(); iterator.hasNext();) {
			Entry<String,Integer> entry = iterator.next();
			if (longestWord.length() < entry.getKey().length()) {
				longestWord = entry.getKey();
			}
			if (entry.getValue() > z) {
				z = entry.getValue();
				mostPopularWord = entry.getKey();
			}
			if (entry.getKey().equalsIgnoreCase("война")) {
				warCount++;
			}
			else if(entry.getKey().equalsIgnoreCase("мир")) {
				mirCount++;
			}
			int i = entry.getKey().length();
			File ff = new File(file, i+"_Letters.txt");
			fw = new FileWriter(ff, true);
			bw = new BufferedWriter(fw);
			if (ff.exists()) {
				fw.write(entry.getKey());
				bw.newLine();
				bw.flush();
			} else {
				bw.write(entry.getKey());
				bw.newLine();
				bw.flush();
			}
		}
		fw.close();
		
		System.out.println("Files are filled.");
		System.out.println("The number of the words in the book is: " + wordsCount);
		System.out.println("The number of the characters in the book is: " + characters);
		System.out.println("The most popular word in the book is: \"" + mostPopularWord + "\" with " + z + " times.");
		System.out.println("The longest word in the book is: \"" + longestWord + "\"");
		System.out.println("Война - " + warCount);
		System.out.println("Мир - " + mirCount);
		sc.close();
	}
}
