import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    
    public Boolean isVowels(char ch){
        return ("aeiou").contains(String.valueOf(ch));
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++){
                if (isVowels(sb.charAt(i))){
                    sb.setCharAt(i, ch);
                }
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++){
            if (Character.toLowerCase(sb.charAt(i)) == Character.toLowerCase(ch)){
                sb.setCharAt(i, ((i + 1) % 2) > 0 ? '*' : '+');
            }
        }
        return sb.toString();
    }
   
    public void testIsVowels(){
        System.out.println(isVowels('F'));
        System.out.println(isVowels('a'));
    }
    
    public void testReplaceVowels(){
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

}
