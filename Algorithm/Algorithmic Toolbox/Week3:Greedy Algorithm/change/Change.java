import java.util.Scanner;

public class Change {
    private static int getChange(int value) {
        int result = 0;
        if (value / 10 > 0) {
            result += value / 10;
            value %= 10;
        }
        if (value / 5 > 0) {
            result += value / 5;
            value %= 5;
        }
        result += value;
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

