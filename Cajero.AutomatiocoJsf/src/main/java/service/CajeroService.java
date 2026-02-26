package service;

import cajero.Cliente;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CajeroService {

    private List<Cliente> clientes = new ArrayList<>();

    public CajeroService() {
        cargarClientes();
    }

    // 📥 Leer CSV
    private void cargarClientes() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("clientes.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String cuenta = datos[0];
                String pin = datos[1];
                double saldo = Double.parseDouble(datos[2]);

                clientes.add(new Cliente(cuenta, pin, saldo));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔎 Buscar cliente
    public Cliente buscarCliente(String cuenta, String pin) {
        for (Cliente c : clientes) {
            if (c.getCuenta().equals(cuenta) && c.getPin().equals(pin)) {
                return c;
            }
        }
        return null;
    }

    // 💰 Depósito
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

    // 🏧 Retiro
    public String retirar(String cuenta, String pin, double monto) {
        if (monto <= 0) {
            return "Monto inválido";
        }

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

    // 📊 Consulta saldo
    public String consultarSaldo(String cuenta, String pin) {
        Cliente c = buscarCliente(cuenta, pin);
        if (c == null) {
            return "PIN o cuenta incorrectos";
        }
        return "Saldo actual: " + c.getSaldo();
    }
}