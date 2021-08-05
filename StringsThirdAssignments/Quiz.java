import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quiz {
    public void processGenes(StorageResource sr)
    {
        int countOfOverLenLimit = 0;
        int countOfOverCgRatio = 0; 
        int maxlen = 0;
        for (String s : sr.data())
        {
            boolean printed = false;
            if (s.length() > 60) 
            {
                printed = true;
                System.out.println(s);
                countOfOverLenLimit++;
            }
            if (cgRatio(s) > 0.35)
            {
                if (!printed)
                    System.out.println(s);
                countOfOverCgRatio++;
            }
            if (maxlen < s.length())
                maxlen = s.length();
        }
        System.out.println("the number of Strings in sr that are longer than 60 characters =" + countOfOverLenLimit);
        System.out.println("the number of strings in sr whose C-G-ratio is higher than 0.35 =" + countOfOverCgRatio);
        System.out.println("the length of the longest gene in sr = " + maxlen);
        System.out.println("the size of sr = " + sr.size());
        
    }
    
    public void testProcessGenes()
    {
        URLResource ur = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna = ur.asString();
        StorageResource testSr = getAllGenes(dna.toUpperCase());
        processGenes(testSr);
        int startIndex = 0;
        int len = dna.length();
        int count = 0;
        while (startIndex < len)
        {
            int currIndex = dna.indexOf("CTG", startIndex);
            if (currIndex == -1)
                break;
            startIndex = currIndex + 3;
            count++;
        }
        System.out.println("There are " + count + " times that the codon CTG appears in this strand of DNA");
    }
    
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
    
    public void testLink()
    {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words())
        {
            String testStr = s.toLowerCase();
            if (testStr.indexOf("youtube.com") != -1)
            {
                int startIndex = s.indexOf("\"");
                int stopIndex = s.indexOf("\"", startIndex + 1);
                System.out.println(s.substring(startIndex + 1, stopIndex));
            }
        }
    }
}
