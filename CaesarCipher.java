import edu.duke.*;

/**
 * Write a description of CaesarCipher here.
 * 
 * @author (pmv) 
 * @version (24/01/17)
 */
public class CaesarCipher {
    
    public String encrypt(String input, int key){
        // Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        // Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        // Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++){
            // Look at the ith character of encrypted
            char currChar = encrypted.charAt(i);
            // Find the index of currChar in the alphabet
            int idx = alphabet.indexOf(currChar);
            
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            
            // otherwise: do nothing
        }    
        // Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String encryptWithCases(String input, int key){
        // Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        // Write down the alphabet
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        // Compute the shifted alphabet
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        // Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++){
            // Look at the ith character of encrypted
            char currChar = encrypted.charAt(i);

            if (Character.isUpperCase(currChar)){
                int idx = alphabetUpper.indexOf(currChar);
                if (idx != -1){
                     char newChar = shiftedAlphabetUpper.charAt(idx);
                     encrypted.setCharAt(i, newChar);
                } 
            }else{
                int idx = alphabetLower.indexOf(currChar);
                if (idx != -1){
                     char newChar = shiftedAlphabetLower.charAt(idx);
                     encrypted.setCharAt(i, newChar);
                } 
            }   
            // otherwise: do nothing
        }    
        // Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        // Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        // Write down the alphabet
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        // Compute the shifted alphabet
        String shiftedAlphabetUpperKey1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0, key1);
        String shiftedAlphabetLowerKey1 = alphabetLower.substring(key1) + alphabetLower.substring(0, key1);
        String shiftedAlphabetUpperKey2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0, key2);
        String shiftedAlphabetLowerKey2 = alphabetLower.substring(key2) + alphabetLower.substring(0, key2);
        Boolean flag = false;
        // Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++){
            // Look at the ith character of encrypted
            char currChar = encrypted.charAt(i);

            if (Character.isUpperCase(currChar)){
                int idx = alphabetUpper.indexOf(currChar);
                if (idx != -1){
                     if (! flag)
                         encrypted.setCharAt(i, shiftedAlphabetUpperKey1.charAt(idx));
                     else
                        encrypted.setCharAt(i, shiftedAlphabetUpperKey2.charAt(idx));
                     flag = !flag;
                } 
            }else if (Character.isLowerCase(currChar)){
                int idx = alphabetLower.indexOf(currChar);
                if (idx != -1){
                      if (!flag)
                         encrypted.setCharAt(i, shiftedAlphabetLowerKey1.charAt(idx));
                     else
                        encrypted.setCharAt(i, shiftedAlphabetLowerKey2.charAt(idx));
                     flag = !flag;
                } 
            }else 
                flag = !flag;
            // otherwise: do nothing
        }    
        // Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesar(){
        int key1 = 8, key2 = 21, key = 13;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptWithCases(message, key);
        System.out.println(encrypted);
        String decrypted = encryptWithCases(encrypted, key);
        System.out.println(decrypted);
    }

}
