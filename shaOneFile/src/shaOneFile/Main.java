package shaOneFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		if (args.length != 0 &&  !args[0].equals("")) {
			Map<String, ArrayList<File>> files = new HashMap<>();
			File f = new File(args[0]);
			recursiveSearch(f, files);
			System.out.println(files);
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

	public static void treatment(File file, Map<String, ArrayList<File>> files)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
		String tmpSha = convertToShaOne(getFileContent(file));
		// The hash doesnt exist, we need to create one with it's list
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

	public static String getFileContent(File f) throws IOException {
		String content = "";
		BufferedReader br = new BufferedReader(new FileReader(f));
		boolean eof = false;
		String actualLine = "";
		while (!eof) {
			actualLine = br.readLine();
			if (actualLine != null) {
				content += actualLine;
			} else {
				eof = true;
			}
		}
		return content;
	}

	/*
	 * Convert a String into a SHA-1 HASH Return the SHA-1 has a hashed String
	 */
	public static String convertToShaOne(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		byte[] digest = sha.digest(input.getBytes("UTF-8"));
		// say what one more time
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			sb.append(Integer.toHexString(digest[i] & 0xFF | 0x100).substring(1, 3));
		}
		return sb.toString();
	}
}
