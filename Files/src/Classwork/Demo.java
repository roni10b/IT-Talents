package Classwork;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Demo {

	public static void main(String[] args)  {

		Car bmw = new Car(4, "x6");
		Car bmw1 = new Car(4, "x5");
		Car mercedes = new Car(4, "C180");
		Car mercedes1 = new Car(4, "C220");

		HashMap<String, HashMap<String, ArrayList<Car>>> cars = new HashMap<>();
		addCar(cars, "cars", "BMW", bmw);
		addCar(cars, "cars", "BMW", bmw1);
		addCar(cars, "cars", "Mercedes", mercedes);
		addCar(cars, "cars", "Mercedes", mercedes1);

		// XStream xStream = new XStream();
		// xStream.aliasField("Name", Person.class, "name");
		// xStream.aliasField("Age", Person.class, "age");
		// xStream.aliasField("Mail", Person.class, "mail");
		// xStream.alias("Person", people.getClass());
		// String xml = xStream.toXML(people);
		//
		// File xmlFile = new File("person.xml");
		// xmlFile.createNewFile();
		// BufferedWriter bw = new BufferedWriter(new FileWriter(xmlFile,
		// false));
		// bw.write(xml);
		// bw.flush();
		// System.out.println(xml);
		
		File gson = new File("koli.json");
		writeToJsonFile(gson, cars);
		System.out.println(readFromJsonFile(gson));
		
		Gson j = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(readFromJsonFile(gson));
		JsonObject c = element.getAsJsonObject();
		JsonArray cars1 = c.get("cars").getAsJsonObject().get("Mercedes").getAsJsonArray();
		ArrayList<Car> asd = new ArrayList<>();
		for (JsonElement jsonElement : cars1) {
			asd.add(j.fromJson(jsonElement, Car.class));
		}
	}

	public static void addCar(HashMap<String, HashMap<String, ArrayList<Car>>> cars, String typeCar, String model,
			Car car) {
		HashMap<String, ArrayList<Car>> firstLevel = cars.get(typeCar);
		if (firstLevel == null) {
			firstLevel = new HashMap<>();
			cars.put(typeCar, firstLevel);
		}
		ArrayList<Car> secondLevel = cars.get(typeCar).get(model);
		if (secondLevel == null) {
			secondLevel = new ArrayList<>();
			firstLevel.put(model, secondLevel);
		}
		secondLevel.add(car);
	}
	public static String readFromJsonFile(File file) {
		StringBuilder sb = new StringBuilder();
		if (isFileValid(file)) {
			try (Scanner sc = new Scanner(file);) {
				while (sc.hasNextLine()) {
					sb.append(sc.nextLine());
				}
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
		} else {
			System.out.println("Invalid file.");
		}
		return sb.toString();
	}
	public static void writeToJsonFile(File file, HashMap<String, HashMap<String, ArrayList<Car>>> col) {
		Gson gson = new Gson();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			gson.toJson(col, bw);
			bw.flush();
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	public static boolean isFileValid(File file) {
		if (file == null) {
			System.out.println("Invalid file");
			return false;
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("The file already exists.");
			}
		}
		return true;
	}
}
