
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public void tester()
    {
        FileResource fr = new FileResource();
        
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        
        parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Malawi"));
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        System.out.println("the number = " + numberOfExporters(parser, "gold"));
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public String countryInfo(CSVParser parser, String targetCountry)
    {
        for (CSVRecord record : parser)
        {
            String country = record.get("Country");
            if (country.equals(targetCountry))
            {
                System.out.println("country = " + country);
                System.out.println("targetCountry = " + targetCountry);
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return (country + ": " + exports + ": " + value);
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int count = 0;
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem))
            {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount)
    {
        int len = amount.length();
        for (CSVRecord record : parser)
        {
            String value = record.get("Value (dollars)");
            if (value.length() > len)
                System.out.println(record.get("Country") + " " + value);
        }
    }
}
