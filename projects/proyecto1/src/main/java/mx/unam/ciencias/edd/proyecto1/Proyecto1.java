package mx.unam.ciencias.edd.proyecto1;

/**
 * Hello world!
 *
 */
public class Proyecto1 {
    public static void main(String[] args) {
        ArgumentParser parser = new ArgumentParser(args);

        if (parser.getFileNames().esVacia()) {
            System.out.println("No se especificaron archivos de entrada.");
            return;
        }

    }
}
