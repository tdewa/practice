import java.util.ArrayList;
import java.util.Collections;

/**
 * トランプクラス
 * カードドロー、カードシャッフル
 *
 * @auther TsukasaDewa
 */
class Trump{
	ArrayList<String> cards;
	// construct
	public Trump(){
		/* トランプ52枚の用意 */
		cards = new ArrayList<String>();
		String character = new String("");
		// トランプ52枚生成
		for (int i=1; i<=13; i++) {
			switch (i) {
				case 1:
					character = "A";
					break;
				case 11:
					character = "J";
					break;
				case 12:
					character = "Q";
					break;
				case 13:
					character = "K";
					break;
				default:
					character = "" + i;
					break;
			}
			for (int j=1; j<=4; j++) {
				this.cards.add(character);
			}
		}
		// トランプシャッフル
		Collections.shuffle(this.cards);
	}
	public String getCard(){
		int head_index = 0;
		String card = new String(this.cards.get(head_index));
		// TODO:残りのカードなくなった場合の再度、山の構成、シャッフル
		this.cards.remove(head_index); // 引いたカードは破棄
		return card;
	}
}