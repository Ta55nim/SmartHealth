import java.io.*;

public class FileManager {
    private static final String FILE_NAME = "user_data.txt";

    public static void saveUser(User user, double bmi, double bmr) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(user.getName() + "," + user.getAge() + "," + user.getGender() + ","
                    + user.getHeight() + "," + user.getWeight() + "," + bmi + "," + bmr + "\\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void showAllUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
