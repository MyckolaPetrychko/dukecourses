import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData){
            double distanceInMeter = from.distanceTo(qe.getLocation());
            if (distanceInMeter / 1000 < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        /**
         * Get the earthquakes that have a magnitude more than 5
         */
        List<QuakeEntry> filteredByMagnitude = filterByMagnitude(list, 5);
        
         /**
         * Print earthquakes above a certain magnitude
         */
        for (QuakeEntry qe : filteredByMagnitude){
            System.out.println(qe);
        }
        
        /**
         * Print the number of earthquakes that have a magnitude more than 5
         */
        System.out.println("Found " + filteredByMagnitude.size() + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
      
        // This location is Durham, NC (35.988, -78.907),

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        List<QuakeEntry> closedToMe = filterByDistanceFrom(list, 1000, city);
        
        for (QuakeEntry qe : closedToMe){
            StringBuilder s = new StringBuilder();
            s.append((city.distanceTo(qe.getLocation())) / 1000 + " ");
            s.append(qe.getInfo());
            System.out.println(s.toString());
        }
        
        System.out.println("Found " + closedToMe.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    /**
     * Filtering by Depth
     */
    public List<QuakeEntry> filterByDepth(List<QuakeEntry> quake, double minDepth, double maxDepth){
        List<QuakeEntry> answer = new ArrayList<>();
        
        for (QuakeEntry qe : quake){
            double currentDepth = qe.getDepth();
            if (currentDepth > minDepth && currentDepth < maxDepth){
                answer.add(qe);
            }
        }
        
        return answer;
    }
    
    /**
     * Print all the earthquakes from a data source whose depth is between a given 
     * minimum and maximum value
     */
    public void quakesOfDepth​(){
          EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        double minDepth = -10000.0, maxDepth = -5000.0;
        /**
         * Get the earthquakes that have a depth between minDepth and maxDepth
         */
        List<QuakeEntry> filteredByDepth = filterByDepth(list, minDepth, maxDepth);
        
         /**
         * Print earthquakes above a certain depth
         */
        for (QuakeEntry qe : filteredByDepth){
            System.out.println(qe);
        }
        
        /**
         * Print the number of earthquakes that have a depth between minDepth and maxDepth
         */
        System.out.println("Found " + filteredByDepth.size() + " quakes that match that criteria");
    }
    
    /**
     * Filtering by Phrase in Title 
     */
    public List<QuakeEntry> filterByTitle(List<QuakeEntry> quake, String where, String phrase){
        List<QuakeEntry> answer = new ArrayList<>();

        switch(where){
            case "start":
                for (QuakeEntry qe : quake){
                    String info = qe.getInfo();
                    if (info.startsWith(phrase)){
                        answer.add(qe);
                    }
                }
            break;
            case "end":
                for (QuakeEntry qe : quake){
                    String info = qe.getInfo();
                    if (info.endsWith(phrase)){
                        answer.add(qe);
                    }
                }
            break;
            case "any":
                for (QuakeEntry qe : quake){
                    String info = qe.getInfo();
                    if (info.indexOf(phrase) != -1){
                        answer.add(qe);
                    }
                }
            break;
        }
        
        /*String pattern = "";
        switch(where){
            case "start":
                pattern = "^" + phrase;
            break;
            case "end":
                pattern = phrase + "$";
            break;
            case "any":
                pattern = phrase;
            break;
        }
        
        for (QuakeEntry qe : quake){
            String info = qe.getInfo();
             Pattern pattern = Pattern.compile(pattern);
             Matcher m = pattern.matcher(info);
             if (m.find()){
                 answer.add(qe);
             }
        }*/
        
        return answer;
    }
    
    public void quakesByPhrase​(){
         EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        String phrase = "Explosion", where = "start";
        /**
         * Get the earthquakes that have a depth between minDepth and maxDepth
         */
        List<QuakeEntry> filteredByTitle = filterByTitle(list, where, phrase);
        
         /**
         * Print earthquakes above a certain title
         */
        for (QuakeEntry qe : filteredByTitle){
            System.out.println(qe);
        }
        
        /**
         * Print the number of earthquakes that matches the given phrase
         */
        System.out.println("Found " + filteredByTitle.size() + " quakes that match " + phrase + " at " + where);
    }
    
}
