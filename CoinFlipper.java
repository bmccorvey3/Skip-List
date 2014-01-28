import java.util.Random;

/**
 * 
 * generates Heads or Tails
 * 
 */
public class CoinFlipper {
	private Random gen;
	
	/**
	 * default constructor
	 */
	public CoinFlipper(){
		this(new Random());
	}
	
	/**
	 * 
	 * constructor with a given source of randomness
	 * @param gen source of randomness
	 */
	public CoinFlipper(Random gen){
		this.gen = gen;
	}
	
	/**
	 * simulates a random coin flip
	 * @return a random heads or tails enum
	 */
	public Coin flipCoin(){
		if(gen.nextBoolean()){
			return Coin.HEADS;
		} else {
			return Coin.TAILS;
		}
	}
	
	/**
	 * 
	 * A coin enum with a heads and tails value
	 *
	 */
	public static enum Coin{
		HEADS, TAILS
	}
}
