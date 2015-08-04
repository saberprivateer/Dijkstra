import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by D on 8/3/2015.
 */
public class Main {

    public static int[] X;
    public static int[] A;
    public static ArrayList<Integer>[] graph;
    public static ArrayList<Integer>[] graphLength;
    public static boolean explored = false;


    public static void log(Object args) {
        System.out.println(args.toString());
    }

    public static void shortPath() {
        int 
        while (!explored) {
            explored = true;
            for (int i=0; i<X.length;i++){
                if(X[i]>=0) {
                    for (int j=0;j<graph[i].size();j++){
                        if (X[graph[i].get(j)]<0){

                        }
                    }
                }
            }
        }
    }

    public static void init() {
        X = new int[graph.length];
        A = new int[graph.length];
        X[0]=0;
        A[0]=0;
        for (int i= 1;i<X.length;i++) {
            X[i]=-1;
            A[i]=-1;
        }
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        log("Begin Program");
        log("End Program");
        parseData("4node");
        init();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        log("Program ran for " + duration / 1000000 + " milliseconds");
    }

    public static void parseData(String fileName) throws IOException {
        String[] arr = data(fileName);
        graph = new ArrayList[arr.length];
        graphLength = new ArrayList[arr.length];
        Scanner sc;
        int n1;
        int n2;
        for (int i = 0;i<arr.length;i++) {
            graph[i] = new ArrayList<Integer>(1);
            graphLength[i] = new ArrayList<Integer>(1);
            sc = new Scanner(arr[i]);
            n1 = sc.nextInt();
            sc.useDelimiter("\\D+");
            while (sc.hasNextInt()){
                n1 = sc.nextInt();
                n2 = sc.nextInt();
                graph[i].add(n1);
                graphLength[i].add(n2);
            }
        }
    }

    public static String[] data(String datafile) throws IOException {
        //System.out.println("Opening " + datafile + ".");
        //Name of the file
        String filePath = new File("").getAbsolutePath();
        String fullPath = filePath + "/src/Files/" + datafile + ".txt";

        LineNumberReader lnr = new LineNumberReader(new FileReader(new File(fullPath)));
        lnr.skip(Long.MAX_VALUE);
        //Add 1 because line index starts at 0
        // Finally, the LineNumberReader object should be closed to prevent resource leak
        String[] arr = new String[lnr.getLineNumber() + 1];
        lnr.close();
        try {
            //Create object of FileReader
            FileReader inputFile = new FileReader(filePath + "/src/Files/" + datafile + ".txt");
            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);
            //Variable to hold the one line data
            String line;
            int i;
            i = 0;
            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null) {
                arr[i] = line;
                i++;
            }
            //Close the buffer reader
            bufferReader.close();
        } catch (Exception e) {
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }

        return arr;
    }
}
