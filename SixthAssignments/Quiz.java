import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Quiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Quiz {
    public void totalBirths (FileResource fr)
    {
        int totalBirths = 0;
        int totalBoys = 0;
        int countOfBoys = 0;
        int totalGirls = 0;
        int countOfGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                countOfBoys++;
            }
            else{
                totalGirls += numBorn;
                countOfGirls++;
            }
        }
        System.out.println("total births = " + totalBirths + " total count = " +  (countOfBoys + countOfGirls));
        System.out.println("total boys = " + totalBoys  + " total count = " + countOfBoys);
        System.out.println("total girls = " + totalGirls + " total count = " + countOfGirls);
    }
    
    public void testTotalBirths()
    {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender)
    {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 0;
        for (CSVRecord rec : parser)
        {
            String currName = rec.get(0);
            if (rec.get(1).equals(gender)){
                rank++;
                if (currName.equals(name))
                {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank()
    {
        //System.out.println("rank = " + getRank(1960, "Emily", "F"));
        System.out.println("rank = " + getRank(1971, "Frank", "M"));
    }
    
    public String getName(int year, int rank, String gender)
    {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        String notFound = "NO NAME";
        int count = 0;
        for (CSVRecord rec : parser)
        {
            if (rec.get(1).equals(gender)){
                count++;
                if (count == rank)
                {
                    return rec.get(0);
                }
            }
        }
        return notFound;
    }
    
    public void testGetName()
    {
        //System.out.println(getName(2012, 2, "F"));
        //System.out.println(getName(1980, 350, "F"));
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newyear, String gender)
    {
        String newName = getName(newyear, getRank(year, name, gender), gender);
        
        if (gender.equals("M"))
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newyear);
        else
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newyear);
    }
    
    public void testWhatIsNameInYear()
    {
        //whatIsNameInYear("Isabella", 2012, 2014, "F");
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int yearWithHighestRank = 0;
        int highestRank = Integer.MAX_VALUE;
        for (File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int tempRank = getRank(year, name, gender);
            if (tempRank != -1 && tempRank < highestRank)
            {
                highestRank = tempRank;
                yearWithHighestRank = year;
            }
        }
        if (yearWithHighestRank == 0)
            return -1;
        return yearWithHighestRank;
    }
    
    public void testYearOfHighestRank()
    {
        String name = "Genevieve";
        String gender = "F";
        System.out.println(name + " " + gender + " highest ranking was in " + yearOfHighestRank(name, gender));
        name = "Mich";
        gender = "M";
        System.out.println(name + " " + gender + " highest ranking was in " + yearOfHighestRank(name, gender));
    }
    
    public double getAverageRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        double sum = 0;
        int count = 0;
        for (File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int tempRank = getRank(year, name, gender);
            if (tempRank != -1)
            {
                sum += tempRank;
                count++;
            }
        }
        if (count == 0)
            return -1;
        return sum / count;
    }
    
    public void testGetAverageRank()
    {
        String name = "Susan";
        String gender = "F";
        System.out.println(name + " " + gender + " avg rank = " + getAverageRank(name, gender));
        name = "Robert";
        gender = "M";
        System.out.println(name + " " + gender + " avg rank = " + getAverageRank(name, gender));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {
        FileResource fr = new FileResource("yob" + year + ".csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = getRank(year, name, gender);
        int totalBirths = 0;
        for (CSVRecord rec : parser)
        {
            String currName = rec.get(0);
            if (rec.get(1).equals(gender)){
                rank--;
                if (rank <= 0)
                    return totalBirths;
                totalBirths += Integer.parseInt(rec.get(2));
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher()
    {
        String name = "Emily";
        String gender = "F";
        int year = 1990;
        System.out.println(name + " " + gender + " " + year + " total births = " + getTotalBirthsRankedHigher(year, name, gender));
        name = "Drew";
        gender = "M";
        year = 1990;
        System.out.println(name + " " + gender + " " + year + " total births = " + getTotalBirthsRankedHigher(year, name, gender));
    }
}
