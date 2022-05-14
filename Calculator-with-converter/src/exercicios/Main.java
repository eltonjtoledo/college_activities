package exercicios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL caminhoView = getClass().getResource("/exercicios/calculadora.fxml");
        GridPane primeiraTela = FXMLLoader.load(caminhoView);

        Scene tela1 = new Scene(primeiraTela, 400, 570, Color.TRANSPARENT);
        stage.setTitle("Minha Calculadora");
        stage.setOpacity(0.95);
        stage.setScene(tela1);
        stage.show();
    }
}
