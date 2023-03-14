package HotelManagement;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileCounter {
	
	public static int getCountRows() {

		int count = 0;
		try {
		BufferedReader reader = new BufferedReader(new FileReader("D:\\Java Class\\Project\\ItemCost.txt"));
				String eachline = "";
				while ((eachline = reader.readLine()) != null) {
					count++;
				}
			reader.close();

		} catch (Exception e) {
	}
		return count;
		}

	}

