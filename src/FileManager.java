import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    private static final String FILE_NAME = "factory_data.dat";

    public static void saveData(FactoryManager manager) {

        try {
            ObjectOutputStream output =
                    new ObjectOutputStream(
                            new FileOutputStream(FILE_NAME)
                    );

            output.writeObject(manager);
            output.close();

            System.out.println("Data saved successfully.");

        } catch (Exception e) {
            System.out.println(
                    "Error while saving data: " + e.getMessage()
            );
        }
    }

    public static FactoryManager loadData() {

        try {
            ObjectInputStream input =
                    new ObjectInputStream(
                            new FileInputStream(FILE_NAME)
                    );

            FactoryManager manager =
                    (FactoryManager) input.readObject();

            input.close();

            System.out.println("Data loaded successfully.");

            return manager;

        } catch (Exception e) {

            System.out.println("No saved data found.");

            return new FactoryManager();
        }
    }
}