import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Repository {
    private ArrayList<String> foods = new ArrayList<>();

    public ArrayList<String> getFoods() {
        return foods;
    }

    public void fileRead(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    foods.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Model {
    private Repository repository;

    public Model(Repository repository) {
        this.repository = repository;
    }

    public HashMap<String, Integer> countItems() {
        HashMap<String, Integer> items = new HashMap<>();
        for (String food : repository.getFoods()) {
            items.put(food, items.getOrDefault(food, 0) + 1);
        }
        return items;
    }

    public String maxString() {
        String maxString = "";
        for (String food : repository.getFoods()) {
            if (food.length() > maxString.length()) {
                maxString = food;
            }
        }
        return maxString;
    }

    public int countFoods() {
        return repository.getFoods().size();
    }

    public void printCountItems() {
        HashMap<String, Integer> items = countItems();
        System.out.println("Слово: количество таких слов");
        for (String food : items.keySet()) {
            if(food != "")
                System.out.println(food + ": " + items.get(food));
        }
    }
}

class Presenter {
    public void run() {
        Repository repository = new Repository();
        File file = new File("src/input.txt");
        repository.fileRead(file);

        Model model = new Model(repository);
        System.out.println("Количество слов: " + model.countFoods());
        System.out.println("Самое длинное слово: " + model.maxString());
        model.printCountItems();
    }
}

public class Main {
    public static void main(String[] args) {
        Presenter presenter = new Presenter();
        presenter.run();
    }
}

