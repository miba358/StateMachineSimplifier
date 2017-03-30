import java.io.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Matthew Toro on 3/29/2017.
 */
public class SubPartition {

    public ArrayList<String> states;


    public SubPartition(ArrayList<String> states, int numInputs){
        this.states = states;
    }

    public ArrayList<SubPartition> getSubPartitions(StateTable table, ArrayList<SubPartition> curPartition){
        ArrayList<SubPartition> newPartitions = new ArrayList<SubPartition>();


        String[] inputs = table.getValid_input();
        String curState;
        ArrayList<String> nextStates = new ArrayList<String>(states.size());

        for (int i = 0; i < inputs.length; i++){
            for (int s = 0; s < states.size(); s++) {
                curState = states.get(s);
                nextStates.add(s, table.getNextState(curState, inputs[i]));
            }

            for (int p = 0; p < curPartition.size(); p++){
                for (int s = 0; s < states.size(); s++){




                }
            }


        }

        return newPartitions;
    }

}
