package UI;

import Model.DepthofFieldCalculator;
import Model.Lens;
import Model.LensManager;

import java.text.DecimalFormat;
import java.util.Scanner;

public class CameraTextUI {
    private static final double coCon = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard

    public CameraTextUI(LensManager manager) {
        // Accept and store a reference to the lens manager (the model)
        // (this design is called "dependency injection")
        this.manager = manager;

        // Populate lenses (Make, max aperture (smallest supported F number), focal length [mm]):
        manager.addLenses(new Lens("Canon", 1.8, 50));
        manager.addLenses(new Lens("Tamron", 2.8, 90));
        manager.addLenses(new Lens("Sigma", 2.8, 200));
        manager.addLenses(new Lens("Nikon", 4, 200));
    }

    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    public void show() {
        System.out.println(" if you see 'Error: Invalid lens index' " +
                "that means your number is out of our capacity ");
        System.out.print("  (-1 to exit)\n: ");
        int choice;
        do {
            System.out.println(" our total number of lenses is "
                    + manager.totalLenses());
            System.out.println(" Lenses to pick from: ");
            manager.printAll();
            System.out.print("  (-1 to exit)\n: ");
            choice = in.nextInt();
            if (choice == -1) {
                System.out.println(" Exit Successfully");
                break;
            }

            if (choice < manager.totalLenses() && choice >= 0) {
                System.out.println(" Your choice is number " + choice + " and it is "
                        + manager.search(choice).toString()
                );
                System.out.println(" Now we wanner make sure that this len is you correct choice ");
                System.out.print("Aperture [the F number]: ");
                double ap = in.nextDouble();
                if (ap < manager.search(choice).getMaxAperture()) {
                    System.out.println(" ERROR: This aperture is not possible with this lens ");
                    System.out.println(" Please change the lens' number");
                    System.out.println(" Exit  because of the error");
                    break;
                } else {
                    System.out.print("Distance to subject [m]: ");
                    double dis = in.nextDouble();
                    DepthofFieldCalculator calculateor = new DepthofFieldCalculator(manager.search(choice), dis, ap, coCon);
                    System.out.println("  In focus: "+ formatM(calculateor.getNearFocalPoint()) + 'm' +
                            " to " + formatM(calculateor.getFarFocalPoint()) + 'm' +
                            " [DoF = "  + formatM(calculateor.getDepthOfField()) + 'm' +
                            "]\n  Hyperfocal point: " + formatM(calculateor.getHyperFocalDistance()) + 'm') ;
                    System.out.println(" If you want you to exit please enter -1 now ");
                    System.out.println(" Continue:  enter any other number except -1 ");
                    choice = in.nextInt();
                }
            } else {
                System.out.println(" Error: Invalid lens index ");
                break;
            }
            System.out.println('\n');
        } while (choice != -1);

    }
}


