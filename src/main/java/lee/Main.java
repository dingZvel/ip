package lee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Lee using FXML.
 */
public class Main extends Application {

    private final Lee lee = new Lee("./data/taskList.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Lee");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLee(lee);  // inject the Lee instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
