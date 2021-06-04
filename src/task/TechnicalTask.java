package task;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

//class that initialize project
public class TechnicalTask {

    private Scanner scanner;

    public TechnicalTask() {
        URL url = getClass().getResource("data.txt");

        try {
            File file = new File(url.getPath());
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        //Creating instance  of solution class.
        //The parameter in constructor is first line of the file, which contains key.
        Solution solution = new Solution(scanner.nextLine());

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            solution.solve(line);
        }
    }
}
