import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MonsterLoot {

	private static String filePath = "/home/arch/Documents/workspace/LootMonster/MONSTERS/";
	private static ArrayList<String> monsterList;
	static ArrayList<String> monsterLoot;

	public static void main(String[] args) throws IOException {

		monsterList = getMonsters();	

		for(String monsterName : monsterList){		
			String name = toTitleCase(monsterName);
			System.out.println(name + " - " + getMonsterLoot(monsterName) + "\n");	
		}
	}

	private static ArrayList<String> getMonsterLoot(String fileName){

		ArrayList<String> lootList = new ArrayList<String>();

		Scanner txtscan = null;
		try {
			txtscan = new Scanner(new File(filePath + fileName + ".xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(txtscan.hasNextLine()){

			while(txtscan.hasNextLine()){	

				String str = txtscan.nextLine();

				if(str.indexOf("<item id=") != -1){

					str = str.replaceAll("\\s+","");
					str = str.substring(9, 13);

					lootList.add(idToString(str));
				}				
			}

		}
		return lootList;
	}

	private static ArrayList<String> getMonsters(){

		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		monsterList = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				if (fileName.endsWith(".xml")) {
					fileName = fileName.substring(0, fileName.length() - 4);					  
				}				

				monsterList.add(fileName);
			}
		}

		Collections.sort(monsterList, String.CASE_INSENSITIVE_ORDER);

		return monsterList;

	}

	private static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0)))
			.append(arr[i].substring(1)).append(" ");
		}          
		return sb.toString().trim();
	}  


	private static String idToString(String ID){

		String path = "/home/arch/Documents/workspace/LootMonster/";
		String itemName = null;

		Scanner scan = null;
		try {
			scan = new Scanner(new File(path + "items.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(scan.hasNextLine()){
			itemName = scan.nextLine();

			if(itemName.indexOf("<item id=\"" + ID) != -1){
				itemName = itemName.substring(22, itemName.length()-3);				
				return itemName;
			}

		}
		return null;
	}
	
	//TODO
	private void toHTML(){


	}

}

