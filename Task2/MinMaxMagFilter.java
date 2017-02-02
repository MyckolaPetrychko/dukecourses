
/**
 * Write a description of MagnitudeFilter​ here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMaxMagFilter implements Filter
{
    private double magMin; 
    private double magMax;
    
    public MinMaxMagFilter(double min, double max) { 
        magMin = min;
        magMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        double mag = qe.getMagnitude();
        return (mag >= magMin && mag <= magMax); 
    } 
    
    public String getName(){
        return "MinMaxMagFilter";
    }

}
