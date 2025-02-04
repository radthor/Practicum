import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {
        ArrayList<String> product = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;
        String productRec = "";
        String ID = "";
        String name = "";
        String description = "";
        double cost= 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            name = SafeInput.getNonZeroLenString(in, "Enter the product name: ");
            description = SafeInput.getNonZeroLenString(in, "Enter the product description: ");
            cost = SafeInput.getRangedDouble(in, " Enter the product cost:  ", 0, 9999);

            productRec = ID + ", " + name + ", " + description + ", " + cost;
            product.add(productRec);

            done= SafeInput.getYNConfirm(in, "Are you done?");
        }while(!done);

        for( String p: product)
            System.out.println(p);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
        {

            for(String rec : product)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

