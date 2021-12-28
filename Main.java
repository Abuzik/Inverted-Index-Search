package search;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static ArrayList<Integer> find(Map<String, List<Integer>> mp, String toFind) {
        Map<String, List<Integer>> mapCopy = new HashMap<>(mp);
        ArrayList<Integer> arrLst = new ArrayList<>();
        int includes = 0;
        if(mp.containsKey(toFind)) {
            includes = mp.get(toFind).size();
            mp.get(toFind).forEach(item -> {
                arrLst.add(item);
            });
        }
        if(includes != 0) {
            System.out.println(Integer.toString(includes) + " persons found:");
        }
        return new ArrayList<>(arrLst);
    }
    public static void printAllPeoples(String[] all) {
        for(int i = 0; i < all.length; i++) {
            System.out.println(all[i]);
        }
    }
    public static void printMenu() {
        System.out.println("\n=== Menu ===\n 1. Find a person\n 2. Print all people\n 0. Exit");
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String readFileAsStringArray = Files.readString(Paths.get(args[1]));
//        System.out.println(readFileAsStringArray);
        String[] allStr = new String[readFileAsStringArray.split("\n").length];
        allStr = Arrays.copyOf(readFileAsStringArray.split("\n"), readFileAsStringArray.split("\n").length);
        List<String> lst = new ArrayList<String>(Arrays.asList(allStr));
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < lst.size(); i++) {
            String[] str = lst.get(i).split(" ");
            for (int j = 0; j < str.length; j++) {
                if(map.containsKey(str[j])) {
                    map.get(str[j]).add(i);
                } else {
                    List<Integer> num = new ArrayList<>();
                    num.add(i);
                    map.put(str[j], num);
                }
            }
        }
        System.out.println(map.toString());
        printMenu();
        int controller = scanner.nextInt();
        while (controller != 0) {
            switch (controller){
                case 0:
                    controller = 0;
                    break;
                case 1:
                    System.out.print("\nEnter a name or email to search all suitable people.\n");
                    scanner.nextLine();
                    String scan = scanner.nextLine();
                    if(find(map, scan).size() > 0) {
                        ArrayList<Integer> arrList = new ArrayList<>(find(map, scan));
                        for (int it : arrList) {
                            System.out.println(allStr[it]);
                        }
                    }
                    printMenu();
                    controller = scanner.nextInt();
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    printAllPeoples(allStr);
                    printMenu();
                    controller = scanner.nextInt();
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.\n");
                    printMenu();
                    controller = scanner.nextInt();
                    break;
            }
        }
        scanner.close();
        System.out.println("\nBye!");
    }
}