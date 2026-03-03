package bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import service.CajeroService;

import java.io.Serializable;

@Named("cajeroBean")
@SessionScoped
public class CajeroBean implements Serializable {

    private String cuenta;
    private String pin;
    private double monto;
    private String mensaje;

    private CajeroService service = new CajeroService();

    // acciones JSF
    public void depositar() {
        mensaje = service.depositar(cuenta, pin, monto);
    }

    public void retirar() {
        mensaje = service.retirar(cuenta, pin, monto);
    }

    public void consultarSaldo() {
        mensaje = service.consultarSaldo(cuenta, pin);
    }

    // getters y setters
    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getMensaje() { return mensaje; }
}