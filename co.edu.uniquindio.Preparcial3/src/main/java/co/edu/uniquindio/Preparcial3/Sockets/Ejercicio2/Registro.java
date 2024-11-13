package co.edu.uniquindio.Preparcial3.Sockets.Ejercicio2;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Registro implements Serializable {
    private String identificacionCliente;
    private String tipoVehiculo;
    private double galones;
    private LocalDateTime fechaHora;

    public Registro(String identificacionCliente, String tipoVehiculo, double galones) {
        this.identificacionCliente = identificacionCliente;
        this.tipoVehiculo = tipoVehiculo;
        this.galones = galones;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Registro{" +
                "identificacionCliente='" + identificacionCliente + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", galones=" + galones +
                ", fechaHora=" + fechaHora +
                '}';
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s,%s,%.2f,%s%n",
                identificacionCliente,
                tipoVehiculo,
                galones,
                fechaHora.format(formatter));
    }
}