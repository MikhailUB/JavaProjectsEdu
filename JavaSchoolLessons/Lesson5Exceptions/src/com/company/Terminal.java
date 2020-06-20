package com.company;

public interface Terminal {
    boolean getPinInputted();
    void inputPin(String pin) throws InvalidPinException, AccountIsLockedException;
    int getBalance() throws PinNotInputedException;
    void putToBalance(String amount) throws PinNotInputedException, InvalidAmountException;
    void pullFromBalance(String amount) throws PinNotInputedException, InvalidAmountException;
}
