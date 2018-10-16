package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller implements Initializable {


    @FXML
    public TextField nb_element;
    public TextField poids_max;
    public Button submit;
    public HBox element;
    public HBox poids;
    public HBox valeur;
    public HBox tab;




    @FXML
    public void nom(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        submit.getText();
        final int[][][] element_array = new int[1][1][1];
        final int[] nombre_element = new int[1];

        // remplir le tableau des elements


        nb_element.setOnAction(event -> {
            nombre_element[0] = Integer.parseInt(nb_element.getText())+1;
            element_array[0] =new int[nombre_element[0]][2];
            (element_array[0])[0][0]=0;
            (element_array[0])[0][1]=0;
            for (int i = 1; i< nombre_element[0]; i++){
                nb_element.setDisable(true);


                TextField ee= new TextField();
                ee.setText(i+1+"  ");
                ee.setPrefWidth(40);
                ee.setPrefHeight(20);
                ee.setDisable(true);
                element.getChildren().add(ee);

                TextField pp= new TextField();
                int finalI = i;
                pp.setOnAction(event1 ->
                {
                    element_array[0][finalI][0]= Integer.parseInt(pp.getText());
                    pp.setDisable(true);
                }
                );
                pp.setPrefWidth(40);
                pp.setPrefHeight(20);
                poids.getChildren().add(pp);

                TextField vv= new TextField();
                vv.setPrefWidth(40);
                vv.setPrefHeight(20);
                vv.setOnAction(event1 ->
                {
                    element_array[0][finalI][1]= Integer.parseInt(vv.getText());
                    vv.setDisable(true);
                }
                );
                valeur.getChildren().add(vv);




            }


        });


        poids_max.setOnAction(event ->
        {
            int w=0;
            w= Integer.parseInt(poids_max.getText())+1;
            int resultat[][]=new int[nombre_element[0]][w];

            int i=0,j=0;

            for(i=0;i<nombre_element[0];i++) {resultat[i][0]=0;}
            for(j=0;j<w;j++) {resultat[0][j]=0;}

            // remplir la table P(i,j)


            for(j=1;j<w;j++)
            {

                for(i=1;i<nombre_element[0];i++)
                {
                    if(j<(element_array[0])[i][0]){resultat[i][j]=resultat[i-1][j];}
                    else
                    {
                        int v_max;
                        if(resultat[i-1][j]>resultat[i-1][j-((element_array[0])[i][0])]){v_max=resultat[i-1][j];}
                        else {v_max=resultat[i-1][j-((element_array[0])[i][0])]+((element_array[0])[i][1]);}
                        resultat[i][j]=v_max;
                    }
                }
            }




            for(i=0;i<nombre_element[0];i++)
            {
                VBox tabv=new VBox();

                for(j=0;j<w;j++)
                {

                    TextArea tt=new TextArea(resultat[i][j]+"");
                    tt.setPrefWidth(40);
                    tt.setPrefHeight(20);
                    tabv.getChildren().add(tt);
                }

                tab.getChildren().add(tabv);
            }






        });









    }

}
