
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b)
    {
        int currIndex = 0;
        int count = 0;
        int len = b.length();
        while (b.indexOf(a, currIndex) != -1)
        {
            currIndex = b.indexOf(a, currIndex) + a.length();
            count++;
        }
        return count;
    }
    
    public void testHowMany()
    {
        //3
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println("times = " + howMany(a, b));
        //2
        a = "AA";
        b = "ATAAAA";
        System.out.println("times = " + howMany(a, b));
        //2
        a = "AA";
        b = "ATAAAAA";
        System.out.println("times = " + howMany(a, b));
        //3
        a = "AA";
        b = "AATATAATAAA";
        System.out.println("times = " + howMany(a, b));
    }
}
