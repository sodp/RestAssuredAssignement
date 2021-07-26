package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class randomUser {
	
	/// FUNCTION TO GENERATE RANDOM STRING
	  public static Map<String , String> RandomString() {
		Map <String,String>word = new HashMap<String,String>();

	    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
        int length = 7;
	    for(int i = 0; i < length; i++) {

	      int index = random.nextInt(alphabet.length());

	      char randomChar = alphabet.charAt(index);

	      sb.append(randomChar);
	    }

	    String randomString = sb.toString();
	    word.put("name","absfdf"+randomString);
		return word;

	  }
}
