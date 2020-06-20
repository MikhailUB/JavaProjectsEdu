package com.company;

/*
Реализовать интерфейс Terminal, c помощью которого можно:
1) Проверить состояние счета
2) Снять / положить деньги
*/
public class TerminalImpl implements Terminal {

    private final TerminalServer server = new TerminalServer();
    private final PinValidator pinValidator = new PinValidator();
    private boolean pinInputted = false;

    @Override
    public boolean getPinInputted() {
        return pinInputted;
    }

    @Override
    public void inputPin(String pin) throws InvalidPinException, AccountIsLockedException {
        int intPin;
        try {
            intPin = Integer.parseUnsignedInt(pin);
        } catch (NumberFormatException nfEx) {
            throw new InvalidPinException("некорректный PIN-код (следует ввести 4 цифры)");
        }
        pinValidator.inputPin(intPin);
        pinInputted = true;
    }

    private void checkPinInputted() throws PinNotInputedException {
        if (!pinInputted)
            throw new PinNotInputedException("Перед выполнением каждой операции требуется вводить PIN-код");
    }

    @Override
    public int getBalance() throws PinNotInputedException {
        checkPinInputted();
        int value = server.getBalance();
        pinInputted = false;
        return value;
    }

    @Override
    public void putToBalance(String amount) throws PinNotInputedException, InvalidAmountException {
        checkPinInputted();
        int value = stringToAmount(amount);
        server.putToBalance(value);
        pinInputted = false;
    }

    @Override
    public void pullFromBalance(String amount) throws PinNotInputedException, InvalidAmountException {
        checkPinInputted();
        int value = stringToAmount(amount);
        server.pullFromBalance(value);
        pinInputted = false;
    }

    private int stringToAmount(String str) throws InvalidAmountException {
        int value;
        try {
            value = Integer.parseUnsignedInt(str);
        } catch (NumberFormatException nfEx) {
            throw new InvalidAmountException("некорректная сумма (следует вводить цифры)");
        }
        return value;
    }

}
