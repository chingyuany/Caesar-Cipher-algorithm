
/**
 * Write a description of wordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class wordPlay {
    public boolean isVowel(char ch)
    {
        String vowel = "AEIOU";
        char uch = Character.toUpperCase(ch);
        for(int i = 0; i < 5 ; i++)
        {
            
        if (uch == vowel.charAt(i))
        {
            return true;
        }
        
    }
    return false;
    }
    public String replaceVowels(String phrase,char ch)
    {
          StringBuilder newhprase = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++)
        {
        if (isVowel(phrase.charAt(i)))
        {
              newhprase.setCharAt(i, ch);
        }
        }
        return newhprase.toString();
    }
    public String emphasize(String phrase,char ch)
    {
         char uch = Character.toUpperCase(ch);
        StringBuilder newhprase = new StringBuilder(phrase);
        for(int i = 0; i < phrase.length(); i++)
        {
            char newch = Character.toUpperCase(phrase.charAt(i));
        if ( newch == uch && i % 2 == 0)
        {
              newhprase.setCharAt(i, '*');
        }
        else if (newch == uch && i % 2 != 0)
        {
              newhprase.setCharAt(i, '+');
        }
        }
        return newhprase.toString();
    }
    public void testemphasize()
    {
        char ch = 'a';
        String phrase ="Mary Bella Abracadabra";
        String result = emphasize(phrase, ch);
        System.out.println(result);
    }
    public void testvowel()
    {
        char character = 'O';
        if (isVowel(character))
        {System.out.println(character+" is vowel");}
        else
        {
            System.out.println(character+" is NOT vowel");}
        }
    
    public void testreplaceVowels()
    {
        char ch = '*';
        String phrase = "Hello World";
        String result = replaceVowels(phrase,ch);
        System.out.println(result);
    }
}
