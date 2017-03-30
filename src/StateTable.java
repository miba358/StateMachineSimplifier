import java.io.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents the initial state table.
 */
public class StateTable {
    private int numStates; // number of states in the state machine
    private int numInputs; // number of input bits

    private String[] states; // array of the string representations of each state

    private String[][] table; // 2d array of the state table

    private String[] output; // output array corresponding to each state

    private String[] valid_input;

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

        // initializes fields
        this.numStates = numStates;
        this.numInputs = numInputs;
        this.states = new String[numStates];

        setValidInput();

        // copies entries of states array into the states field
        System.arraycopy(states, 0, this.states, 0, numStates);

        // initializes state table
        this.table = new String[numStates][numInputs];

        // copies entries of the table matrix into the state matrix
        System.arraycopy(initData, 0, this.table, 0, initData.length);

        // initializes output array
        this.output = new String[numStates];

        // copies entires of the output array into the output array state
        System.arraycopy(output, 0, this.output, 0, output.length);


    }


    /**
     * creates a new statetable from a file
     *
     * @param file - the file to create the statetable from
     */
    public StateTable(String file) {

        // arraylist which is populated with lines of the file as strings
        ArrayList<String> lines = new ArrayList<String>();


        // attempt to load file
        try {
            FileInputStream in = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // add line entries to the arraylist
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }


            // if cannot read file
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        // set state fields to there respective values
        this.numInputs = Integer.parseInt(lines.get(0).substring(10));
        this.numStates = Integer.parseInt(lines.get(1).substring(10));
        this.states = lines.get(2).substring(7).split(" ");
        this.output = lines.get(3).substring(8).split(" ");

        setValidInput();

        // initializes the table matrix
        this.table = new String[this.numStates][(int)Math.pow(this.numInputs,2)];

        // sets values of table matrix
        for(int i = 5; i<this.numStates+5;i++)
            table[i-5] = lines.get(i).split(" ");


    }


    /**
     * initializes the valid_input field with all the possible
     * input representations
     */
    private void setValidInput(){
        int numInputBits = this.numInputs;
        int numSelections = (int)Math.pow(numInputBits, 2);

        this.valid_input = new String[numSelections];

        for (int i = 0; i<numSelections;i++){
            this.valid_input[i] = Integer.toBinaryString(i);
        }

        for(int i = 0; i<numSelections; i++){
            while(this.valid_input[i].length() < numInputBits){
                this.valid_input[i] = "0"+this.valid_input[i];
            }
        }

    }

    /**
     * @return string representation of a StateTable
     */
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

    /**
     * gets the next state of the current state based on input
     * @param currentState - current state
     * @param input - the input in binary representation
     * @return - the next state based on the inputs
     */
    public String getNextState(String currentState, String input){

        int numState = Arrays.asList(this.states).indexOf(currentState);
        int numCol = Arrays.asList(this.valid_input).indexOf(input);

        return this.table[numState][numCol];

    }

    /**
     * @param state - the state to get the output of
     * @return - the output value of the provided state
     */
    public String getOutput(String state){
        int numState = Arrays.asList(this.states).indexOf(state);

        return this.output[numState];

    }


    public String[][] getInitialPartition(){
        for(String state: this.table[0]){

        }
        return null;
    }

    /**
     * @return number of input bits
     */
    public int getNumInputs(){
        return this.numInputs;
    }

    /**
     * @return list of every possible valid input
     */
    public String[] getValid_input(){
        return this.valid_input;
    }

}
