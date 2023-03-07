package mx.unam.ciencias.edd.proyecto1;

import java.io.IOException;

public class ArgumentParser {
    private boolean reverseOrder = false;
    private boolean numericSort = false;
    private boolean ignoreCase = false;
    private boolean unique = false;
    private Lista<String> fileNames = new Lista<>();

    public ArgumentParser(String[] args) {
        try {
            parse(args);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void parse(String[] args) throws IOException {
        for (String arg : args) {
            switch (arg) {
                case "-r":
                    reverseOrder = true;
                    // TODO: Implementar -r

                    break;
                case "-o":
                    numericSort = true;
                    // TODO: Implementar -o

                    break;
                case "-f":

                    // TODO: Implementar -f
                    ignoreCase = true;
                    break;
                case "-u":
                    // TODO: Implementar -u
                    unique = true;
                    break;
                default:
                    fileNames.agrega(arg);
                    break;
            }
        }

        SortUtils.sort(fileNames, reverseOrder, numericSort, ignoreCase, unique);
    }

    public boolean isReverseOrder() {
        return reverseOrder;
    }

    public boolean isNumericSort() {
        return numericSort;
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
