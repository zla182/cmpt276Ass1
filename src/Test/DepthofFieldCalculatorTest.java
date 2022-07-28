package Test;

import Model.DepthofFieldCalculator;
import Model.Lens;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DepthofFieldCalculatorTest {

    Lens l1 = new Lens("Canon", 1.8, 50);
    Lens l2 = new Lens("Tamron", 2.8, 90);
    Lens l3 = new Lens("Sigma", 2.8, 200);
    Lens l4 = new Lens("Nikon", 4, 200);
    final double coCon = 0.029;    // "Circle of Confusion" for a "Full Frame" camera


    @Test
    /*
        With a given lens and camera settings, the hyperfocal distance is the distance from the camera beyond which all objects will seem in focus.
        Hyper focal distance [mm] = (lens focal length [mm])^2/
                                                               (selected aperture * camera’s circle of confusion [mm])
     */
    void getHyperFocalDistance() {

        DepthofFieldCalculator D1 = new DepthofFieldCalculator(l1, 7, 1.8, coCon);
        assertEquals(((50 * 50) / (1.8 * coCon))/1000, D1.getHyperFocalDistance());

        DepthofFieldCalculator D2 = new DepthofFieldCalculator(l2, 1, 2.6, coCon);
        assertEquals(((90 * 90) / (2.6 * coCon))/1000 , D2.getHyperFocalDistance());

        DepthofFieldCalculator D3 = new DepthofFieldCalculator(l3, 6, 3, coCon);
        assertEquals(((200 * 200) / (3 * coCon))/1000 , D3.getHyperFocalDistance());

        DepthofFieldCalculator D4 = new DepthofFieldCalculator(l4, 2, 2, coCon);
        assertEquals(((200 * 200) / (2 * coCon))/1000 , D4.getHyperFocalDistance());

    }

    @Test
    /*
        Near focal point is distance from the camera to the nearest point which will seem in focus.
        Near Focal Point [mm] = (hyper focal point [mm] * distance to subject [mm])
                                                                                    /
                                                                                      (hyper focal point [mm] + (distance to subject [mm] – lens focal length [mm]))
     */
    void getNearFocalPoint() {
        DepthofFieldCalculator D1 = new DepthofFieldCalculator(l1, 7, 1.8, coCon);
        double n1 = D1.getHyperFocalDistance();
        assertEquals((n1*7)/(n1+(7-50.0/1000)),D1.getNearFocalPoint(),0.001);

        DepthofFieldCalculator D2 = new DepthofFieldCalculator(l2, 1, 2.6, coCon);
        double n2 = D2.getHyperFocalDistance();
        assertEquals((n2 * 1) / (n2 + ( 1 - 90.0/1000)) , D2.getNearFocalPoint(),0.001);

        DepthofFieldCalculator D3 = new DepthofFieldCalculator(l3, 6, 3, coCon);
        double n3 = D3.getHyperFocalDistance();
        assertEquals((n3 * 6) / (n3 + ( 6 - 200.0/1000)) , D3.getNearFocalPoint(),0.001);

        DepthofFieldCalculator D4 = new DepthofFieldCalculator(l4, 2, 2, coCon);
        double n4 = D4.getHyperFocalDistance();
        assertEquals((n4 * 2) / (n4 + ( 2 - 200.0/1000)) , D4.getNearFocalPoint(),0.001);

    }



    @Test
    /*
        Far focal point is distance from the camera to the farthest point which will seem in focus.
        If the subject is beyond the hyperfocal distance, then the far focal point is +Infinity(in Java use Double.POSITIVE_INFINITY).
        Far Focal Point [mm] = (hyper focal point [mm] * distance to subject [mm])
                                                                                  /
                                                                                    (hyper focal point [mm] – (distance to subject [mm] – lens focal length [mm]))
     */
    void getFarFocalPoint() {
        DepthofFieldCalculator D1 = new DepthofFieldCalculator(l1, 1.5, 1.8, coCon);
        double n1 = D1.getHyperFocalDistance();
        assertEquals(((n1 * 1.5)) / (n1 - ( 1.5 - 50.0/1000)), D1.getFarFocalPoint(),0.001);

        DepthofFieldCalculator D2 = new DepthofFieldCalculator(l2, 1, 2.6, coCon);
        double n2 = D2.getHyperFocalDistance();
        assertEquals((n2 * 1) / (n2 - ( 1 - 90.0/1000)), D2.getFarFocalPoint(),0.001);

        DepthofFieldCalculator D3 = new DepthofFieldCalculator(l3, 1.8, 3, coCon);
        double n3 = D3.getHyperFocalDistance();
        assertEquals((n3 * 1.8) / (n3 - ( 1.8 - 200.0/1000)) , D3.getFarFocalPoint(),0.001);

        DepthofFieldCalculator D4 = new DepthofFieldCalculator(l4, 2, 2, coCon);
        double n4 = D4.getHyperFocalDistance();
        assertEquals((n4 * 2) / (n4 - ( 2 - 200.0/1000)), D4.getFarFocalPoint(),0.001);

        //this one test  distance greater than HyperFocalDistance
        DepthofFieldCalculator D5 = new DepthofFieldCalculator(l4,1000.0,2,coCon);
        assertEquals(Double.POSITIVE_INFINITY,D5.getFarFocalPoint());

    }

    @Test
    /*
        Depth of field [mm] = (far focal point [mm]) - (near focal point [mm])
     */
    void getDepthOfField() {
        DepthofFieldCalculator D1 = new DepthofFieldCalculator(l1, 7, 1.8, coCon);
        double n1 = D1.getHyperFocalDistance();
        assertEquals(((n1 * 7)) / (n1 - ( 7 - 50.0/1000))-((n1 * 7)) / (n1 + ( 7 - 50.0/1000)),D1.getDepthOfField(),0.001);

        DepthofFieldCalculator D2 = new DepthofFieldCalculator(l2, 1, 2.6, coCon);
        double n2 = D2.getHyperFocalDistance();
        assertEquals((n2 * 1) / (n2 - ( 1 - 90.0/1000))-(n2 * 1) / (n2 + ( 1 - 90.0/1000)),D2.getDepthOfField(),0.001);

        DepthofFieldCalculator D3 = new DepthofFieldCalculator(l3, 6, 3, coCon);
        double n3 = D3.getHyperFocalDistance();
        assertEquals((n3 * 6) / (n3 - ( 6 - 200.0/1000))-((n3 * 6) / (n3 + ( 6 - 200.0/1000))),D3.getDepthOfField(),0.001);

        DepthofFieldCalculator D4 = new DepthofFieldCalculator(l4, 2, 2, coCon);
        double n4 = D4.getHyperFocalDistance();
        assertEquals((n4 * 2) / (n4 - ( 2 - 200.0/1000))-((n4 * 2) / (n4 + ( 2 - 200.0/1000))),D4.getDepthOfField(),0.001);

    }

}
