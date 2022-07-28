package Model;

import Model.Lens;

public class DepthofFieldCalculator {
    private double HyperFocalDistance;
    private double NearFocalPoint;
    private double FarFocalPoint;
    private double DepthOfField;

    /*
     Hyperfocal distance
     With a given lens and camera settings, the hyperfocal distance is the distance from the camera
        beyond which all objects will seem in focus.
     Hyper focal distance [mm] = (lens focal length [mm])^2
                                                            /
                                                               (selected aperture * camera’s circle of confusion [mm])
     */
    public double getHyperFocalDistance() {
        return HyperFocalDistance;
    }

    /*
     Near focal point
        Near focal point is distance from the camera to the nearest point which will seem in focus.
        Near Focal Point [mm] = (hyper focal point [mm] * distance to subject [mm])
                                                                                    /
                                                                                        (hyper focal point [mm] + (distance to subject [mm] – lens focal length [mm]))
     */
    public double getNearFocalPoint() {
        return NearFocalPoint;
    }

    /*
     Far focal point
     Far focal point is distance from the camera to the farthest point which will seem in focus.
     If the subject is beyond the hyperfocal distance, then the far focal point is +Infinity
        (in Java use Double.POSITIVE_INFINITY).
     Far Focal Point [mm] = (hyper focal point [mm] * distance to subject [mm])
                                                                                /
                                                                                    (hyper focal point [mm] – (distance to subject [mm] – lens focal length [mm]))
     */
    public double getFarFocalPoint() {
        return FarFocalPoint;
    }

    /*
     Depth of field
     Depth of field [mm] = (far focal point [mm]) - (near focal point [mm])
     */
    public double getDepthOfField() {
        return DepthOfField;
    }

    public DepthofFieldCalculator(Lens lens, double distance, double aperture, double coCon) {

        if(lens.getMaxAperture() <= 0 ){
            System.out.println("there are something wrong with this len");
        }else{
            HyperFocalDistance = (((double) lens.getFocalLength()) * ((double) lens.getFocalLength()) / (aperture * coCon))/1000 ;
            NearFocalPoint=(HyperFocalDistance*distance)/(HyperFocalDistance+distance-(double)lens.getFocalLength()/1000);
            if(distance > HyperFocalDistance){
                FarFocalPoint = Double.POSITIVE_INFINITY;
            }else {
                FarFocalPoint=(HyperFocalDistance*distance)/(HyperFocalDistance-distance+(double)lens.getFocalLength()/1000);
            }
            DepthOfField = FarFocalPoint - NearFocalPoint;
        }

    }

}
