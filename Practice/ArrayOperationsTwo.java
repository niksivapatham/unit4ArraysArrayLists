
public class ArrayOperationsTwo
{
    private int[] values = {1,2,3,4,5,6,7,8,9,10};

    public void swap()
    {
        int first = values[0];
        values[0]=values[values.length-1];
        values[values.length-1]=first;
        
    }
    
    public void print()
    {
        for (int x: values)
        {
            System.out.println(x);
        }
    }
    
    public void shiftRight()
    {
        int first = values[0];
        for (int i=0;i<values.length;i++) {
            if (i==0){                                
                values[0] = values[values.length-1];
            }
            else if(i==values.length-1) {
                values[values.length-1]=first;
            }else{
                values[i]=values[i+1];
            }            
        }
    }
    
    public void replaceEven()
    {
        for (int x: values)
        {
           if (x%2==0){
               values[x-1]=0;               
           }
        }
    }
    
    public void replaceLarge(){
        for (int x=0;x<(values.length-1);x++){
            if (x!=0 || x!= (values.length-1)){
                if (x-1>x)
                {
                    values[x]=values[x-1];
                } else if (x+1>x) {
                    values[x]=values[x+1];
                }
            }
        }
    }
    
    public void replaceMiddle()
    {
        if (values.length%2==0){
            int remove1 = values[(values.length-1)/2];
            
            int remove2 = values[(values.length-1)/2+1];
            System.out.println(remove1+remove2);
            int[] new_values = new int[100];
            for (int x: values){
                if (values[x-1]!= remove1 || values[x-1]!= remove2){
                    new_values[x-1]=values[x-1];
                } else {
                    break;
                }
            }
        }else{
            
        }
    }
}
