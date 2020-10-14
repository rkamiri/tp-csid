package shaOneFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

	public static void main(String[] args) throws Exception{
		if (args.length != 0 &&  !args[0].equals("")) {
			Map<String, ArrayList<File>> files = new HashMap<>();
			File f = new File(args[0]);
		//	recursiveSearch(f, files);
			//System.out.println(files);
			String zebi = (getFileContent(new File("C:\\Users\\Windows\\eclipse-workspace\\shaOneFile\\yeety\\test.txt")));
			System.out.println(convertToShaOne(zebi));
		}
	}

	public static void recursiveSearch(File currentDirectory, Map<String, ArrayList<File>> files) {
		for (File file : currentDirectory.listFiles()) {
			if (file.isDirectory()) {
				recursiveSearch(file, files);
			} else if (file.isFile()) {
				if (file.canRead()) {
					try {
						treatment(file, files);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Erreur le fichier ne peut pas être lus");
				}
			} else {
				System.out.println("The file is not a file, nor a directory");
			}
		}
	}

	public static void treatment(File file, Map<String, ArrayList<File>> files) throws Exception{
		String tmpSha = convertToShaOne(getFileContent(file));
		// The hash does'nt exist, we need to create one with it's list
		if (!files.containsKey(tmpSha)) {
			files.put(tmpSha, new ArrayList<File>());
			files.get(tmpSha).add(file);
		}
		// The Hash is already present in the map, we're adding the file to the maps
		// list
		else {
			files.get(tmpSha).add(file);
		}
	}

	public static String getFileContent(File f) throws IOException{
		return new String(Files.readAllBytes(Paths.get(f.getPath())));
	}

	/*
	 * Convert a String into a SHA-1 HASH Return the SHA-1 has a hashed String
	 */
	public static String convertToShaOne(String input) throws Exception {
		String shaone ="";
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		byte[] digest = sha.digest(input.getBytes("UTF-8"));
		// say what one more time
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			shaone+= Integer.toString(digest[i] & 0xFF | 0x100, 16).substring(1);
		}
		return shaone;
	}
}
