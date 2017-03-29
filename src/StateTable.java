import java.io.*;
import java.math.*;
import java.util.ArrayList;

/**
 * This class represents the initial state table.
 */
public class StateTable {
    private int numStates;
    private int numInputs;

    private String[] states;

    private String[][] table;

    private String[] output;

    /**
     * constructor for a StateTable.
     *
     * @param numStates - number of states the table has
     * @param numInputs - the number of inputs the table has
     * @param initData  - initial data to populate the state table
     * @param states    - string array of state names
     * @param output    - outputs of each state of the statetable
     */
    public StateTable(int numStates, int numInputs, String[][] initData, int[] output, String[] states) {

        this.numStates = numStates;
        this.numInputs = numInputs;

        this.states = new String[numStates];

        System.arraycopy(states, 0, this.states, 0, numStates);

        this.table = new String[numStates][numInputs];

        System.arraycopy(initData, 0, this.table, 0, initData.length);

        this.output = new String[numStates];

        System.arraycopy(output, 0, this.output, 0, output.length);


    }

    /**
     * creates a new statetable from a file
     *
     * @param file - the file to create the statetable from
     */
    public StateTable(String file) {

        ArrayList<String> lines = new ArrayList<String>();

        try {
            FileInputStream in = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }



        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        this.numInputs = Integer.parseInt(lines.get(0).substring(10));
        this.numStates = Integer.parseInt(lines.get(1).substring(10));
        this.states = lines.get(2).substring(7).split(" ");
        this.output = lines.get(3).substring(8).split(" ");

        this.table = new String[this.numStates][(int)Math.pow(this.numInputs,2)];

        for(int i = 5; i<this.numStates+5;i++)
            table[i-5] = lines.get(i).split(" ");


    }


    public String toString(){
        String toReturn = "";

        toReturn+="Number of inputs: "+this.numInputs+"\n";
        toReturn+="Number of states: "+this.numStates+"\n";
        toReturn+="States: ";
        for(String state: states)
            toReturn+=state;
        toReturn += "\n";

        toReturn+="Outputs: ";
        for(String output: this.output)
            toReturn+=output;
        toReturn+="\n";

        toReturn+="The Table: \n";
        for(int i = 0; i< table.length;i++){
            for(int j = 0; j< table[0].length;j++){
                toReturn+=(table[i][j]+" ");
            }
            toReturn+="\n";
        }

        return toReturn;
    }


}
