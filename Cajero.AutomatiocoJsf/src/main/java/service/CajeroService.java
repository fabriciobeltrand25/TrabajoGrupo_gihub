package service;

import cajero.Cliente;
import java.io.*;
import java.util.*;

public class CajeroService {

    private List<Cliente> clientes = new ArrayList<>();

    public CajeroService() {
        cargarClientes();
    }

    private void cargarClientes() {

        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream("clientes.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                clientes.add(
                        new Cliente(
                                datos[0],
                                datos[1],
                                Double.parseDouble(datos[2])
                        )
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Cliente buscarCliente(String cuenta, String pin) {

        for (Cliente c : clientes) {

            if (c.getCuenta().equals(cuenta) && c.getPin().equals(pin)) {
                return c;
            }

        }

        return null;

    }

    public String depositar(String cuenta, String pin, double monto) {

        if (monto <= 0) {
            return "Monto inválido";
        }

        Cliente c = buscarCliente(cuenta, pin);

        if (c == null) {
            return "PIN o cuenta incorrectos";
        }

        c.setSaldo(c.getSaldo() + monto);

        return "Depósito exitoso. Nuevo saldo: " + c.getSaldo();

    }

    public String retirar(String cuenta, String pin, double monto) {

        Cliente c = buscarCliente(cuenta, pin);

        if (c == null) {
            return "PIN o cuenta incorrectos";
        }

        if (c.getSaldo() < monto) {
            return "Saldo insuficiente";
        }

        c.setSaldo(c.getSaldo() - monto);

        return "Retiro exitoso. Nuevo saldo: " + c.getSaldo();

    }

    public String consultarSaldo(String cuenta, String pin) {

        Cliente c = buscarCliente(cuenta, pin);

        if (c == null) {
            return "PIN incorrecto o cuenta inválida";
        }

        return "Tu saldo actual es: " + c.getSaldo();

    }

}