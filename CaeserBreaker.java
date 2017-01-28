
/**
 * Write a description of CaeserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaeserBreaker {

    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int k = 0; k < alpha.length(); k++){
            System.out.println(alpha.charAt(k) + "->" + freqs[k]);
        }
        if (maxDex < 4)
            dkey = 26 - (4 - maxDex);
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public int[] countLetters(String message){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++){
            char cur = Character.toLowerCase(message.charAt(k));
            int pos = alpha.indexOf(cur);
            if (pos != -1) 
                counts[pos] += 1;
        }
 
        return counts;
    }
    
    public int maxIndex(int[] m){
       int maxDex = 0;
       for (int k = 0; k < m.length; k++){
           if (m[k] > m[maxDex]){
            maxDex = k;
           }
        }
        return maxDex;
    }
    
    public void TestDecrypt(){
        System.out.println(decrypt("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!"));
    }
    
}
