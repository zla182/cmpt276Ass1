import Model.Lens;
import Model.LensManager;
import UI.CameraTextUI;

    /**
     * Launch application
     */
    public class Main {
        public static void main(String arg2s[]) {
            LensManager manager = new LensManager();
            CameraTextUI ui = new CameraTextUI(manager);
            ui.show();
        }
    }
