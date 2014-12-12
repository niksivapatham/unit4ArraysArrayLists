import java.util.ArrayList;

public class ReverseRemove
{
    public static void SearchAndDestroy(ArrayList<String> list, String math)
    {
        for (int i = list.size() - 1; i >= 0; i--)
        {
            if(match.equals(list.get(i)))
            {
                list.remove(i);
            }
        }
    }
}