package org.cvm.view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.cvm.app.View;
import org.cvm.input.Key;
import org.cvm.input.KeyInput;
import org.cvm.input.Mouse;

import java.awt.*;
import java.io.File;

import static org.cvm.Framework.*;
import static org.cvm.Framework.mouseInput;

public class HomeView extends View {

    FileChooser fileChooser;

    @Override
    public void onLaunch() {
        System.out.println("HomeView onLaunch");

        fileChooser = new FileChooser();

        Image img_play = new Image(getClass().getResourceAsStream("play.png"));
        Image img_play2 = new Image(getClass().getResourceAsStream("play2.png"));
        ImageView img_play_view = new ImageView(img_play);
        HBox hbox_play = new HBox(img_play_view);
        hbox_play.addEventHandler(MouseEvent.MOUSE_ENTERED,(event) -> {
            img_play_view.setImage(img_play2);
        });
        hbox_play.addEventHandler(MouseEvent.MOUSE_EXITED,(event) -> {
            img_play_view.setImage(img_play);
        });
        hbox_play.addEventHandler(MouseEvent.MOUSE_CLICKED,(event) -> {
            app.gotoView("Play");
        });

        Image img_exit = new Image(getClass().getResourceAsStream("exit.png"));
        Image img_exit2 = new Image(getClass().getResourceAsStream("exit2.png"));
        ImageView img_exit_view = new ImageView(img_exit);
        HBox hbox_exit = new HBox(img_exit_view);
        hbox_exit.addEventHandler(MouseEvent.MOUSE_ENTERED,(event) -> {
            img_exit_view.setImage(img_exit2);
            System.out.println("111");
        });
        hbox_exit.addEventHandler(MouseEvent.MOUSE_EXITED,(event) -> {
            img_exit_view.setImage(img_exit);
            System.out.println("222");
        });
        hbox_exit.addEventHandler(MouseEvent.MOUSE_CLICKED,(event) -> {
            app.exit();
        });

        VBox box = new VBox(hbox_play, hbox_exit);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        box.setSpacing(20);

        Group group = new Group();

        Image img_home = new Image(getClass().getResourceAsStream("home.jpg"));
        ImageView img_home_view = new ImageView(img_home);
        group.getChildren().add(img_home_view);

        Image img_title = new Image(getClass().getResourceAsStream("title.png"));
        ImageView img_title_view = new ImageView(img_title);

        Text text = new Text("V1.0    L：读取文件");
        text.setFont(new Font(30));

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(box);
        anchorPane.getChildren().add(img_title_view);
        anchorPane.getChildren().add(text);
        AnchorPane.setBottomAnchor(box, 10.0);
        AnchorPane.setRightAnchor(box, 10.0);
        AnchorPane.setLeftAnchor(text, 10.0);
        AnchorPane.setBottomAnchor(text, 10.0);

        getChildren().add(group);
        getChildren().add(anchorPane);
    }

    @Override
    public void onUpdate(double time) {
        if (keyInput.isReleased(Key.L)) {
            System.out.println("Pressed L");
            File file = fileChooser.showOpenDialog(app.getStage());
            if (file != null) {
                app.setFile(file);
                app.gotoView("Play");
            }
            else {
                System.out.println("file = null");
            }
        }
        if (keyInput.isPressed(Key.SPACE)) {
            System.out.println("Pressed SPACE");
            app.gotoView("Play");
        }
        if (keyInput.isPressed(Key.ESCAPE)) {
            System.out.println("Pressed ESC");
            app.exit();
        }
    }
}
