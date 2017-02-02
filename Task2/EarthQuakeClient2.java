import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 
    
     public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, ArrayList<Filter> f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            for (Filter f1 : f){
                if (!f1.satisfies(qe)) { 
                    continue;
                } 
                answer.add(qe); 
            }
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

       /* Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } */
        
        /*Filter f1 = new MinMaxMagFilter(4.0, 5.0);
        ArrayList<QuakeEntry> qe1  = filter(list, f1); 
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> qe2  = filter(qe1, f2);
        for (QuakeEntry qe : qe2){
            System.out.println(qe);
        }*/
        
        Location japan = new Location(35.42, 139.43);
        String phrase = "Japan";
        String where = "end";
        Filter f1 = new DistanceFilter(japan, 10000000);
        ArrayList<QuakeEntry> qe1  = filter(list, f1); 
        Filter f2 = new PhraseFilter(phrase, where);
        ArrayList<QuakeEntry> qe2  = filter(qe1, f2);
        for (QuakeEntry qe : qe2){
            System.out.println(qe);
        }
        
     
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMathcAllFilter(){
         EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        /*for (QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        
        String phrase = "a";
        String where = "any";
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MinMaxMagFilter(0.0, 2.0);
        Filter f2 = new DepthFilter(-100000.0, -10000.0);
        Filter f3 = new PhraseFilter(phrase, where);
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for (QuakeEntry qe : filtered){
            System.out.println(qe);
        }
    }
    
     public void testMathcAllFilter2(){
         EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        /*for (QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        
        String phrase = "Ca";
        String where = "any";
        
        Location oklahoma = new Location(36.1314, -95.9372);
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MinMaxMagFilter(0.0, 3.0);
        Filter f2 = new DistanceFilter(oklahoma, 10000000.0);
        Filter f3 = new PhraseFilter(phrase, where);
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for (QuakeEntry qe : filtered){
            System.out.println(qe);
        }
        
        System.out.println(maf.getName());
    }

}
