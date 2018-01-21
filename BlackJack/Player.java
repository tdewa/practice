import java.util.ArrayList;
/**
 * プレイヤークラス
 * 手札管理
 *
 * @auther TsukasaDewa
 */
class Player{
	String playerName;
	ArrayList<String> hand;
	// construct
	public Player(String name) {
		this.playerName = name;
		this.hand = new ArrayList<String>();
	}
	// 名前を取得
	public String getPlayerName() {
		return this.playerName;
	}
	// 手札にカードをセット
	public void setHand(String card) {
		hand.add(card);
	}
	// 手札のカードを見せる
	public String openHand() {
		String handStr = new String("");
		handStr = playerName + "の手札：";
		for (int i=0; i<this.hand.size(); i++) {
			handStr += this.hand.get(i) + " ";
		}
		return handStr;
	}
	// 手札のカードを計算する
	public int calculateTotal() {
		int total = 0;
		for (int i=0; i<this.hand.size(); i++) {
			// 「J、Q、K」の絵柄カードは全て「10」とカウント
			if (this.hand.get(i).equals("J") || 
					this.hand.get(i).equals("Q") ||
					this.hand.get(i).equals("K")
			) {
				total += 10;
			}
			else if (!this.hand.get(i).equals("A")) {
				// 「2～T（10）」はそのままカウント。「T」はTenの略
				total += Integer.parseInt(this.hand.get(i));
			}
		}
		for (int i=0; i<this.hand.size(); i++) { // MEMO: Aが最初で A 6 10 と引いた場合、バストしてしまうためAを最後にカウントしている
			// 「A」”エース”は「1」か「11」として任意にカウント
			if (this.hand.get(i).equals("A")) {
				if (total <= 10) {
					total += 11;
				}
				else {
					total += 1;
				}
			}
		}
		return total;
	}
	// 手札のカード枚数を数える
	public int countHand() {
		return this.hand.size();
	}
	// 手札のカード枚数を数える
	public String resultHand() {
		int total = this.calculateTotal();
		if (total < 21) {
			return "";
		}
		// Bust、Busted（バスト）カードの合計が「22」以上。
		else if (total > 21) {
			return "(Bust、Busted)";
		}
		// BlackJack（ブラックジャック）カードの合計が「21」以上。
		else {
			// 2枚のカードは最高の手
			if (this.countHand() == 2) {
				return "(BlackJack ナチュラル21)";
			}
			else {
				return "(BlackJack)";
			}
		}
	}
}