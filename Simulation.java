import java.util.Random;
import java.util.concurrent.*;
/**
 * This class simulates addition and selection of phone numbers to/from 
 * the tree phone containers
 * @author Robson Adem 
 */
public class Simulation
{
    private PhoneContainer p;
    private int selectCount;
    private int addCount;
    private long selectTime;
    private long addTime;
    /**
     * Constructor for objects of class Simulation
     */
    public Simulation( int operations,double selectPercentage)
    {
        selectCount=(int)(operations*selectPercentage);
        addCount=operations-selectCount;
    }

    /**
     * A method to run the simulation 
     * @param   PhoneContainer P
     * */
    public void run(PhoneContainer p)
    {
        this.p=p;
        Random r=  new Random();
        long phoneNumber;
        long range=999999999-111111111;
        long startTimeAdd= System.currentTimeMillis();
        for(int i=0; i<addCount ;i++){
            phoneNumber= (long)(r.nextDouble()*1000000000l);
            p.add(phoneNumber);
            //System.out.println("Phone # "+phoneNumber+"was added to the lottery pool");
        }
        long endTimeAdd= System.currentTimeMillis();
        addTime=endTimeAdd-startTimeAdd;
        int kth;
        long startTimeSelect= System.currentTimeMillis();
        for(int i=0; i<selectCount ;i++){
            //select an itme between 1 and the addCount
            kth=r.nextInt(addCount)+1;
            p.select(kth);
           // System.out.println("The "+kth+"th smallest element was picked");
        }
        long endTimeSelect= System.currentTimeMillis();
        selectTime=endTimeSelect-startTimeSelect;
    }
    
    /**
     * A method to return the execution time to add to a list
     * @return  long addTime 
     */
    public long getAddTime()
    {
        return addTime;
    }
    
    /**
     * A method to return the execution time to select to a list
     * @return  long selectTime 
     */
    public long getSelectTime()
    {
        return selectTime;
    }

}
