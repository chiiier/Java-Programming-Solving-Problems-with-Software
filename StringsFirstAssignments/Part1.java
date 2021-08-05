import edu.duke.*;
import java.lang.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    private String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
            return "";
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1 || (endIndex - startIndex) % 3 != 0)
            return "";
        return dna.substring(startIndex, endIndex + 3);
    }
    
    private void testSimpleGene(){
        String testCase = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("result = " + findSimpleGene(testCase));
        
        testCase = "AGTCATGAGTAG";
        System.out.println("result = " + findSimpleGene(testCase));
        
        testCase = "AGTCAAGTAC";
        System.out.println("result = " + findSimpleGene(testCase));
        
        testCase = "AGTCAATGGCCTAA";
        System.out.println("result = " + findSimpleGene(testCase));
        
        testCase = "ATGTCAAGTAA";
        System.out.println("result = " + findSimpleGene(testCase));
    }
    
    public static void main(String args[])
    {
        Part1 pt = new Part1();
        pt.testSimpleGene();
    }
}
