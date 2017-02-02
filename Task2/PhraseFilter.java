
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    
    private String where; 
    private String phrase;
    
    public PhraseFilter(String phrase, String where) { 
        this.phrase = phrase;
        this.where = where;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        switch(where){
            case "start":
                return qe.getInfo().startsWith(phrase);
            case "end": 
                    return qe.getInfo().endsWith(phrase);
            case "any":
                return qe.getInfo().contains(phrase.toString());
        }
        return false;
    } 
    
     public String getName(){
        return "PhraseMagFilter";
    }

}
