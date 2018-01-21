import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class BlackJack{
	private Trump trump;
	private Player dealer, dewa;
	private CardCounting cardCounting;
	// construct
	public BlackJack() {
		// トランプの用意
		this.trump = new Trump();
		// ディーラー、プレイヤーの挑戦者用意
		this.dealer = new Player("ディーラー");
		this.dewa = new Player("出羽");
		// カードカウンティング用意
		this.cardCounting = new CardCounting();
	}
	// BlackJackPlayOnce
	public void startGame() {
		// 手札を初期化
		this.dealer.clearHand();
		this.dewa.clearHand();

		// カードを配布（プレイヤー、ディーラー順）初期準備
		this.dewa.setHand(this.trump.getCard());
		this.dealer.setHand(this.trump.getCard());
		System.out.printf("%s\n", this.dealer.openHand()); // ディーラーは1枚目オープン
		this.cardCounting.countHand(this.dealer.getHand(), 0);
		this.dewa.setHand(this.trump.getCard());
		this.dealer.setHand(this.trump.getCard());
		this.cardCounting.countHand(this.dewa.getHand(), 0);

		// プレイヤー手札確認
		System.out.printf("%s トータル:%d\n", this.dewa.openHand(), this.dewa.calculateTotal());
		// 毎回hit/stayタイミングにて自分のカードをカウンティング
		System.out.println(this.cardCounting.getChanceStatus());
		System.out.printf("%sの番です\n", this.dewa.getPlayerName());
		System.out.println("カードを１枚引きますか？引く場合はHit(h)");
		System.out.println("勝負をしますか？引かない場合はStand(s)、Stay(s)");

		// プレイヤーターン
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = new String("");
		try {
			inputStr = stdin.readLine();
			while (inputStr != null && (inputStr.equals("Hit") || inputStr.equals("h"))) { // ヒットが続く限り引く
				this.dewa.setHand(this.trump.getCard());
				this.cardCounting.countHand(this.dewa.getHand(), this.dewa.getHand().size() - 1);
				System.out.printf("%s トータル:%d\n", this.dewa.openHand(), this.dewa.calculateTotal());
				if (this.dewa.calculateTotal() > 21) {
					break;
				}
				// 毎回hit/stayタイミングにて自分のカードをカウンティング
				System.out.println(this.cardCounting.getChanceStatus());
				System.out.println("カードを１枚引きますか？引く場合はHit(h)");
				System.out.println("勝負をしますか？引かない場合はStand(s)、Stay(s)");
				inputStr = stdin.readLine();
			}
		}
		catch (Exception except) {
			except.getStackTrace();
			System.exit(-1); // プログラムを強制終了
		}

		// ディーラーターン
		while (this.dealer.calculateTotal() <= 17) { // 手持ちのカードの合計が「17」以上になるまでヒット
			this.dealer.setHand(this.trump.getCard());
		}

		// 勝負
		System.out.printf("%s トータル:%d%s\n", this.dealer.openHand(), this.dealer.calculateTotal(), this.dealer.resultHand());
		System.out.printf("%s トータル:%d%s\n", this.dewa.openHand(), this.dewa.calculateTotal(), this.dewa.resultHand());
		if (this.dewa.calculateTotal() <= 21 && this.dealer.calculateTotal() <= 21) { // バストしてない
			// 21に近い場合は21に近い方が勝ち
			if (this.dewa.calculateTotal() > this.dealer.calculateTotal()) {
				System.out.printf("%sの勝ちです\n", this.dewa.getPlayerName());
				this.cardCounting.countWinPlayer();
			}
			else if (this.dewa.calculateTotal() == 21 && this.dealer.calculateTotal() == 21) {
				if (this.dewa.countHand() == 2 && this.dewa.countHand() < this.dealer.countHand()) {
					System.out.printf("ナチュラル21 %sの勝ちです\n", this.dewa.getPlayerName());
					this.cardCounting.countWinPlayer();
				}
				else if (this.dealer.countHand() == 2 && this.dealer.countHand() < this.dewa.countHand()) {
					System.out.printf("ナチュラル21 %sの負けです\n", this.dewa.getPlayerName());
					this.cardCounting.countWinDealer();
				}
				else {
					System.out.println("引き分けです");
				}
			}
			else {
				System.out.printf("%sの負けです\n", this.dewa.getPlayerName());
				this.cardCounting.countWinDealer();
			}
		}
		else { // 片方バストしている
			if (this.dewa.calculateTotal() > 21 && this.dealer.calculateTotal() > 21) {
				System.out.printf("%sの負けです 双方がバストした場合は、ディーラーの勝ち\n", this.dewa.getPlayerName());
				this.cardCounting.countWinDealer();
			}
			else if (this.dewa.calculateTotal() > 21) {
				System.out.printf("%sの負けです\n", this.dewa.getPlayerName());
				this.cardCounting.countWinDealer();
			}
			else {
				System.out.printf("%sの勝ちです\n", this.dewa.getPlayerName());
				this.cardCounting.countWinPlayer();
			}
		}
		// ディーラーの一枚目のカードを除いたカードをカウンティング
		this.cardCounting.countHand(this.dealer.getHand(), 1);
		// 現在の勝利数
		System.out.println(this.cardCounting.getWinCount());
	}
	public static void main(String[] args) {
		BlackJack game = new BlackJack();
		game.startGame(); // 手始めにワンプレイは行なう
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = new String("");
		try {
			System.out.println("プレイを続行しますか？続行する場合はYes(y)");
			inputStr = stdin.readLine();
			while (!inputStr.isEmpty() && (inputStr.equals("Yes") || inputStr.equals("y"))) { // ヒットが続く限り引く
				// BlackJackスタート
				game.startGame();
				System.out.println("プレイを続行しますか？続行する場合はYes(y)");
				inputStr = stdin.readLine();
			}
			stdin.close();
		}
		catch (Exception except) {
			except.printStackTrace();
			System.exit(-1); // プログラムを強制終了
		}
	}
}