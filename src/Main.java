import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        String file = args[0];

        StateTable st = new StateTable(file);

        System.out.print(st.toString());

        System.out.println(st.getNextState("A","10"));
        System.out.println(st.getOutput("M"));
    }

}
