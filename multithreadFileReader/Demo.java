package multithreadFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {

	public volatile static int count = 0;
	
	public static void main(String[] args) {		
		File f = new File("voina.txt");
		
		readVoinaIMir(3, f);
	}
	
	public static void readVoinaIMir(int threads, File file) {
		StringBuilder sb = new StringBuilder();
		try (Scanner sc = new Scanner(file);){
			while(sc.hasNextLine()) {
				sb.append(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		int start = 0;
		int end = sb.length()/threads;
		ArrayList<Thread> threadss = new ArrayList<>();
		
		for (int i = 0; i < threads; i++) {
			int s = start;
			int e = end;
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					for (int j = s; j < e; j++) {
						if (sb.charAt(j) == ',') {
							count++;
						}
					}
				}
			});
			start+= sb.length()/threads;
			end += sb.length()/threads;
			threadss.add(th);
		}
		
		for (int i = 0; i < threadss.size(); i++) {
			threadss.get(i).start();
			try {
				threadss.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(count);
		
	}
}