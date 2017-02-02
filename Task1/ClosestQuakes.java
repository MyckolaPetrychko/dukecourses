
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        Map<Double, QuakeEntry> distanceBetweenCurrentAndQuake = new HashMap<>();
        
        for (QuakeEntry qe : quakeData){
            double distance = current.distanceTo(qe.getLocation()) / 1000;
            distanceBetweenCurrentAndQuake.put(distance, qe);
        }
        
         Map<Double, QuakeEntry> treeMap = new TreeMap<Double, QuakeEntry>(
                new Comparator<Double>() {

                    @Override
                    public int compare(Double o1, Double o2) {
                        return o1.compareTo(o2);
                    }

                });

        treeMap.putAll(distanceBetweenCurrentAndQuake);
        
        int count = 0;
        for (Map.Entry<Double, QuakeEntry> entry : treeMap.entrySet()){
            if (count < howMany){
                answer.add(entry.getValue());
            }else {
                break;
            }
             count++;   
        }

        return answer;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
