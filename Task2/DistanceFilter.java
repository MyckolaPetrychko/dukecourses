
/**
 * Write a description of DistanceFilter​ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    
    private Location location; 
    private double maxDist;
    
    public DistanceFilter(Location location, double maxDist) { 
        this.location = location;
        this.maxDist = maxDist;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        double distInMeters = location.distanceTo(qe.getLocation());
        return (distInMeters < maxDist); 
    } 
    
     public String getName(){
        return "DistanceFilter";
    }

}
