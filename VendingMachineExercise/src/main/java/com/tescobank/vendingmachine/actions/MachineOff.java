package com.tescobank.vendingmachine.actions;

import java.util.logging.Logger;

import com.tescobank.vendingmachine.VendingMachine;
import com.tescobank.vendingmachine.exceptions.CoinException;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Item;

/**
 * This class is checking when the machine is turned off
 * 
 * @author Javier Fornells
 *
 */
public class MachineOff implements Action {

	private VendingMachine vendingMachine;
	private static final Logger log = Logger
			.getLogger(MachineOff.class.getName());

	public MachineOff(VendingMachine machineActions) {
		this.vendingMachine = machineActions;
	}

	@Override
	public void machineIsOff() throws UserException {
		throw new UserException("Now machine is turned off");
	}

	@Override
	public void machineIsOn() {
		this.vendingMachine.setAction(new ManageMachine(vendingMachine));
		log.warning("Now machine is turned on");
	}

	@Override
	public Item selectedItem() throws UserException {
		throw new UserException("The machine must be turned on");
	}

	@Override
	public void cancelSelectedItem() throws UserException {
		throw new UserException("The machine must be turned on");
	}

	@Override
	public boolean dispatchItem()
			throws UserException, CoinException {
		throw new UserException("The machine must be turned on");
	}
}