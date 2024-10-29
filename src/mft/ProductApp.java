package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

@Log4j
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
        log.debug("Application started");
        launch();
    }
}
