import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ToyList {

    public static void main(String[] args) {
        List<Toy> toys = readFile("/Users/denis_udud/IdeaProjects/KPP_Java/src/toys.txt");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть мінімальну ціну у копійках: ");
        int minPrice = scanner.nextInt();

        System.out.print("Введіть максимальну ціну у копійках: ");
        int maxPrice = scanner.nextInt();

        int minAge = 0;
        int maxAge = 5;

        List<Toy> filteredToys = toys.stream()
                .filter(toy -> toy.ageRangeContains(minAge, maxAge) && toy.priceInRange(minPrice, maxPrice))
                .sorted(Comparator.comparingInt(Toy::getPrice).reversed())
                .collect(Collectors.toList());

        System.out.println("Перелік іграшок, ціна яких належить вказаному діапазону, і які підходять дітям від 5 років у порядку спадання ціни:");
        for (Toy toy : filteredToys) {
            DecimalFormat df = new DecimalFormat("00");
            System.out.println(toy.name + " - " + (toy.price / 100) + " грн і " + df.format(toy.price % 100) + " коп. " + toy.ageRange);
        }
    }

    public static List<Toy> readFile(String fileName) {
        List<Toy> toys = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                toys.add(new Toy(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return toys;
    }
}