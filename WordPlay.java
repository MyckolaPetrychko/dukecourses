import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (pvm) 
 * @version (a version number or a date)
 */
public class WordPlay {
    
    /**
     * Return true if the char ch is vowels
     * Otherwise return false
     */
    public Boolean isVowels(char ch){
        return ("aeiou").contains(String.valueOf(ch));
    }
    
    /**
     * Replace vowels in phrase to char ch
     */
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++){
                if (isVowels(sb.charAt(i))){
                    sb.setCharAt(i, ch);
                }
        }
        return sb.toString();
    }
    
    /**
     * Change character ch in phrase 
     *  to '*' if the character is in odd position (i + 1) % 2 > 0
     *  to '+' if the character is in even position (i + 1) % 2 == 0
     */
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
