package task_3;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;



import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;




public class game {

    public static String bytesToHex() throws NoSuchAlgorithmException {
    	KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA3-256");
        SecureRandom random = new SecureRandom();
        keyGen.init(random);
        SecretKey secretKey = keyGen.generateKey();
        String s = new BigInteger(secretKey.getEncoded()).toString(16);
        return s;
    }
    
    public static int menu(String[] name) {
    	System.out.println("Available moves:");		    
	    for(int i = 1; i<= name.length; i++) {
			System.out.println(i + " - " + name[i-1]);
			if (i==name.length) {
				System.out.print("0 - exit \n? - help \n");
			}
        }
	    System.out.print("Enter you move:");
		Scanner in = new Scanner(System.in);
	    String a = in.next();
	    in.close();	  
	    int num = 0;
	   switch (a) {
	    case "0":
		   System.out.println("Your move: exit");
		   System.exit(0);
		   break;
	    case "?":
		   help(name);
		   
		   break;
	    default:
		   num = Integer.parseInt(a);
		   String str = name[num-1];
		   System.out.println("Your move:" + str);
		   
		   break;
	   }
	  
	return num-1;
	    
    }	
	public static void help(String[] args) {
		String[][] tab = new String[args.length][args.length];
		int mid = tab.length/2;
		int winsCount = 0;
		String draw = "DRAW", lose = "LOSE", win = "WIN";
		for(int i=0; i<tab.length; i++) {
			for(int k=0; k<tab.length; k++) {
				if(i == k) {
					tab[i][k] = draw;
					if(i<mid) {
						winsCount = mid;
					}else {
						winsCount = mid+1;
					}
				}else if(winsCount != 0) {
					tab[i][k] = lose;
					winsCount--;
				}else {
					tab[i][k] = win;
				}
			}
		}
		
		
		
	}
	
	public static String games(String[] args, int userIndex, int randIndex) {
		String res = "";
		int mid = args.length/2;
		
		
			if (userIndex<mid && randIndex>mid) {
				res = "You win!";
			}
			if(userIndex<mid && randIndex<=mid) {
				res = "You lose!";
			}
			if(userIndex>=mid && randIndex>mid) {
				res = "You lose!";
			}
			if(randIndex<=mid && userIndex>=mid) {
				res = "You win!";
			}
			if(userIndex == randIndex) {
				res = "Draw";
			}
			
		
		
		return res;
	}


	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, UnsupportedEncodingException{
		if (args.length < 3 || args.length%2 == 0) {
			System.out.println("Input error, example: rock paper scissors lizard Spock");
		} else {
			int randIndex = new Random().nextInt(args.length);
			System.out.println("HMAC: " + bytesToHex());
			
			int userIndex = menu(args);
			
			System.out.println("Computer move: " + args[randIndex]);
			
			System.out.println(games(args, userIndex, randIndex));
			System.out.println("HMAC: " + bytesToHex());
		}
		
		
	}

}
