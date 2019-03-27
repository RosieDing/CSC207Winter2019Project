package ATM.Accounts;

import ATM.Accounts.TransferTypes.Depositable;
import ATM.Accounts.TransferTypes.Payable;
import ATM.Accounts.TransferTypes.TransferInable;
import ATM.Accounts.TransferTypes.Withdrawable;


import java.io.Serializable;
import java.util.ArrayList;

public abstract class BasicAccount extends Account implements Withdrawable, TransferInable, Payable, Depositable, Serializable{

        /**
         * Constructor of BasicAccount
         * Create a new account with ownerID
         *
         * @param ownerID the ID of the owner
         */

    public BasicAccount(ArrayList<String> ownerID){
        super(ownerID);
    }

        /**Abstract Method for deposit money to account*/
        public abstract void deposit(int amount);

        /**Abstract Method for paying money from account */
        public abstract void pay(double amount);

        /**Abstract Method for withdraw money from account */
        public abstract void withdraw(int amount);

    }
