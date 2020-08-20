
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String Ualphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Lalphabet= "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedUAlphabet = Ualphabet.substring(key)+
        Ualphabet.substring(0,key);
        String shiftedLAlphabet = Lalphabet.substring(key)+
        Lalphabet.substring(0,key);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
           
            char currChar = encrypted.charAt(i);
            
            //Find the index of currChar in the alphabet (call it idx)
            int Uidx = Ualphabet.indexOf(currChar);
            int Lidx = Lalphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(Uidx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedUAlphabet.charAt(Uidx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            else if (Lidx !=-1)
            {
                char newChar = shiftedLAlphabet.charAt(Lidx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String Ualphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Lalphabet= "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String eshiftedUAlphabet = Ualphabet.substring(key1)+
        Ualphabet.substring(0,key1);
        String eshiftedLAlphabet = Lalphabet.substring(key1)+
        Lalphabet.substring(0,key1);
        String oshiftedUAlphabet = Ualphabet.substring(key2)+
        Ualphabet.substring(0,key2);
        String oshiftedLAlphabet = Lalphabet.substring(key2)+
        Lalphabet.substring(0,key2);
        System.out.println(oshiftedUAlphabet);
        System.out.println(oshiftedLAlphabet);
    for (int i = 0; i < encrypted.length(); i++)
    {
        char currChar = encrypted.charAt(i);
        if (i % 2 != 0)
        {
            
            
            int Uidx = Ualphabet.indexOf(currChar);
            int Lidx = Lalphabet.indexOf(currChar);
         if(Uidx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = oshiftedUAlphabet.charAt(Uidx);
                System.out.println(newChar);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            else if (Lidx !=-1)
            {
                char newChar = oshiftedLAlphabet.charAt(Lidx);
                System.out.println(newChar);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }   
        }
        else {
        int Uidx = Ualphabet.indexOf(currChar);
            int Lidx = Lalphabet.indexOf(currChar);
         if(Uidx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = eshiftedUAlphabet.charAt(Uidx);
                System.out.println(newChar);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }
            else if (Lidx !=-1)
            {
                char newChar = eshiftedLAlphabet.charAt(Lidx);
                System.out.println(newChar);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            }   
        
        }
    }
    return encrypted.toString();
    }
    public void testtwokeys()
    {
        int key1= 8;
        int key2= 21;
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String result =encryptTwoKeys(message,key1,key2);
        System.out.println(result);
    }
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}
