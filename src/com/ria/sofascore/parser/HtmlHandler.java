package com.ria.sofascore.parser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;

public class HtmlHandler extends Application {


    private WebView webview = new WebView();
    private final WebEngine webengine = webview.getEngine();
    private HtmlParser htmlParser = new HtmlParser();
    private boolean isMainLoaded=true;
    private ArrayList<String> links;
    private int pageIndex;

    public void start(Stage primaryStage) {

        primaryStage.setOpacity(1);
        webengine.getLoadWorker().stateProperty().addListener(

                new ChangeListener<Worker.State>() {

                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            Document doc = webengine.getDocument();
                            try {
                                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
                                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                                //transformer.setOutputProperty("{https://www.sofascore.com/ru/tennis/livescore}", "4");

                                StringWriter writer = new StringWriter();
                                StreamResult result = new StreamResult(writer);
                                transformer.transform(new DOMSource(doc),result);
                                String strResult = writer.toString();
                                if(isMainLoaded){
                                    links=htmlParser.getAllLinks(strResult);
                                }
                                isMainLoaded=false;
                                if(pageIndex==links.size()){
                                    isMainLoaded=true;
                                    pageIndex=0;
                                    webengine.load("https://www.sofascore.com/ru/tennis/livescore");
                                }else{
                                    getMatchFromLink(links.get(pageIndex));
                                    pageIndex++;
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
        webengine.load("https://www.sofascore.com/ru/tennis/livescore");
        Scene view = new Scene(webview,800,600);
        primaryStage.setScene(view);
        primaryStage.setTitle("sofascore_parser");
        primaryStage.show();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50000), e->{
            webengine.reload();
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void getMatchFromLink(String s) {
        webengine.load("https://www.sofascore.com"+s);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
