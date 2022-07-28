package Model;

public class Lens {


    private String type;
    private double maxAperture;
    private int focalLength;

    public String getType() {
        return type;
    }

    public double getMaxAperture() {
        return maxAperture;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public Lens(String type, double maxAperture, int focalLength) {
        this.type = type;
        this.maxAperture = maxAperture;
        this.focalLength = focalLength;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMaxAperture(double maxAperture) {
        if(maxAperture < 0){
            return;
        }
        this.maxAperture = maxAperture;
    }

    public void setFocalLength(int focalLength) {
        if(focalLength < 0){
            return;
        }
        this.focalLength = focalLength;
    }

    public boolean isEmpty(){
        if(this.type == null || this.maxAperture == 0
                || this.focalLength == 0){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public String toString() {
        return   type + '\'' +
                 + focalLength + " mm " +
                " F" + maxAperture;
    }

}
