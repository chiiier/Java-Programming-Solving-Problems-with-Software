import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff % 3 == 0)
                return currIndex;
            else
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
        return dna.length();
    }
    
    public void testFindStopCodon()
    {
        //            01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        //     0123456789012
        dna = "AGTCATGAGCTAG";
        dex = findStopCodon(dna, 4, "TAG");
        if (dex != 10) System.out.println("error on 10 dex = " + dex);
        System.out.println("Test finished.");
    }
    
    public String findGene(String dna)
    {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testGene()
    {
        // with ATG and multiple stop codons.(first is TAA)
        String testCase = "AAGATGCCCTAACAAATGATTATATAGACC";
        System.out.println("result = " + findGene(testCase));
        
        // with ATG and one stop codon.
        testCase = "AGTCATGAGCTAG";
        System.out.println("result = " + findGene(testCase));
        
        // with no ATG.
        testCase = "AGTCAAGTAC";
        System.out.println("result = " + findGene(testCase));
        
        // with ATG and no valid stop codons.
        testCase = "AGTCAATGGCCATAA";
        System.out.println("result = " + findGene(testCase));
        
        // with ATG and multiple stop codons. (first is TAG)
        testCase = "ATGTCAAGCTAGCCGATGCAATAA";
        System.out.println("result = " + findGene(testCase));
        
        // test
        testCase = "AATGCTAACTAGCTGACTAAT";
        System.out.println("test result = " + findGene(testCase));
    }
    
    public String findGene(String dna, int where)
    {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int temp = Math.min(taaIndex, tagIndex);
        int minIndex = Math.min(temp, tgaIndex);
        if (minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna)
    {
        int currIndex = 0;
        while (currIndex < dna.length())
        {
            String currGene = findGene(dna, currIndex);
            if (currGene.isEmpty())
                break;
            System.out.println(currGene);
            currIndex = dna.indexOf(currGene, currIndex) + currGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna)
    {
        int currIndex = 0;
        StorageResource sr = new StorageResource();
        while (currIndex < dna.length())
        {
            String currGene = findGene(dna, currIndex);
            if (currGene.isEmpty())
                break;
            sr.add(currGene);
            currIndex = dna.indexOf(currGene, currIndex) + currGene.length();
        }
        return sr;
    }
    
    public void testGetAllGenes()
    {
        String testCase = "AAGATGCCCTAACAAATGATTATATAGACC";
        StorageResource testSr = getAllGenes(testCase);
        for (String s : testSr.data())
        {
            System.out.println(s);
        }
    }
}
