import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;



public class PersonReader {
    public static void main(String[] args) {


        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        System.out.println("Please select a file");
        try {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                System.out.format("%-12s %-12s %-12s %-8s %-4s%n",
                        "ID#", "Firstname", "Lastname", "Title", "YOB");
                System.out.println("==================================================");

                try (InputStream in = new BufferedInputStream(Files.newInputStream(file));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
                {

                    while (reader.ready()) {
                        rec = reader.readLine();
                        String[] fields = rec.split(",");
                        if (fields.length == 5) {
                            System.out.format("%-12s %-12s %-12s %-8s %-4s%n",
                                    fields[0].trim(),  // ID
                                    fields[1].trim(),  // Firstname
                                    fields[2].trim(),  // Lastname
                                    fields[3].trim(),  // Title
                                    fields[4].trim()); // YOB
                        }
                    }
                    System.out.println("\n\nData file read!");
                }
            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
