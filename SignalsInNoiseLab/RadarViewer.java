import javax.swing.JFrame;
import java.util.Scanner;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarViewer
{
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // create the radar, prompts user for dimensions, starting postion, and veloctiy     
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("How many rows should the radar have?: ");
        final int ROWS = in.nextInt();
        System.out.println("How many columns should the radar have?: ");
        final int COLS = in.nextInt();
        System.out.println("How much should the monster move in the x direction (between 1 and 5 inclusive)?: ");
        final int dX = in.nextInt();
        System.out.println("How much should the monster move in the y direction (between 1 and 5 inclusive)?: ");
        final int dY = in.nextInt();
        System.out.println("What should the starting x-coordinate of the monster be?: ");
        final int xInit = in.nextInt();
        System.out.println("What should the starting y-coordinate of the monster be?: ");
        final int yInit = in.nextInt();
        
        //Create the radar class with the entered parameters
        Radar radar = new Radar(ROWS, COLS, dX, dY, xInit, yInit);
        radar.setNoiseFraction(0.01);
        radar.scan();
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // a frame contains a single component; create the radar component and add it to the frame
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        
        // set the size of the frame to encompass the contained component
        frame.pack();
        
        // make the frame visible which will result in the paintComponent method being invoked on the
        //  component.
        frame.setVisible(true);
        
        // perform 100 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        for(int i = 0; i < 100; i++)
        {
            Thread.sleep(100); // sleep 100 milliseconds (1/10 second)
            
            radar.scan();
            
            frame.repaint();
        }
        
        //Finds the max value of the accumulator
        int max = 0;
        for (int i = 0; i<11; i++){
            for (int j = 0; j<11; j++){
                if (radar.getAccumValue(i,j)>max)
                {
                    max = radar.getAccumValue(i,j);
                }
            }            
        }
        
        //Finds the two different index values of the max value of the accumulator, or the x and y velocites
        for (int i = 0; i<11; i++)
        {
            for (int j = 0; j<11; j++)
            {
                if (radar.getAccumValue(i,j) == max)
                {
                    System.out.println("Change in X equals: " + (j-5));
                    System.out.println("Change in Y equals: " + (i-5));
                }
            }            
        }
        
    }
}
