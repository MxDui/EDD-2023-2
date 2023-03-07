package mx.unam.ciencias.edd.proyecto1;

import java.io.IOException;

public class ArgumentParser {
    private boolean reverseOrder = false;
    private boolean outputFile = false;
    private boolean ignoreCase = false;
    private boolean unique = false;
    private Lista<String> fileNames = new Lista<>();

    public ArgumentParser(String[] args) {
        try {
            parse(args);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void parse(String[] args) throws IOException {
        for (String arg : args) {
            switch (arg) {
                case "-r":
                    reverseOrder = true;
                    break;
                case "-o":
                    outputFile = true;
                    break;
                case "-f":
                    ignoreCase = true;
                    break;
                case "-u":
                    unique = true;
                    break;
                default:
                    fileNames.agrega(arg);
                    break;
            }
        }

        SortUtils.sort(fileNames, reverseOrder, outputFile, ignoreCase, unique);
    }

    public boolean isReverseOrder() {
        return reverseOrder;
    }

    public boolean isOutputFile() {
        return outputFile;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public boolean isUnique() {
        return unique;
    }

    public Lista<String> getFileNames() {
        return fileNames;
    }

}
