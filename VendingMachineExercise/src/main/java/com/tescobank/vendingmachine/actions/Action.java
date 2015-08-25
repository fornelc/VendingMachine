package com.tescobank.vendingmachine.actions;

import com.tescobank.vendingmachine.exceptions.CoinException;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Item;

/**
 * Interface used by 'InsertCoinsState' - 'ManageMachine' - 'MachineOnOff', to manage actions
 * 
 * @author Javier Fornells
 *
 */
public interface Action {
	
	Item selectedItem() throws UserException;

	void cancelSelectedItem() throws UserException;
	
	boolean dispatchItem() throws UserException,
			CoinException;

	/**
	 * Turn off machine
	 */
	void machineIsOff() throws UserException;

	/**
	 * Turn on machine
	 */
	void machineIsOn() throws UserException;
	
}
