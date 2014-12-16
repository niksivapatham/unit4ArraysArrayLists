
/**
 * The model for radar scan and accumulator
 * 
 * @author Nikhil Sivapatham
 * @version 15 December 2014
 */
public class Radar
{

    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;

    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;

    //The values of the previous scan
    private boolean[][] previousScan;

    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;

    // number of scans of the radar since construction
    private int numScans;
    
    //The user set velocities
    private int dX;
    private int dY;

    //The user set initial Location
    private int xInit;
    private int yInit;

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols , int changeX, int changeY, int initX, int initY)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        accumulator = new int[11][11]; // elements will be set to 0
        previousScan = new boolean[rows][cols];

        // Sets the location of the monster from the user input (can be explicity set through the
        //  setMonsterLocation method
        monsterLocationRow = initY;
        monsterLocationCol = initX;

        //Sets the ammount the monster will per frame from user input
        dX = changeX;
        dY = changeY;

        //Initial postion, just for reference mainly
        xInit = initX;
        yInit = initY;

        //NoiseFration, noiseFraction and accuracy of lab share an inverse relationship sadly :(
        noiseFraction = 0.01;
        
        //Keeps track of ammount of scan done
        numScans= 0;
    }

    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan()
    {
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {                
                previousScan[row][col] = currentScan[row][col];                
                currentScan[row][col] = false;
            }
        }

        // inject noise into the grid
        injectNoise();

        // Move the monster and detect it
        this.setMonsterLocation((monsterLocationRow+dY), (monsterLocationCol+dX));   

        
        // udpate the accumulator
        for(int row = 0; row < previousScan.length; row++)
        {
            for(int col = 0; col < previousScan[0].length; col++)
            {
                //Basically just finds every true location in the previous scan
                if (previousScan[row][col] == true)
                {                    
                    for (int yCurrent = 0; yCurrent<this.getNumRows(); yCurrent++)
                    {
                        for (int xCurrent = 0; xCurrent<this.getNumCols(); xCurrent++)
                        {
                            //Finds every location in the current scan within 5 cells that are alive 
                            if ((Math.abs(xCurrent-col) <=5) && (Math.abs(yCurrent-row) <=5) && (currentScan[yCurrent][xCurrent] == true))
                            {
                                //Increases the respective "veloctiy" (from index values)
                                accumulator[yCurrent-row+5][xCurrent-col+5] ++;
                            }
                        }
                    }
                }
            }
        }

        // keep track of the total number of scans
        numScans++;
    }

    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        //wrapper for the rows remember the row of the monster's location 
        if ((row>this.getNumRows()-1))
        {            
            monsterLocationRow = row - this.getNumRows();
        }
        else if (row<0) 
        {            
            monsterLocationRow = row+this.getNumRows();
        }
        else {
            monsterLocationRow = row;
        }

        //wrapper for the columns and remember the col of the monster's location 
        if ((col>this.getNumCols()-1)) 
        {
            monsterLocationCol = col - this.getNumCols();
        }
        else if (col<0) 
        {
            monsterLocationCol = col + this.getNumCols();
        } 
        else
        {     
            monsterLocationCol = col;        
        }
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[monsterLocationRow][monsterLocationCol] = true;
    }

    /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }

    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }

    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }

    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }

    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }

    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }

    /**
     * Returns specified value of accumulator
     *
     * @param  row   the row value of the value
     * @param  col   the column value of the value
     * @return     The value at the specified location
     */
    public int getAccumValue(int row, int column)
    {
        return accumulator[row][column];
    }

    /**
     * Returns the final resultant velocities in an array
     *
     * @return     An array of the two velocities
     */
    public int[] getVelocities()
    {
        //Finds the max value
        int max = 0;
        for (int i = 0; i<11; i++){
            for (int j = 0; j<11; j++){
                if (getAccumValue(i,j)>max)
                {
                    max = getAccumValue(i,j);
                }
            }            
        }
        
        //Finds the max's indexes, which are its two velocity components <--(Look at that physics terminology, gonna get ace that AP Physics 1 final !)
        for (int i = 0; i<11; i++)
        {
            for (int j = 0; j<11; j++)
            {
                if (getAccumValue(i,j) == max)
                {                 
                    int xVeloctiy = (j-5);
                    int yVeloctiy = (i-5);
                    int[] velocities = {xVeloctiy, yVeloctiy};
                    return velocities; 
                }
            }            
        }
        //Returns empty set incase world ends becuase Java is picky like that
        int[] exception = new int[10];
        return exception;
    }


    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }

}
