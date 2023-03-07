package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SortUtils {
    public static void sort(Lista<String> files, boolean reverseOrder, boolean numericSort, boolean ignoreCase,
            boolean unique) throws IOException {

        if (files.esVacia()) {
            return;
        }

        if (reverseOrder) {
            reverseOrder(files);

        } else {
            normalOrder(files, numericSort, ignoreCase, unique);
        }

    }

    private static void reverseOrder(Lista<String> files) throws IOException {

        Lista<Record> list = new Lista<Record>();

        for (String file : files) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            while (line != null) {
                list.agrega(new Record(line));
                line = br.readLine();
            }
            br.close();
        }
        list.mergeSort((a, b) -> a.getLine().trim().replaceAll("[^a-zA-Z]", "").toLowerCase()
                .compareTo(b.getLine().replaceAll("[^a-zA-Z]", "").toLowerCase()));

        // ouput the list in reverse order with a buffered writer
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));

        for (Record record : list.reversa()) {
            bw.write(record.getLine());
            bw.newLine();
        }

        bw.close();
    }

    private static void normalOrder(Lista<String> files, boolean numericSort, boolean ignoreCase, boolean unique)
            throws IOException {
        Lista<String> lines = new Lista<String>();

        // Read all lines from all files into a single list.
        for (String file : files) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            while (line != null) {
                lines.agrega(line);
                line = br.readLine();
            }
            br.close();
        }

        // Sort the list.
        if (numericSort) {
            lines.mergeSort((a, b) -> {
                try {
                    return Integer.parseInt(a) - Integer.parseInt(b);
                } catch (NumberFormatException e) {
                    return a.compareTo(b);
                }
            });
        } else {
            lines.mergeSort((a, b) -> {
                if (ignoreCase) {
                    return a.toLowerCase().compareTo(b.toLowerCase());
                }
                return a.compareTo(b);
            });
        }

        // Remove duplicates if needed.

        if (unique) {
            Lista<String> uniqueLines = new Lista<String>();
            String last = null;
            for (String line : lines) {
                if (last == null || !line.equals(last)) {
                    uniqueLines.agrega(line);
                }
                last = line;
            }
            lines = uniqueLines;
        }

        // ouput the list with a buffered writer
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")));

        for (String line : lines) {
            bw.write(line);
            bw.newLine();
        }

        bw.close();

    }
}
