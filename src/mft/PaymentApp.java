package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

public class PaymentApp {


    @Log4j
    public static class paymentApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            Scene scene = new Scene(
                    FXMLLoader.load(getClass().getResource("view/PaymentView.fxml"))
            );

            primaryStage.setScene(scene);
            primaryStage.setTitle("Payment Basket");
            primaryStage.show();
        }

        public static void main(String[] args) {
            log.debug("Application started");
            launch();
        }
    }


}
