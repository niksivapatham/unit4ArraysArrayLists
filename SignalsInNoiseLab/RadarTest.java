

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RadarTest.
 *
 * @author  Nikhil Sivapatham
 * @version 12/15/14
 */
public class RadarTest
{
    
    /**
     * Tests a default, basic case where it starts at 0,0 and moves only 1 in each direction
     */
    @Test
    public void testStateOne()
    {
        //Does the deed
        Radar radar = new Radar(100,100,1,1,0,0);
        for(int i = 0; i < 100; i++)
        {
            radar.scan();
        }
        
        //Retrieves the final velocities in array form
        int[] velocities = radar.getVelocities();
        
        //Unpacks and tests
        for (int i = 0; i<2; i++)
        {
            if (i ==0 )
            {
                int xVelocity = velocities[i];
                assertEquals(1,xVelocity);
            }
            else if (i ==1)
            {
                int yVelocity = velocities[i];
                assertEquals(1,yVelocity);
            }
        }        
        
    } 
    
    
    /**
     * Tests slightly more advanced case, with differing velocities
     */
    @Test
    public void testStateTwo()
    {
        //Does the deed
        Radar radar = new Radar(100,100,3,4,0,0);
        for(int i = 0; i < 100; i++)
        {
            radar.scan();
        }
        
        //Retrieves the final velocities in array form
        int[] velocities = radar.getVelocities();
        
        //Unpacks and tests
        for (int i = 0; i<2; i++)
        {
            if (i ==0 )
            {
                int xVelocity = velocities[i];
                assertEquals(3,xVelocity);
            }
            else if (i ==1)
            {
                int yVelocity = velocities[i];
                assertEquals(4,yVelocity);
            }
        }        
        
    } 
    
    /**
     * Tests an advanced case, with differing velocites, and different starting position, and different grid size
     */
    @Test
    public void testStateThree()
    {
        //Does the deed
        Radar radar = new Radar(100,60,5,5,99,99);
        for(int i = 0; i < 100; i++)
        {
            radar.scan();
        }
        
        //Retrieves the final velocities in array form
        int[] velocities = radar.getVelocities();
        
        //Unpacks and tests
        for (int i = 0; i<2; i++)
        {
            if (i ==0 )
            {
                int xVelocity = velocities[i];
                assertEquals(5,xVelocity);
            }
            else if (i ==1)
            {
                int yVelocity = velocities[i];
                assertEquals(5,yVelocity);
            }
        }        
        
    } 
}
