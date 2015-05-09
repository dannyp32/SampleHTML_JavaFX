package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        WebView webView = new WebView();
        String url = getClass().getResource("test.html").toExternalForm();

        webView.getEngine().load(url);
        borderPane.setCenter(webView);
        final Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(768);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}