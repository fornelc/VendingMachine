package com.tescobank.vendingmachine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ManagingCoins {

	/**
	 * To manage coins
	 * 
	 * @author Javier Fornells
	 */
	
	private ManagingCoins() {
	}

	private static final ManagingCoins coins = new ManagingCoins();
	private static List<Coin> listCoins = new ArrayList<Coin>();
	// Introduce 4.40 pounds
		static {
			listCoins.add(Coin.Pence10);
			listCoins.add(Coin.Pence10);
			listCoins.add(Coin.Pence20);
			listCoins.add(Coin.Pence50);
			listCoins.add(Coin.Pence50);
			listCoins.add(Coin.Pound1);
			listCoins.add(Coin.Pound1);
			listCoins.add(Coin.Pound1);
		}
	private static final Logger log = Logger.getLogger(ManagingCoins.class.getName());

	public static ManagingCoins getCoins() {
		return coins;
	}
	
	public void addAllCoins(List<Coin> allCoins) {
		listCoins.addAll(allCoins);
	}
	
	public void removeListCoins(List<Coin> removeListCoins) {
		for (Coin coinMoney : removeListCoins) {
			listCoins.remove(coinMoney);
			log.info("Removed from the list: " + coinMoney.toString());
		}
	}

	public List<Coin> getAvailableCoins() {
		return listCoins;
	}

}