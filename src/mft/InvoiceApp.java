package invoice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InvoiceApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("view/invoiceForm.fxml"))
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Invoice App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Main");
        launch();
    }
}
