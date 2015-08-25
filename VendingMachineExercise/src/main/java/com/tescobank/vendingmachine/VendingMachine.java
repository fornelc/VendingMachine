package com.tescobank.vendingmachine;

import java.util.List;

import com.tescobank.vendingmachine.actions.Action;
import com.tescobank.vendingmachine.actions.AddingCoins;
import com.tescobank.vendingmachine.actions.MachineOff;
import com.tescobank.vendingmachine.actions.ManageMachine;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Coin;
import com.tescobank.vendingmachine.model.Item;
import com.tescobank.vendingmachine.model.ManagingItems;

/**
 * Get and set coins, items and actions
 * 
 * @author Javier Fornells
 *
 */
public class VendingMachine{


	private Item item = null;

	// Different actions 'Adding Money' - 'Machine is off' - 'Manage Machine'
	private Action action;
	
	private List<Coin> coins;
	
	public VendingMachine() {
	}

	//get current coins
	public List<Coin> getMoneyCoins() {
		return coins;
	}

	//set coins
	public void setMoneyCoins(List<Coin> coins) {
		this.coins = coins;
	}
	
	
	//get current item
	public Item getItem() throws UserException {
		return item;
	}

	//set item
	public void setItem(Item product) {
		this.item = product;
	}


	//get the current action
	public Action getAction() {
		return action;
	}

	//set action
	public void setAction(Action state) {
		this.action = state;
	}
}