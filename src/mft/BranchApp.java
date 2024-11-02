package mft;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

@Log4j
public class BranchApp extends Application {

    public static void main(String[] args) {
        log.debug("App Started");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(
                FXMLLoader.load(getClass().getResource("view/branchForm.fxml"))
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("Branch Basket");
        primaryStage.show();
    }
}
