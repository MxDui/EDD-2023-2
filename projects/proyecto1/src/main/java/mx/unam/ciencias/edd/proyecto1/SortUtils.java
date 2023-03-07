package mx.unam.ciencias.edd.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
            reverseOrder(files, unique);

        } else {
            normalOrder(files, numericSort, ignoreCase, unique);
        }

    }

    private static void reverseOrder(Lista<String> files, boolean unique)
            throws IOException {

        Lista<Record> list = new Lista<Record>();
        Lista<Record> orderedList = new Lista<Record>();

        for (String file : files) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            while (line != null) {
                list.agrega(new Record(line));
                line = br.readLine();
            }
            br.close();
        }
        orderedList = list.mergeSort((a, b) -> a.getLine().trim().replaceAll("[^a-zA-Z]", "").toLowerCase()
                .compareTo(b.getLine().replaceAll("[^a-zA-Z]", "").toLowerCase()));

        // ouput the list in reverse order with a buffered writer
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(files.getUltimo())));

        // remove duplicates if needed
        if (unique) {
            Lista<Record> uniqueLines = new Lista<Record>();
            Record last = null;
            for (Record line : orderedList) {
                if (last == null || !line.getLine().equals(last.getLine())) {
                    uniqueLines.agrega(line);
                }
                last = line;
            }
            orderedList = uniqueLines;
        }

        for (Record record : orderedList.reversa()) {
            System.out.println(record.getLine());
        }

        bw.close();
    }

    private static void normalOrder(Lista<String> files, boolean outputFile, boolean ignoreCase, boolean unique)
            throws IOException {
        Lista<Record> list = new Lista<Record>();
        Lista<Record> orderedList = new Lista<Record>();

        for (String file : files) {

            if (outputFile && file.equals(files.getPrimero())) {
                continue;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            while (line != null) {
                list.agrega(new Record(line));
                line = br.readLine();
            }
            br.close();
        }
        orderedList = list.mergeSort((a, b) -> a.getLine().trim().replaceAll("[^a-zA-Z]", "").toLowerCase()
                .compareTo(b.getLine().replaceAll("[^a-zA-Z]", "").toLowerCase()));

        // Remove duplicates if needed.
        if (unique) {
            Lista<Record> uniqueLines = new Lista<Record>();
            Record last = null;
            for (Record line : orderedList) {
                if (last == null || !line.getLine().trim().equals(last.getLine().trim())) {
                    uniqueLines.agrega(line);
                }
                last = line;
            }
            orderedList = uniqueLines;
        }

        // ouput the list with a buffered writer

        if (outputFile) {

            File file = new File(files.getPrimero());

            if (!file.exists()) {
                try {
                    file.createNewFile();
                    System.out.println("Created new file: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Error creating filesssSSS: " + e.getMessage());
                }
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            for (Record record : orderedList) {
                bw.write(record.getLine());
                bw.newLine();
            }

            bw.close();
        } else {
            for (Record record : orderedList) {
                System.out.println(record.getLine());
            }
        }

    }
}
