import java.util.*;
/**
 * Write a description of LargestQuakes​ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes{
    
    public void findLargestQuakes​(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        
        ArrayList<QuakeEntry> quake = parser.read(source);
        System.out.println("read data for " + quake.size());
        
        /*for (QuakeEntry qe : quake){
            System.out.println(qe);
        }*/
        
        /*int indexOfMax = indexOfLargest(quake);
        System.out.println("Earthquake with largest magnitude is on " + indexOfMax + " position");
        QuakeEntry qe = quake.get(indexOfMax);
        System.out.println(qe);*/
        
        ArrayList<QuakeEntry> largestQuake = getLargest(quake, 5);
        
        for (QuakeEntry qe : largestQuake){
            System.out.println(qe);
        }
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double max = data.get(0).getMagnitude();
        int index = 0;;
        for (int i = 0; i < data.size(); i++){
            double curr = data.get(i).getMagnitude();
            if (max < curr){
                max = curr;
                index = i;
            }
        }
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> data, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<>();
              
        for (int i = 0; i < data.size(); i++){
            if (i >= howMany) break;
            int indexOfLarge = indexOfLargest(data);
            answer.add(data.get(indexOfLarge));
            data.remove(indexOfLarge);
        }
        
        return answer;
    }

}
