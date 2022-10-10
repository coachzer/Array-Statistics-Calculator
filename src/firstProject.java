import java.util.Scanner;
import java.util.Arrays;

public class firstProject {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Length of the array: ");
		int a = sc.nextInt();

		if (a <= 0) {
			System.out.println("Try something else. :)");
		}
		int[] arr = new int[a];
		a = arr.length;
		
		System.out.println("Elements of an array: ");
		for (int i = 0; i < a; i++) {
			arr[i] = sc.nextInt();
		}
		int[] arr1 = new int[a];
		for (int i = 0; i < a; i++) {
			if (isPal(arr[i])) {
				arr1[i] = arr[i];
			}
		}

		int[] arr2 = new int[a];
		for (int i = 0; i < a; i++) {
			arr2[i] = arr[i];
		}

		System.out.println("Number of elements: " + a);
		diffEl(arr);
		System.out.println("Number of different elements: " + diffEl(arr));
		evenNum(arr);
		System.out.println("Number of even numbers: " + evenNum(arr));
		oddNum(arr);
		System.out.println("Number of odd numbers: " + oddNum(arr));
		freqRep(arr);
		maxOcc(arr);
		System.out.println("Number with the max occurrence: " + maxOcc(arr));
		maxNum(arr);
		System.out.println("Largest number: " + maxNum(arr));
		secondMinNum(arr);
		avgNum(arr);
		System.out.println("The average of all numbers: " + avgNum(arr));
		stdDev(arr);
		System.out.println("Standard deviation is: " + stdDev(arr));
		medianF(arr);
		System.out.println("Median is: " + medianF(arr));
		sumNum(arr);
		System.out.println("Sum of all numbers: " + sumNum(arr));
		palNum(arr);
		System.out.println("Number of palindromic numbers: " + palNum(arr));
		largePalSmall(arr);
		System.out.println("Largest palindromic number, that is smaller than the greatest in the array: " + largePalSmall(arr));

		// reverses palindromic numbers only
		reversePrint(arr1);
		System.out.println();
		// reverses inserted numbers
		reversePrint(arr2);
		sc.close();
	}
	
	public static long diffEl(int[] arr) { // counting the numbers of different elements
		int counter = 0;
		
		for (int i = 0; i < arr.length; i++) {
			int j;
			for (j = 0; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					break;
				}
			}
			if (i == j) {
				counter++;
			}
		}
		
		return counter;
	}
	
	public static long evenNum(int[] arr) { // counting the number of even numbers
		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				counter++;
			}
		}
		return counter;
	}
	
	public static long oddNum(int[] arr) {
		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 != 0) { // if (arr[i] % 2 == 1) also works
				counter++;
			}
		}
		return counter;
	}
	
	public static void freqRep(int[] arr) {
		boolean[] visit = new boolean[arr.length];
		Arrays.fill(visit, false); // all elements are false at the beginning

		for (int i = 0; i < arr.length; i++) {
			if (visit[i]) {
				continue; // so we don't repeat elements
			}

			int counter = 1; // every element shows up at lease once so we start from 1
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					visit[j] = true;
					counter++;
				}
			}
			System.out.println("Element: " + arr[i] + " | Repetition: " + (( (double) counter/arr.length) * 100) + "%");
		}
		
	} 
	
	public static long maxOcc(int[] arr) {
		Arrays.sort(arr);
		
		int max = 1;
		int res = arr[0];
		int counter = 1;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1]) 
				counter++;
			else {
				if (counter > max) {
					max = counter; 
					res = arr[i - 1];
				}
				counter = 1; // resetting counter to the starting value
			}
		}
		if (counter > max) {
			res = arr[arr.length - 1];
		}
		return res;
	}
	
	public static long maxNum(int[] arr) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		return max;
	}
	
	public static long minNum(int[] arr) {
		int min = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		return min;
	}
	
	public static void secondMinNum(int[] arr) {
		Arrays.sort(arr);
		
		long min1 = minNum(arr);
		long min2 = Long.MAX_VALUE; // holding the maximum value of long

		if (arr.length < 2) {
			System.out.println("We need at least 2 numbers for this.");
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min1) {
				min2 = min1;
				min1 = arr[i];
			}
			else if (arr[i] < min2 && arr[i] != min1) {
				min2 = arr[i];
			}
		}
		
		if (min2 == Long.MAX_VALUE)
			System.out.println("We only have 1 number.");
		else System.out.println("Smallest number is: " + min1 + " and the second smallest number is: " + min2);
	}
	
	public static double avgNum(int[] arr) {
		
		return (double) sumNum(arr) / arr.length;
	}
	
	public static double stdDev(int[] arr) {
		double stdDeviation = 0;

		for (int i : arr) {
			stdDeviation += Math.pow((i - avgNum(arr)), 2);
		}
		return Math.sqrt(stdDeviation/(arr.length - 1));
	}
	
	public static double medianF(int[] arr) {
		Arrays.sort(arr);
		
		double median;
		if (arr.length % 2 == 0) {
			int middle = arr[arr.length / 2] + arr[arr.length / 2 - 1]; // avg of middle elements
			median = ( (double) middle) / 2;
		}
		else {
			median = arr[arr.length / 2];
		}
		return median;
	}
	
	public static long sumNum(int[] arr) {
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
 	}
	
	public static long revNum(int a) { // this reverses a number
		int i;
		int j = 0;
		int b;
		if (a < 0)
			b = Math.abs(a);
		else b = a;
		
		while (b > 0) {
			i = b % 10;
			j = j * 10 + i;
			b = b / 10;
		}
		
		return j;
	}
	
	public static boolean isPal(int a) { // checks if it's a palindromic number
		int b;
		if (a < 0)
			b = Math.abs(a);
		else b = a;
		return b == revNum(a);
	}
	
	public static long palNum(int[] arr) { // amount of palindromic numbers
		int counter = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (isPal(arr[i])) {
				counter++;
			}
		}
		return counter;
	}

	public static long largePal(int[] arr) { // not used
		Arrays.sort(arr);
		
		for (int i = arr.length - 1; i >= 0; i--) {
			if (isPal(arr[i])) {
				return arr[i];
			}
		}
		
		return 0; // if there is no palindromic numbers
	}
	
	public static long largePalSmall(int[] arr) { // second largest palindromic number
		long max = maxNum(arr);
		long palSmall = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (max > palSmall && isPal(arr[i]) && palSmall < arr[i] && arr[i] < max) {
				palSmall = arr[i];
			}
		}
		
		return palSmall;
	}
	
	public static void reversePrint(int[] arr) { // reversing  palindromic elements in order they were inserted and inserted elements in general

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[arr.length - i - 1] + ", ");
		}
	}
}
