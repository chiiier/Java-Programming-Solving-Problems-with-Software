import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord lowestSoFar = null;
        for (CSVRecord record : parser)
        {
            lowestSoFar = getLowestOfTwo(record, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord LowestSoFar)
    {
        if (LowestSoFar == null)
        {
            LowestSoFar = currentRow;
        }
        else 
        {
            double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double lowestTemp = Double.parseDouble(LowestSoFar.get("TemperatureF"));
            //Sometimes there was not a valid reading at a specific hour, 
            //so the temperature field says -9999. 
            //You should ignore these bogus temperature values.
            if (currTemp != -9999 && lowestTemp > currTemp)
            {
                LowestSoFar = currentRow;
            }
        }
        return LowestSoFar;
    }
    
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord lowestTempRow = coldestHourInFile(fr.getCSVParser());
        System.out.println("The lowest temperature " + lowestTempRow.get("TemperatureF") + " occured at " + lowestTempRow.get("TimeEDT") 
         + " on " + lowestTempRow.get("DateUTC") + " UTC");
    }
    
    public String fileWithColdestTemperature()
    {
        CSVRecord lowestSoFar = null;
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(current, lowestSoFar);
            if (current == lowestSoFar)
            {
                fileName = f.getName();
            }
        }
        return fileName;
    }
    
    public void testFileWithColdestTemperature()
    {
        String fileName = fileWithColdestTemperature();
        
        FileResource fr = new FileResource(fileName);
        //print out according to the description of the assignment
        CSVRecord coldestDayRow = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest day was in file " + fileName);
        System.out.println("Coldest temperature on that day was " + coldestDayRow.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser)
        {
            System.out.println(record.get("DateUTC") + ": " + Double.parseDouble(record.get("TemperatureF")));
        }
        //end of print out
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestSoFar = null;
        for (CSVRecord record : parser)
        {
            lowestSoFar = getLowestOfTwoHumidity(record, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public CSVRecord getLowestOfTwoHumidity(CSVRecord currentRow, CSVRecord LowestSoFar)
    {
        if (LowestSoFar == null)
        {
            LowestSoFar = currentRow;
        }
        else 
        {
            String tempCurr = currentRow.get("Humidity");
            String tempLowest = LowestSoFar.get("Humidity");
            if (!tempCurr.equals("N/A") && !tempLowest.equals("N/A"))
            {
                int currHumi = Integer.parseInt(tempCurr);
                int lowestHumi = Integer.parseInt(tempLowest);  
                if (lowestHumi > currHumi)
                {
                    LowestSoFar = currentRow;
                }
            }
        }
        return LowestSoFar;
    }
    
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
                            + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord curr = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwoHumidity(curr, lowestSoFar);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles()
    {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
                            + " at " + csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser)
    {
        double sum = 0.0;
        int count = 0;
        
        for (CSVRecord record : parser)
        {
            double currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp != -9999)
            {
                sum += currTemp;
                count++;
            }
        }
        return sum / count;
    }
    
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(fr.getCSVParser()));    
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double sum = 0.0;
        int count = 0;
        
        for (CSVRecord record : parser)
        {
            String temp = record.get("Humidity");
            if (!temp.equals("N/A"))
            {
                int humidity = Integer.parseInt(temp);
                if (humidity >= value)
                {
                    sum += Double.parseDouble(record.get("TemperatureF"));
                    count++;
                }
            }
        }
        if (count == 0)
            return -9999;
        return sum / count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        
        double avg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (avg == -9999)
        {
            System.out.println("No temperatures with that humidity");
        }
        else
        {
            System.out.println("Average Temp when high Humidity is " + avg);
        }
    }
    
}   
