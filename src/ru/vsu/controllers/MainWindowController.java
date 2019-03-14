package ru.vsu.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import ru.vsu.DB;
import ru.vsu.domains.SecurityGroup;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane checkBoxPane;

    @FXML
    private Button checkButton;

    @FXML
    private TextArea textArea;

    private DB db;

    @FXML
    void checkButtonAction(ActionEvent event) {
        ObservableList<Node> children = checkBoxPane.getChildren();
        for (Node child : children) {
            CheckBox checkBox = (CheckBox) child;
            if (checkBox.isSelected()) {
                db.incConformityInGroups(checkBox.getText());
            }
        }
        resultWorks();
        db.printResults();
        db.deleteOldData();
    }

    @FXML
    void initialize() {
        db = new DB(9);
//        db.testConnection();
        textArea.setText("hi");

        ObservableList<Node> children = checkBoxPane.getChildren();
        for (Node child : children) {
            CheckBox checkBox = (CheckBox) child;
            checkBox.setSelected(true);
        }
    }

    void resultWorks() {
        List<SecurityGroup> securityGroupList = db.getSecurityGroupList();
        SecurityGroup betterChoise = null;
        StringBuilder sb = new StringBuilder();
        for (SecurityGroup securityGroup : securityGroupList) {
            if (securityGroup.getConformity() >= securityGroup.getHowMuchPlusWeNeed()) {
                betterChoise = securityGroup;
            }
        }
        if (betterChoise == null) {
            sb.append("Its not any class of security!\n\n");
        } else {
            sb.append("Its: " + betterChoise.getName() + " : ("
                    + betterChoise.getConformity() + " / " +
                    betterChoise.getHowMuchPlusWeNeed() + ")\n\n");
        }

        sb.append("Other results\n");
        for (SecurityGroup securityGroup : securityGroupList) {
            sb.append(securityGroup.getName() + " : ("
                    + securityGroup.getConformity() + " / " +
                    securityGroup.getHowMuchPlusWeNeed() + ")\n");
        }
        textArea.setText(sb.toString());
    }
}
