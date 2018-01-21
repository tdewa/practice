import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class BlackJack{
	public static void main(String[] args) {
		// トランプの用意
		Trump trump = new Trump();
		// ディーラー、プレイヤーの挑戦者用意
		Player dealer = new Player("ディーラー");
		Player dewa = new Player("出羽");

		// カードを配布（プレイヤー、ディーラー順）初期準備
		dewa.setHand(trump.getCard());
		dealer.setHand(trump.getCard());
		System.out.printf("%s\n", dealer.openHand()); // ディーラーは1枚目オープン
		dewa.setHand(trump.getCard());
		dealer.setHand(trump.getCard());

		// プレイヤー手札確認
		System.out.printf("%s トータル:%d\n", dewa.openHand(), dewa.calculateTotal());
		System.out.printf("%sの番です\n", dewa.getPlayerName());
		System.out.println("カードを１枚引きますか？引く場合はHit(h)");
		System.out.println("勝負をしますか？引かない場合はStand(s)、Stay(s)");

		// プレイヤーターン
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = new String("");
		try {
			inputStr = stdin.readLine();
			while (inputStr != null && (inputStr.equals("Hit") || inputStr.equals("h"))) { // ヒットが続く限り引く
				dewa.setHand(trump.getCard());
				System.out.printf("%s トータル:%d\n", dewa.openHand(), dewa.calculateTotal());
				if (dewa.calculateTotal() > 21) {
					break;
				}
				inputStr = stdin.readLine();
			}
		}
		catch (Exception except) {
			except.getStackTrace();
			System.exit(-1); // プログラムを強制終了
		}

		// ディーラーターン
		while (dealer.calculateTotal() <= 17) { // 手持ちのカードの合計が「17」以上になるまでヒット
			dealer.setHand(trump.getCard());
		}
		
		// 勝負
		System.out.printf("%s トータル:%d%s\n", dealer.openHand(), dealer.calculateTotal(), dealer.resultHand());
		System.out.printf("%s トータル:%d%s\n", dewa.openHand(), dewa.calculateTotal(), dewa.resultHand());
		if (dewa.calculateTotal() <= 21 && dealer.calculateTotal() <= 21) { // バストしてない
			// 21に近い場合は21に近い方が勝ち
			if (dewa.calculateTotal() > dealer.calculateTotal()) {
				System.out.printf("%sの勝ちです\n", dewa.getPlayerName());
			}
			else if (dewa.calculateTotal() == 21 && dealer.calculateTotal() == 21) {
				if (dewa.countHand() == 2 && dewa.countHand() < dealer.countHand()) {
					System.out.printf("ナチュラル21 %sの勝ちです\n", dewa.getPlayerName());
				}
				else if (dealer.countHand() == 2 && dealer.countHand() < dewa.countHand()) {
					System.out.printf("ナチュラル21 %sの負けです\n", dewa.getPlayerName());
				}
				else {
					System.out.println("引き分けです");
				}
			}
			else {
				System.out.printf("%sの負けです\n", dewa.getPlayerName());
			}
		}
		else { // 片方バストしている
			if (dewa.calculateTotal() > 21 && dealer.calculateTotal() > 21) {
				System.out.printf("%sの負けです 双方がバストした場合は、ディーラーの勝ち\n", dewa.getPlayerName());
			}
			else if (dewa.calculateTotal() > 21) {
				System.out.printf("%sの負けです\n", dewa.getPlayerName());
			}
			else {
				System.out.printf("%sの勝ちです\n", dewa.getPlayerName());
			}
		}
	}
}