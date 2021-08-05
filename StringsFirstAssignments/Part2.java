import edu.duke.*;
import java.lang.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    private String findSimpleGene(String dna, String startCodon, String stopCodon){
        String testdna = dna.toLowerCase();
        startCodon = startCodon.toLowerCase();
        stopCodon = stopCodon.toLowerCase();
        int startIndex = testdna.indexOf(startCodon);
        if (startIndex == -1)
            return "";
        int endIndex = testdna.indexOf(stopCodon, startIndex + 3);
        if (endIndex == -1 || (endIndex - startIndex) % 3 != 0)
            return "";
        return dna.substring(startIndex, endIndex + 3);
    }
    
    private void testSimpleGene(){
        String testCase = "AGTCAAGTAA";
        System.out.println("result = " + findSimpleGene(testCase, "ATG", "TAA"));
        
        testCase = "AGTCATGAGTAG";
        System.out.println("result = " + findSimpleGene(testCase.toLowerCase(), "ATG", "TAA"));
        
        testCase = "AGTCAAGTAC";
        System.out.println("result = " + findSimpleGene(testCase.toLowerCase(), "ATG", "TAA"));
        
        testCase = "AGTCAATGGCCTAA";
        System.out.println("result = " + findSimpleGene(testCase.toLowerCase(), "ATG", "TAA"));
        
        testCase = "ATGTCAAGTAA";
        System.out.println("result = " + findSimpleGene(testCase, "ATG", "TAA"));
    }
    
    public static void main(String args[])
    {
        Part2 pt = new Part2();
        pt.testSimpleGene();
    }
}
