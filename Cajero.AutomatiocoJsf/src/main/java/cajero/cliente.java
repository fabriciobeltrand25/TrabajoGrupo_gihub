package cajero;

public class Cliente {

    private String cuenta;
    private String pin;
    private double saldo;

    public Cliente() {}

    public Cliente(String cuenta, String pin, double saldo) {
        this.cuenta = cuenta;
        this.pin = pin;
        this.saldo = saldo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}