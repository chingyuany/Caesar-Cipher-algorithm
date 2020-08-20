
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
public int[] countLetters(String message)
{
    String alph = "abcdefghijklmnopqrstuvwxyz";
    int [] counts = new int[26];
    for(int k=0; k< message.length();k++)
    {
        char ch = Character.toLowerCase(message.charAt(k));
        int dex = alph.indexOf(ch);
        if (dex !=-1)
        {
            counts[dex]+=1;
        }
    }
    return counts;
}
public int maxIndex(int [] vals)
{
    int maxDex =0;
    for(int k=0; k<vals.length;k++)
    {
        if (vals[k] >vals[maxDex])
        {maxDex = k;}
    }
    return maxDex;
}
public String halfOfString(String message, int start)
{
    String halfstring = "";
    for (int i = 0; i < message.length(); i++)
    {
     if (start == 0 && i % 2 == 0)
     {
        halfstring += message.charAt(i);
     }
     else if (start == 1 && i%2 !=0)
     {
         halfstring += message.charAt(i);
     }
    }
    return halfstring;
}
public int getKey(String s)
{
    int[] freqs = countLetters(s);
    int maxDex = maxIndex(freqs);
    return maxDex;
}
public String breakCaesarCipher(String input)
{
String evenstring = halfOfString(input,0);
String oddstring = halfOfString(input,1);
int evenkey = getKey(evenstring);
int oddkey = getKey(oddstring);
int devenkey = evenkey - 4;
    if (evenkey <4)
    {
        devenkey = 26 - (4-evenkey);
    }
int doddkey = oddkey - 4;
    if (oddkey <4)
    {
        doddkey = 26 - (4-oddkey);
    }
    System.out.println(devenkey+"\t"+doddkey);
CaesarCipherTwo cc = new CaesarCipherTwo(devenkey,doddkey);    
 String decrypted = cc.decrypt(input);
    return decrypted;
}
 public void simpleTests()
{
FileResource fr = new FileResource();
String s = fr.asString();
//String s = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
CaesarCipherTwo cc = new CaesarCipherTwo(14,24);
String encrypted = cc.encrypt(s);
//System.out.println("encrypted \n" +encrypted);
String decrypted = cc.decrypt(s);
//System.out.println("decrypted \n" +decrypted);
String breaking = breakCaesarCipher(s);
System.out.println("breaking \n" +breaking);
}
}
