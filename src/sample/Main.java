package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test Tool");
        Group root = new Group();
        Scene scene = new Scene(root, 768, 600, Color.web("#f1f6f8", 1.0));

        BorderPane mainPane = new BorderPane();
        TabPane tabPane = new TabPane();

        // Create Tabs
        Tab home = new Tab();
        Tab questionBank = new Tab();
        Tab createTest = new Tab();
        Tab takeTest = new Tab();
        Tab gradeTest = new Tab();
        Tab adminTools = new Tab();

        // Set Tab titles
        home.setText("Home");
        questionBank.setText("Question Bank");
        createTest.setText("Create Test");
        takeTest.setText("Take Test");
        gradeTest.setText("Grade Test");
        adminTools.setText("Administrative Tools");

        // Create a webview for each section
        WebView homeWebView = new WebView();
        String homeUrl = getClass().getResource("home.html").toExternalForm();
        homeWebView.getEngine().load(homeUrl);

        WebView questionBankWebView= new WebView();
        String questionBankURL = getClass().getResource("question-bank.html").toExternalForm();
        questionBankWebView.getEngine().load(questionBankURL);

        WebView createTestWebView = new WebView();
        String createTestURL = getClass().getResource("create-test.html").toExternalForm();
        createTestWebView.getEngine().load(createTestURL);

        WebView takeTestWebView = new WebView();
        String takeTestURL = getClass().getResource("take-test.html").toExternalForm();
        takeTestWebView.getEngine().load(takeTestURL);

        WebView gradeTestWebView = new WebView();
        String gradeTestURL = getClass().getResource("grade-test.html").toExternalForm();
        gradeTestWebView.getEngine().load(gradeTestURL);

        WebView adminToolsWebView= new WebView();
        String adminToolsURL = getClass().getResource("admin-tools.html").toExternalForm();
        adminToolsWebView.getEngine().load(adminToolsURL);


        // Create a stackpane and display a webview inside its corresponding stackpane
        StackPane homeStack = new StackPane();
        homeStack.setAlignment(Pos.CENTER);
        homeStack.getChildren().add(homeWebView);

        StackPane questionBankStack = new StackPane();
        questionBankStack.setAlignment(Pos.CENTER);
        questionBankStack.getChildren().add(questionBankWebView);

        StackPane createTestStack = new StackPane();
        createTestStack.setAlignment(Pos.CENTER);
        createTestStack.getChildren().add(createTestWebView);

        StackPane takeTestStack = new StackPane();
        takeTestStack.setAlignment(Pos.CENTER);
        takeTestStack.getChildren().add(takeTestWebView);

        StackPane gradeTestStack = new StackPane();
        gradeTestStack.setAlignment(Pos.CENTER);
        gradeTestStack.getChildren().add(gradeTestWebView);

        StackPane adminToolsStack = new StackPane();
        adminToolsStack.setAlignment(Pos.CENTER);
        adminToolsStack.getChildren().add(adminToolsWebView);

        // Finally add each stackpanel to it's corresponding tab
        home.setContent(homeStack);
        questionBank.setContent(questionBankStack);
        createTest.setContent(createTestStack);
        takeTest.setContent(takeTestStack);
        gradeTest.setContent(gradeTestStack);
        adminTools.setContent(adminToolsStack);

        // Now that the tabs have been setup, add them to the tabpane
        tabPane.getTabs().add(home);
        tabPane.getTabs().add(questionBank);
        tabPane.getTabs().add(createTest);
        tabPane.getTabs().add(takeTest);
        tabPane.getTabs().add(gradeTest);
        tabPane.getTabs().add(adminTools);
        
        mainPane.setCenter(tabPane);
        mainPane.prefHeightProperty().bind(scene.heightProperty());
        mainPane.prefWidthProperty().bind(scene.widthProperty());

        root.getChildren().add(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

