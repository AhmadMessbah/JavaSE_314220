package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProductApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("view/productForm.fxml"))
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Product Basket");
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Main");
        launch();
    }
}