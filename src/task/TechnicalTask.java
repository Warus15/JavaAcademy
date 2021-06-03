package task;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

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
        Solution solution = new Solution(scanner.nextLine());

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            solution.solve(line);
        }
    }
}
