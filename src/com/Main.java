import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static int max;


    public static void log(Object args) {
        System.out.println(args.toString());
    }

    public static void nextPath() {
        int min = 1000000000;
        int minw = -1;
        int minv = -1;
        int source = -1;
        //for each vertex...
        for (int i=0;i<X.length;i++){
            //where the distance has been calculated...
            if(X[i]>0){
                //for each edge ij
                for (int j=0;j<graph[i].size();j++){
                    //where the distance hasn't been calculated...
//                    log("Has "+(i+1)+","+graph[i].get(j)+" been calculated?");
                    if(X[graph[i].get(j)-1]<0) {
                        if ((A[i]+graphLength[i].get(j))<min){
//                            log((i+1)+","+graph[i].get(j)+" has length "+graphLength[i].get(j));
                            min = A[i]+graphLength[i].get(j);
                            minv = j;
                            minw = graph[i].get(j);
                            source = i;
                        }
                    }
                }
            }
        }
        if (minw==-1){
            log("doesn't connect!");
            explored = true;
        }
        else {
            X[minw-1] = 1;
//            log("Source:"+source);
            A[minw-1] = A[source]+graphLength[source].get(minv);
//            log("X=" + Arrays.toString(X));
//            log("A=" + Arrays.toString(A));
            for (int i=0;i<X.length;i++){
                if (X[i]<0){explored=false;}
            }
        }
    }

    public static void shortPath() {
        int count=0;
        //While X!=V
        for (int i=0;i<X.length;i++) {
            explored = true;
            nextPath();
            count++;
        }
        log("Count:"+count);
    }

    public static void duplicate() {
//        for (int i=0;i<)
    }

    public static void init(int start) {
        X = new int[graph.length];
        A = new int[graph.length];
        for (int i= 0;i<X.length;i++) {
            X[i]=-1;
            A[i]=-1;
        }
        X[start-1]=1;
        A[start-1]=0;
    }

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        String readFile = "dijkstraData";
        log("Begin Program");
        log("End Program");
        parseData(readFile,0);
        init(1);
//        log("Max = "+max);
        shortPath();
        log("X:"+Arrays.toString(X));
        log("A:"+Arrays.toString(A));
        if(readFile.equals("dijkstraData")) {
            log("Distance to 7 = " + A[7 - 1]);
            log("Distance to 37 = " + A[37 - 1]);
            log("Distance to 59 = " + A[59 - 1]);
            log("Distance to 82 = " + A[82 - 1]);
            log("Distance to 99 = " + A[99 - 1]);
            log("Distance to 115 = " + A[115 - 1]);
            log("Distance to 133 = " + A[133 - 1]);
            log("Distance to 165 = " + A[165 - 1]);
            log("Distance to 188 = " + A[188 - 1]);
            log("Distance to 197 = " + A[197 - 1]);
        }
//        nextPath();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        log("Program ran for " + duration / 1000000 + " milliseconds");
    }

    public static void parseData(String fileName,int bump) throws IOException {
        max = 0;
        String[] arr = data(fileName);
        graph = new ArrayList[arr.length];
        graphLength = new ArrayList[arr.length];
        Scanner sc;
        int n1;
        int n2;
        for (int i = 0;i<arr.length;i++) {
//            log("i="+i+" and length="+arr.length+" and arr[i]="+arr[i]);
            graph[i] = new ArrayList<Integer>(1);
            graphLength[i] = new ArrayList<Integer>(1);
            sc = new Scanner(arr[i]);
            n1 = sc.nextInt();
            sc.useDelimiter("\\D+");
            while (sc.hasNextInt()){
                n1 = sc.nextInt()+bump;
                n2 = sc.nextInt();
                graph[i].add(n1);
                graphLength[i].add(n2);
                max = Math.max(n2,max);
            }
        }
        max++;
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
