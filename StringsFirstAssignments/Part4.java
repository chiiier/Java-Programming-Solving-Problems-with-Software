import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    
    public static void main(String args[])
    {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words())
        {
            if (s.toLowerCase().indexOf("youtube.com") != -1)
            {
                int startIndex = s.indexOf("\"") + 1;
                int endIndex = s.indexOf("\"", startIndex);
                System.out.println(s.substring(startIndex, endIndex));
            }
        }
    }
}
