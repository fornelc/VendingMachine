package com.tescobank.vendingmachine.actions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.tescobank.vendingmachine.VendingMachine;
import com.tescobank.vendingmachine.exceptions.CoinException;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Coin;
import com.tescobank.vendingmachine.model.Item;
import com.tescobank.vendingmachine.model.ManagingCoins;
import com.tescobank.vendingmachine.model.ManagingItems;

/**
 * In this class we control the dispatched items
 * 
 * 
 * @author Javier Fornells
 *
 */
public class AddingCoins implements Action {

	private static final Logger log = Logger.getLogger(AddingCoins.class.getName());

	private VendingMachine machineItem;

	public AddingCoins(VendingMachine machine) {
		this.machineItem = machine;
	}

	@Override
	public boolean dispatchItem() throws UserException, CoinException {
		
		List<Coin> insertedCoins = this.machineItem.getMoneyCoins();
		
		if (insertedCoins == null) {
			insertedCoins = new ArrayList<Coin>();
		}
		
		BigDecimal totCoins = sumTotalMoney(insertedCoins);

		Item selectedItem = this.machineItem.getItem();
		
		log.info("You have entered: " + totCoins + " to buy " + this.machineItem.getItem() + " item.");
		
		boolean isDispatched = false;

		if (totCoins.compareTo(selectedItem.getQuantity())> 0) {
			
			// Add all coins
			ManagingCoins.getCoins().addAllCoins(this.machineItem.getMoneyCoins());

			 
			// Calculate the coins change
			BigDecimal change = sumTotalMoney(this.machineItem.getMoneyCoins()).subtract(this.machineItem.getItem().getQuantity());
			
			List<Coin> moneyToReturn = Coin.checkChange(change.toString());
			
			this.machineItem.setMoneyCoins(moneyToReturn);

			// With the change, we remove coins from the list of coins
			ManagingCoins.getCoins().removeListCoins(moneyToReturn);

			//Selling item
			log.warning("Enjoy your item :) - " + this.machineItem.getItem());
			
			//remove item that we are selling, from the list of items
			ManagingItems.getItems().deleteItem(this.machineItem.getItem());
			this.machineItem.setItem(null);
			
			//now we give the change
			log.warning("Thanks, Here is your change: " + this.machineItem.getMoneyCoins());
			isDispatched = true;
			
		} else if (totCoins.compareTo(selectedItem.getQuantity()) == 0 ) {
			log.warning("Enjoy your item :) - " + this.machineItem.getItem());

			//Remove item from the list
			ManagingItems.getItems().deleteItem(this.machineItem.getItem());
			this.machineItem.setItem(null);
			isDispatched = true;
			
		} else {
			log.warning("For this product, you must insert more amount. Price: " + selectedItem.getQuantity() + " - Your amount: " + totCoins);
		}
		
		this.machineItem.setAction(new ManageMachine(machineItem));
		return isDispatched;
		
		
	}
	
	//Check if the machine is turned off
	@Override
	public void machineIsOff() throws UserException {
		this.machineItem.setAction(new MachineOff(machineItem));
		log.warning("The machine is off");
	}

	//Check if the machine is turned on
	@Override
	public void machineIsOn() throws UserException {
		this.machineItem.setAction(new AddingCoins(machineItem));
		throw new UserException("The machine is on");
	}
	
	
	@Override
	public Item selectedItem() throws UserException{
		log.info("Selected item: " + this.machineItem.getItem());
		return this.machineItem.getItem();
	}

	@Override
	public void cancelSelectedItem() throws UserException {
		log.info("Selected item: " + this.machineItem.getItem());
		this.machineItem.setItem(null);
	}
	
	/**
	 * This method calculate the total of coins
	 */
	private BigDecimal sumTotalMoney(List<Coin> coins) {
		BigDecimal totalCoins = BigDecimal.valueOf(0);
		for (Coin coin : coins) {
			totalCoins = totalCoins.add(coin.getCoinMoney());
		}
		return totalCoins;
	}
}
