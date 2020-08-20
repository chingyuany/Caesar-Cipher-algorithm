
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwo {
private String alphabet;
private String shiftedAlphabet1;
private String shiftedAlphabet2;
private int key1;
private int key2;
public CaesarCipherTwo(int key11,int key22)
{
    alphabet= "abcdefghijklmnopqrstuvwxyz";
    shiftedAlphabet1 = alphabet.substring(key11) + alphabet.substring(0,key11);
    shiftedAlphabet2 = alphabet.substring(key22) + alphabet.substring(0,key22);
    key1 = key11;
    key2 = key22;

}
public String encrypt(String input)
{
    StringBuilder encrypted = new StringBuilder(input);
    int start = 0;
 
    for (int k = start; k< encrypted.length() ; k+= 2) {
     char currChar = Character.toLowerCase(encrypted.charAt(k)); 
     int idx = alphabet.indexOf(currChar);
         if(idx != -1){
                
                char newChar = shiftedAlphabet1.charAt(idx);
                  encrypted.setCharAt(k, newChar);
            }
    }
    start =1;
    for (int k = start; k< encrypted.length() ; k+= 2) {
     char currChar = Character.toLowerCase(encrypted.charAt(k)); 
     int idx = alphabet.indexOf(currChar);
         if(idx != -1){
                
                char newChar = shiftedAlphabet2.charAt(idx);
                  encrypted.setCharAt(k, newChar);
            }
    }
      
       return encrypted.toString();
}
   
public String decrypt(String input)
{
   
    int newkey1= 26-key1;
    int newkey2= 26-key2;

    CaesarCipherTwo xx = new CaesarCipherTwo(newkey1, newkey2);
    String decryptmessage = xx.encrypt(input);
    return decryptmessage;
}

   

}
