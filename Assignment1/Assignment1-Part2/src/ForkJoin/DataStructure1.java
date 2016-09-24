package ForkJoin;


public class DataStructure1 {
    private String binary_val;
    private int sensorid;
    private boolean avg;
    private boolean mul;
    private boolean add;

// constructor
public DataStructure1(String binary_val, int sensorid, boolean avg , boolean mul, boolean add) {
   this.binary_val = binary_val;
   this.sensorid = sensorid;
   this.avg = avg;
   this.mul = mul;
   this.add = add;
}

    // getter
    public String getbinary_val() { return binary_val; }
    public int getsensorid() { return sensorid; }
    public boolean getavg() { return avg; }
    public boolean getmul() { return mul; }
    public boolean getadd() { return add; }
    // setter

    public void setbinary_val(String binary_val) { this.binary_val = binary_val; }
    public void setsensorid(int sensorid) { this.sensorid = sensorid; }
    public void setavg(boolean avg) { this.avg = avg; }
    public void setmul(boolean mul) { this.mul = mul; }
    public void setadd(boolean add) { this.add = add; }
 }


