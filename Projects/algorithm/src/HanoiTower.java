

public class HanoiTower {

    public static void main(String[] args) {
        int n = 2;
        move(n, 'A', 'B', 'C');
    }

    public static void move(int n, char from, char inter, char to) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
        } else {
            move(n - 1, from, to, inter);
            System.out.println("Move disk " + n + " from " + from + " to " + to);
            move(n - 1, inter, from, to);
        }
    }
}
