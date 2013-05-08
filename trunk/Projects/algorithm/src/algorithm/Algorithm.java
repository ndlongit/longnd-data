package algorithm;

public class Algorithm {
	
	public static void printStart(int size) {
		int middleValue = size / 2;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i <= middleValue) {
					if ((j >= middleValue - i) && (j <= middleValue + i)) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				} else {
					if ((j >= middleValue - (size - i - 1))
							&& (j <= middleValue + (size - i - 1))) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
			}
			System.out.println();
		}
	}
}
