import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**  ExperimentController performs various experiments of time complexity 
 *   @author Robson Adem
 */
public class ExperimentController
{

    /**
     * Constructor for objects of class ExperimentController
     */

    public ExperimentController(){
    }

    public static void main(String [] args)throws FileNotFoundException
    {
        ExperimentController e=new ExperimentController();
        PrintWriter pw = new PrintWriter(new File("output.csv"));
        PhoneContainer[] containers={new ArrayListPhoneContainer(),new RedBlackTreePhoneContainer( ),
                new HashSetPhoneContainer()};
        int i=0;
        while(i<containers.length){
            e.run(containers[i]);
            i++;
        }

    }

    public void run(PhoneContainer p) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter(new File("output.csv"));
        int [] operations={100000,200000, 300000, 400000, 500000,1000000 };
        double[] selectPer={0.01,0.001,0.0001};
        long addTime=0; long selectTime=0; long avgAdd; long avgSelect;
        for(int s=0;s<selectPer.length;s++){
            for(int op=0;op<operations.length;op++){
                //for a given data calculate the average taking five simulations
                int j=0;
                while(j<5){
                    Simulation sim=new Simulation(operations[op],selectPer[s]);
                    sim.run(p);
                    addTime+=sim.getAddTime();
                    selectTime+=sim.getSelectTime();
                    j++;
                }
                avgAdd=addTime/5;
                avgSelect=selectTime/5;
                StringBuilder sb = new StringBuilder();
                //outputting to cols and rows in excel file
                sb.append(selectPer[s]);
                sb.append(',');
                sb.append(operations[op]);
                sb.append(',');
                sb.append(avgAdd);
                sb.append(',');
                sb.append(avgSelect);
                sb.append('\n');
                System.out.println(selectPer[s]+" , "+operations[op]+" , "+avgAdd+" , "+avgSelect);
                pw.write(sb.toString());
                addTime=0;selectTime=0;
            }
        }

        pw.close();
        System.out.println("---------Experiments have been processed. Find data at output.csv ----------");
    }
}


