package ForkJoin;


public class DataStructure2 {
    private int int_val;
    private int sensorid;
    private boolean avg;
    private boolean mul;
    private boolean add;

// constructor
public DataStructure2(int int_val, int sensorid, boolean avg , boolean mul, boolean add) {
   this.int_val = int_val;
   this.sensorid = sensorid;
   this.avg = avg;
   this.mul = mul;
   this.add = add;
}

    // getter
    public int getint_val() { return int_val; }
    public int getsensorid() { return sensorid; }
    public boolean getavg() { return avg; }
    public boolean getmul() { return mul; }
    public boolean getadd() { return add; }
    // setter

    public void setint_val(int int_val) { this.int_val = int_val; }
    public void setsensorid(int sensorid) { this.sensorid = sensorid; }
    public void setavg(boolean avg) { this.avg = avg; }
    public void setmul(boolean mul) { this.mul = mul; }
    public void setadd(boolean add) { this.add = add; }
 }
