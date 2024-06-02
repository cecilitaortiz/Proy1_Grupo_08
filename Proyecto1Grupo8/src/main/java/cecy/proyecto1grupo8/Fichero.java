package cecy.proyecto1grupo8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Fichero {


    public static ArrayList<String> leer(String ruta, boolean saltarPrimeraLinea) {
        ArrayList<String> lineas = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;

            if (saltarPrimeraLinea) {
                reader.readLine();
            }

            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException ex) {
            System.err.println("No se pudo leer el archivo " + ruta);
        }

        return lineas;
    }

   
    public static ArrayList<String> leer(String ruta) {
        return leer(ruta, false);
    }


    public static ArrayList<String> leerSinCabecera(String ruta) {
        return leer(ruta, true);
    }

 
    public static boolean escribir(String ruta, String linea) {

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(linea + "\n");
        } catch (IOException ex) {
            System.err.println("No se pudo escribir el archivo " + ruta);
            return false;
        }

        return true;
    }

    public static ArrayList<Auto> cargarAutos() {

        ArrayList<Auto> autos = new ArrayList<>();
        ArrayList<String> datos = Fichero.leer("src/main/resources/archivos/autos.txt");
        for (String dato : datos) {
            String[] line = dato.split(",");
            Auto a = new Auto(line[0],line[1],line[2],line[3],Integer.valueOf(line[4]),Double.valueOf(line[5]),Integer.valueOf(line[6]),line[7],line[8]);
            autos.add(a);
        }
        return autos;
    }
}
