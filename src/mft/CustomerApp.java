package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("view/customerForm.fxml"))
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Form");
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Main");
        launch();
    }
}
