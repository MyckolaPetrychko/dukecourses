import java.util.*;
/**
 * Write a description of MatchAllFilter​ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter{

    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<>();
    }
    
    public void addFilter(Filter filter){
        filters.add(filter);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for (Filter filter : filters){
            if (!filter.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    
     public String getName(){
        StringBuilder s = new StringBuilder();
        s.append("Filters used are: ");
        for (Filter f : filters){
            s.append(f.getName());
            s.append(" ");
        }
        return s.toString();
    }
    
}
