
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public float cgRatio(String dna)
    {
        int len = dna.length();
        if (len == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < len; i++)
        {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
                count++;
        }
        return (float) count / len;
    }
    
    public void testCgRatio()
    {
        String testCase = "ATGCCATAG";
        System.out.println("cgRatio = " + cgRatio(testCase));
    }
}
