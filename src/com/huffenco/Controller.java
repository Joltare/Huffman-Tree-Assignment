/**********************************************************
* @author  Richard Connolly
*          Dillon Feehan
* @version 1.0
* @since   2018-12
* Notes in README document in src folder of this project.
**********************************************************/

package com.huffenco;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.HashMap;

public class Controller {

/****************************FXML tags you use use in scenebuilder to build functionality***********************************************/
    @FXML
    private TextArea inputText;
    @FXML
    private TextArea encodedText;
    @FXML
    private Label entropy;
    @FXML
    private Label averageCodeword;
    @FXML
    private Canvas canvas;

    private static int gap = 30;
    private static int vGap = 50;
    private HuffTree tree;
  
/****************************Javafx handle for when the encode button is clicked***********************************************/
    @FXML protected void handleEncodeButtonAction(ActionEvent event) {
        String input = inputText.getText();
        tree = new HuffTree(input);
        HashMap<Character,String> map = tree.getHuffmanEncoding();
        System.out.println(map);

        int height = tree.getHeight();
        StringBuilder disp = new StringBuilder();
        for(int i=0; i<input.length(); i++) {
            disp.append(map.get(input.charAt(i)));
        }
        encodedText.setText(disp.toString());

/****************************This draws the bubbles and populates for the tree***********************************************/
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,2400,2400);
        gc.setStroke(Color.BLUE);
        gc.setFont(new Font("Dialog Bold",20));
        double begin = gap*Math.pow(2,(height-2));
        double dim = Math.pow(2,(height - 1)) * 30;
        HuffTreeCreater.display(tree.getRoot(), dim,10, gc, begin);

/****************************Calculations at the bottom of the Javafx window***********************************************/        
        
        entropy.setText(String.format("%.3f%n", tree.computeEntrpy(map, input)));
        averageCodeword.setText(String.format("%.3f%n", tree.computeAverageCodewordLength(map,input)));
    }
/****************************Javafx handle for when the decode button is clicked***********************************************/
    @FXML protected void handleDecodeButtonAction(ActionEvent event) {
        String input = encodedText.getText();
        HuffTreeDecoder decoder = new HuffTreeDecoder(tree, input);
        String disp = decoder.decode();
        inputText.setText(input+" is decoded as " + disp);
    }

}
