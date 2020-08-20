import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder split= new StringBuilder(message);
        String result = "";
        for(int startIndex = whichSlice; startIndex < split.length() ; startIndex += totalSlices)
        { char w = split.charAt(startIndex);
            result += w;
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i <klength; i++)
        {
            String split = sliceString(encrypted, i, klength);
            int currkey = cc.getKey(split);
            key[i] = currkey;
        }
        return key;
    }

    public void breakVigenere () {
       FileResource fr =  new FileResource("secretmessage1.txt");
        String encrypted = fr.asString();
           int klength = 4;
        int[] key = new int[klength];
        key = tryKeyLength(encrypted, klength, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        String answer = vc.decrypt(encrypted);
        System.out.print(answer);
    }
    public HashSet<String> readDictionary(FileResource  fr)
    {
        HashSet<String> set = new HashSet<String>();
for (String line : fr.lines()) {
   set.add(line.toLowerCase());
}
return set;
       }
       public int countWords(String message,HashSet<String> dictionary)
       {   int count = 0;
           String [] split = message.split("\\W+");
           for(String words : split)
           {
               words = words.toLowerCase();
               if (dictionary.contains(words))
               {
                   count++;
               }
           }
        return count;
        }
        public String breakForLanguage(String encrypted,HashSet<String> dictionary)
        {
           int max = 0;
           int[] maxkey = new int [100] ;
           String message = "";
        for(int index = 1; index <= 100; index++)
        {
          int [] keys = tryKeyLength(encrypted, index, 'e');
          VigenereCipher vc = new VigenereCipher(keys);
          String decrypted = vc.decrypt(encrypted);
          int count = countWords(decrypted,dictionary);
          if(count > max)
          {
           max = count;  
           message = decrypted;
           maxkey = keys;
          }
        }
        System.out.println("key length = "+ maxkey.length);
        for (int i=0 ; i <maxkey.length;i++)
         {System.out.print(maxkey[i]+", ");}
         System.out.println();
         
         System.out.println(max +" valid words in total "+ message.length());
        return message;
        }
        
         public void breakVigenerewithunknowkey () {
       FileResource fr =  new FileResource("secretmessage2.txt");
        String encrypted = fr.asString();
       FileResource dictionary =  new FileResource("dictionaries/English");
       HashSet<String> dict = readDictionary(dictionary);
       String decrypted = breakForLanguage(encrypted,dict);
            String [] ans = decrypted.split("\\n");
           System.out.println(ans[0]);
       
    }
       public void newbreakVigenerewithunknowkey () {
            FileResource fr =  new FileResource("athens_keyflute.txt");
        String encrypted = fr.asString();
       DirectoryResource dr = new DirectoryResource();
       HashMap<String, HashSet<String>> manydict = new HashMap<String, HashSet<String>>();
       for (File f : dr.selectedFiles())
        {
       FileResource dictionary =  new FileResource(f);
       HashSet<String> dict = readDictionary(dictionary);
       manydict.put(f.getName(), dict);
    }
       String ansdict = breakForAllLangs(encrypted, manydict);
       
       
    }
    
    public void breakForLanguagewithkeylength(String encrypted,HashSet<String> dictionary, int index)
        {
         
               
          int [] keys = tryKeyLength(encrypted, index, 'e');
          VigenereCipher vc = new VigenereCipher(keys);
          String decrypted = vc.decrypt(encrypted);
          int count = countWords(decrypted,dictionary);
         
     
        System.out.println("key length = "+ index);
        for (int i=0 ; i <keys.length;i++)
         {System.out.print(keys[i]+", ");}
         System.out.println();
         System.out.println(count +" valid words in total "+ encrypted.length());
      
        }
    public char mostCommonCharIn(HashSet<String> dictionary)
    {
        String result = "";
    for (String w : dictionary) {  result += w;} 
   int [] counts = countLetters(result);
   int max = maxIndex(counts);
    StringBuilder alph = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
    char commonch = alph.charAt(max);
    return commonch;
    }
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
        int max = 0;
        String ansdict = "";
        int num = 0;
         HashSet<String> ansdicts = new  HashSet<String>();
        
    for(String  s : languages.keySet())
    {
        HashSet<String> words = languages.get(s);
        num++;
             Calendar now = Calendar.getInstance();
         long hour = now.get(Calendar.HOUR_OF_DAY);
         long minute = now.get(Calendar.MINUTE);
         System.out.println("目前時間為: "+ hour+" 時"+ minute+" 分 ");
        System.out.println("Trying dict "+s +"第"+num+"本字典");
        int count = newbreakForLanguage(encrypted,words);
        System.out.println("Tried  dict "+s+"第"+num+"本字典"+" contains "+count+" vaild words ");
        if (count > max)
        {
          max = count;
          ansdicts = words;
          ansdict =s ;
        }
    }
    System.out.println("Best possible dict is "+ ansdict+ " it has "+ max + " vaild words");
         Calendar now = Calendar.getInstance(); 
     long   hour = now.get(Calendar.HOUR_OF_DAY);
      long   minute = now.get(Calendar.MINUTE);
         System.out.println("目前時間為: "+ hour+" 時"+ minute+" 分 ");
    return ansdict;
    
    }
     public int newbreakForLanguage(String encrypted,HashSet<String> dictionary)
        {
           char common = mostCommonCharIn(dictionary);
           System.out.println("common char is "+ common);
                Calendar now = Calendar.getInstance();
             long hour = now.get(Calendar.HOUR_OF_DAY);
         long minute = now.get(Calendar.MINUTE);
         System.out.println("目前時間為: "+ hour+" 時"+ minute+" 分 ");
           int max = 0;
           int[] maxkey = new int [100] ;
           String message = "";
           
        for(int index = 1; index <= 100; index++)
        {
            System.out.println("trying key length "+ index);
          int [] keys = tryKeyLength(encrypted, index, common);
          VigenereCipher vc = new VigenereCipher(keys);
          String decrypted = vc.decrypt(encrypted);
          int count = countWords(decrypted,dictionary);
          System.out.println("The "+index+" times has "+count+" vaild words in this dict");
          if(count > max)
          {
           max = count;  
           message = decrypted;
           maxkey = keys;
          }
        }
        /*
        System.out.println("key length = "+ maxkey.length);
        for (int i=0 ; i <maxkey.length;i++)
         {System.out.print(maxkey[i]+", ");}
         System.out.println();
        */
        
        return max;
        }
        public int newbreakForLanguagesperate(String encrypted,HashSet<String> dictionary,char common)
        {
           
           System.out.println("common char is "+ common);
                Calendar now = Calendar.getInstance();
             long hour = now.get(Calendar.HOUR_OF_DAY);
         long minute = now.get(Calendar.MINUTE);
         System.out.println("目前時間為: "+ hour+" 時"+ minute+" 分 ");
           int max = 0;
           int[] maxkey = new int [100] ;
           String message = "";
           
        for(int index = 1; index <= 100; index++)
        {
            System.out.println("trying key length "+ index);
          int [] keys = tryKeyLength(encrypted, index, common);
          VigenereCipher vc = new VigenereCipher(keys);
          String decrypted = vc.decrypt(encrypted);
          int count = countWords(decrypted,dictionary);
          System.out.println("The "+index+" times has "+count+" vaild words in this dict");
          if(count > max)
          {
           max = count;  
           message = decrypted;
           maxkey = keys;
          }
        }
        /*
        System.out.println("key length = "+ maxkey.length);
        for (int i=0 ; i <maxkey.length;i++)
         {System.out.print(maxkey[i]+", ");}
         System.out.println();
        */
        
        return max;
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
}


