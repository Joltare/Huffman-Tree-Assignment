/**********************************************************
* @author  Richard Connolly
*          Dillon Feehan
* @version 1.0
* @since   2018-11
* Notes in README document in src folder of this project.
**********************************************************/

package com.huffenco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/****************************The Main loads up the main scene & sets the window size**************************/
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interface.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1100, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Huffman Encoding By Richard Connolly & Dillon Feehan.");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
