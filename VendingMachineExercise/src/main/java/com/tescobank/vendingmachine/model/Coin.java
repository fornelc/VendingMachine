package com.tescobank.vendingmachine.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.tescobank.vendingmachine.exceptions.CoinException;

/**
 * Coins = 0.10, 0.20, 0.50 and 1 pound.
 * 
 * @author Javier Fornells
 *
 */
public enum Coin {

	// Accepted moneys
	Pence10(new BigDecimal("0.10")), 
	Pence20(new BigDecimal("0.20")), 
	Pence50(new BigDecimal("0.50")), 
	Pound1(new BigDecimal("1.00"));

	private static final Logger log = Logger.getLogger(Coin.class.getName());
	
	private BigDecimal coinMoney;
	
	Coin(BigDecimal coinMoney) {
		this.coinMoney = coinMoney;
	}

	public BigDecimal getCoinMoney() {
		return coinMoney;
	}

	public static List<Coin> checkChange(String coinMoney) throws CoinException {
		List<Coin> coins = new ArrayList<Coin>();
		BigDecimal availableChange = new BigDecimal(coinMoney);

		while (!availableChange.equals(0)) {
			if (availableChange.compareTo(Coin.Pound1.getCoinMoney()) >= 0) {
				availableChange = availableChange.subtract(Coin.Pound1.getCoinMoney());
				coins.add(Pound1);
			} else if (availableChange.compareTo(Coin.Pence50.getCoinMoney()) >= 0) {
				availableChange = availableChange.subtract(Coin.Pence50.getCoinMoney());
				coins.add(Pence50);
			} else if (availableChange.compareTo(Coin.Pence20.getCoinMoney()) >= 0) {
				availableChange = availableChange.subtract(Coin.Pence20.getCoinMoney());
				coins.add(Pence20);
			} else if (availableChange.compareTo(Coin.Pence10.getCoinMoney()) >= 0) {
				availableChange = availableChange.subtract(Coin.Pence10.getCoinMoney());
				coins.add(Pence10);
			} else {
				log.severe("Imposible give change for " + coinMoney + "; moneys available : 0.10, 0.20, 0.50, 1.00");
				break;
			}
		}
		return coins;
	}
	
	// Validate if user's coin is correct
	public static Coin getValidCoin(String coinMoney) throws CoinException {
		BigDecimal currentCoin = new BigDecimal(coinMoney);

		for (Coin coin : Coin.values()) {
			if (coin.getCoinMoney().equals(currentCoin)) {
				return coin;
			}
		}
		throw new CoinException(coinMoney);
	}

}