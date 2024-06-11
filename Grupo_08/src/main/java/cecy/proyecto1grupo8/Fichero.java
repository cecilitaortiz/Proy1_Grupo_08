package cecy.proyecto1grupo8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Fichero {

    public static Queue<String> leer(String rutaArchivo) {
        Queue<String> lineas = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.offer(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    public static boolean escribir(String ruta, String linea) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(linea + "\n");
        } catch (IOException ex) {
            System.err.println("No se pudo escribir el archivo " + ruta);
            return false;
        }

        return true;
    }
public static List<String> leerArchivo(String rutaArchivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }
    public static List<String> eliminarLinea(List<String> lineas, String condicion) {
        List<String> nuevasLineas = new LinkedList<>();
        for (String linea : lineas) {
            if (!linea.equals(condicion)) {
                nuevasLineas.add(linea);
            }
        }
        return nuevasLineas;
    }

    public static void escribirArchivo(String rutaArchivo, List<String> lineas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    public static Queue<Auto> cargarAutos() {

        Queue<Auto> autos = new LinkedList<>();
        Queue<String> datos = Fichero.leer("src/main/resources/archivos/autos.txt");
        for (String dato : datos) {
            String[] line = dato.split(",");
            Auto a = new Auto(line[0], line[1], line[2], line[3], Integer.valueOf(line[4]), Double.parseDouble(line[5]), Integer.valueOf(line[6]), line[7], line[8]);
            autos.add(a);
        }
        return autos;
    }
}
