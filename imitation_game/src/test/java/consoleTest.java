import java.util.Scanner;

public class consoleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s="";
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            s=s+str;
            System.out.println("\rBAK:"+ s);
        }
    }
}
