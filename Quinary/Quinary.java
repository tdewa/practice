import java.util.Scanner;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;

public class Quinary
{
	public static final int CONVERSION_NUMBER = 5;

	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		Pattern pattern;
		Matcher match;
		String input_number;
		int decimal_number; // 10進数用数値
		String regex = "^\\d*$";
		int answer = 0;
		System.out.println("１０進数の数値を入力して下さい。（５進数の数値を入力する場合はエンターを入力して下さい）");
		input_number = scan.nextLine();
		pattern = Pattern.compile(regex);
		match = pattern.matcher(input_number);
		// 10進数の入力でなかった場合
		while (input_number.length() > 0 && match.matches() == false) {
			input_number = scan.nextLine();
		}
		if (input_number.length() == 0) {
			int quinary_number; // 5進数用数値
			regex = "^[0-5]+$";
			System.out.println("５進数の数値を入力して下さい。");
			input_number = scan.nextLine();
			pattern = Pattern.compile(regex);
			match = pattern.matcher(input_number);
			// 5進数の入力がなかった場合
			while (input_number.length() == 0 || match.matches() == false) {
				input_number = scan.nextLine();
				match = pattern.matcher(input_number);
			}
			// 5進数の数値が入力された場合は10進数へ変換
			for (int i=0; i<input_number.length(); i++) {
				answer += (int)Math.pow(CONVERSION_NUMBER, input_number.length() - 1 - i) * Integer.parseInt(input_number.substring(i, i + 1));
			}
			System.out.printf("入力された%d進数の値は%s\n", CONVERSION_NUMBER, input_number);
			System.out.printf("10進数では%d\n", answer);
			System.exit(0); // 強制終了
		}
		// 10進数の数値が入力された場合は5進数に変換
		decimal_number = Integer.parseInt(input_number);
		int loop_count = 0;
		while (decimal_number > 0) {
			answer += decimal_number % CONVERSION_NUMBER * (int)Math.pow(10, loop_count);
			decimal_number /= CONVERSION_NUMBER;
			loop_count++;
		}
		System.out.printf("入力された10進数の値は%s\n", input_number);
		System.out.printf("%d進数では%d\n", CONVERSION_NUMBER, answer);
	}
}
