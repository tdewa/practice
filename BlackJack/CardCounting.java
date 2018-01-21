import java.util.ArrayList;
/**
 * カードカウンティングクラス
 *
 * @auther TsukasaDewa
 */
class CardCounting{
	// プレイヤー有利の場合true
	private boolean chanceFlag;
	// カードカウンティングの合計値
	private int countingTotal;
	// ディーラーの勝利数
	private int winDealer;
	// プレイヤーの勝利数
	private int winPlayer;
	// construct
	public CardCounting() {
		this.chanceFlag = false;
		this.countingTotal = 0;
		this.winDealer = 0;
		this.winPlayer = 0;
	}
	// 有利状態および合計値
	public String getChanceStatus() {
		String statusStr = new String("");
		if (this.chanceFlag) {
			statusStr = "プレイヤー有利(好機)";
		}
		else {
			statusStr = "プレイヤー不利(危機)";
		}
		statusStr += "：カードカウンティング合計値=" + countingTotal;
		return statusStr;
	}
	// 勝利数
	public String getWinCount() {
		return "ディーラーの勝利数:" + this.winDealer + ", プレイヤーの勝利数:" + this.winPlayer;
	}
	// カードカウンティング
	public void counting(String card) {
		if (card.equals("A") ||
				card.equals("10") ||
				card.equals("J") ||
				card.equals("Q") ||
				card.equals("K")
		) {
			this.countingTotal -= 1;
		} else if (Integer.parseInt(card) == 2 ||
				Integer.parseInt(card) == 3 ||
				Integer.parseInt(card) == 4 ||
				Integer.parseInt(card) == 5 ||
				Integer.parseInt(card) == 6
		) {
			this.countingTotal += 1;
		}
		// MEMO:７、８、９ ならばカードカウンティングしない(ゼロ点)

		// 有利状態変更
		if (this.countingTotal > 0) {
			this.chanceFlag = true; // プラスの値ならばプレーヤー側に有利な好機
		}
		else {
			this.chanceFlag = false; // プラスの値ならばプレーヤー側に不利な危機
		}
	}
	// 手札カードを全てカウンティング
	public void countHand(ArrayList<String> cards, int startNumber) {
		for (int i=startNumber; i<cards.size(); i++) {
			this.counting(cards.get(i));
		}
	}
	// ディーラー勝利数をカウント
	public void countWinDealer() {
		this.winDealer++;
	}
	// プレイヤー勝利数をカウント
	public void countWinPlayer() {
		this.winPlayer++;
	}
}