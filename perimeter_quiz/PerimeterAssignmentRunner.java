import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for (Point p : s.getPoints()) {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalPerim = 0.0;
        int count = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            totalPerim = totalPerim + currPt.distance(prevPt);
            prevPt = currPt;
            count++;
        }
        if (count == 0)
            return totalPerim;
        return totalPerim / count;
    }

    public double getLargestSide(Shape s) {
        /// Put code here
        double longestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double distance = currPt.distance(prevPt);
            prevPt = currPt; 
            if (longestSide < distance);
                longestSide = distance;
        }
        return longestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = s.getLastPoint().getX();
        for (Point p : s.getPoints()){
            int x = p.getX();
            if (largestX < x)
                largestX = x;
        }        
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if (perim > largestPerim)
                largestPerim = perim;
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPerim = 0.0;
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perim = getPerimeter(s);
            if (perim > largestPerim)
            {
                largestPerim = perim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int countOfNums = getNumPoints(s);
        System.out.println("count = " + countOfNums);
        double avgLength = getAverageLength(s);
        System.out.println("average length = " + avgLength);
        double longestSide = getLargestSide(s);
        System.out.println("longest side = " + longestSide);
        double largestX = getLargestX(s);
        System.out.println("largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double length = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + length);
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileName = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
