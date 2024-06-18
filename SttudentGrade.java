import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();
        int[] Marks = new int[numberOfSubjects];
        
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.printf("Enter marks obtained in subject %d: ", i + 1);
            Marks[i] = scanner.nextInt();
        }
        
        int totalMarks = calculateTotalMarks(Marks);
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);
        char grade = calculateGrade(averagePercentage);
        
        displayResults(totalMarks, averagePercentage, grade);
    }
    
    public static int calculateTotalMarks(int[] Marks) {
        int total = 0;
        for (int mark : Marks) {
            total += mark;
        }
        return total;
    }
    
    public static double calculateAveragePercentage(int totalMarks, int numberOfSubjects) {
        return (double) totalMarks / numberOfSubjects;
    }
    
    public static char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
    
    public static void displayResults(int totalMarks, double averagePercentage, char grade) {
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);
    }
}
