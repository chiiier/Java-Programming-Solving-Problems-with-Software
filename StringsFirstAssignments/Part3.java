
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    private boolean twoOccurrences(String a, String b)
    {
        int startIndex = b.indexOf(a);
        if (startIndex == -1)
            return false;
        if (b.indexOf(a, startIndex + a.length()) == -1)
            return false;
        return true;
    }
    
    private String lastPart(String a, String b)
    {
        int startIndex = b.indexOf(a);
        if (startIndex == -1)
            return b;
        return b.substring(startIndex + a.length(), b.length());
    }
    
    private void testRunner()
    {
        String a = "by";
        String b = "A story by Abby Long";
        System.out.println("result = " + twoOccurrences(a, b));
        
        a = "a";
        b = "banana";
        System.out.println("result = " + twoOccurrences(a, b));
        
        a = "atg";
        b = "ctgtatgta";
        System.out.println("result = " + twoOccurrences(a, b));
        
        a = "an";
        b = "banana";
        System.out.println("result = " + lastPart(a, b));
        
        a = "zoo";
        b = "forest";
        System.out.println("result = " + lastPart(a, b));
    
    }

    public static void main(String args[])
    {
        Part3 pt = new Part3();
        pt.testRunner();
    }
}
