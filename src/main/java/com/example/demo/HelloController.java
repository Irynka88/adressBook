package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Contact> tableView;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void showDialog(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-dialog.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Редагування");

            AddController addController = fxmlLoader.getController();
            addController.setContact(null);

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnAdd.getScene().getWindow());
            stage.setMinHeight(300);
            stage.setMinWidth(400);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showDeleteWarning(ActionEvent event) {
        System.out.println("Кнопка 'Видалити' натиснута");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo/delete-warning.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Попередження");

            Scene scene = new Scene(parent);
            stage.setScene(scene);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnDelete.getScene().getWindow());

            stage.setMinHeight(200);
            stage.setMinWidth(300);
            stage.setResizable(false);

            System.out.println("Відкривається вікно попередження");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Помилка при завантаженні FXML: " + e.getMessage());
        }

    }

    @FXML
    public void editContact() {
        Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-dialog.fxml"));
                Parent parent = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Редагування");

                AddController addController = fxmlLoader.getController();
                addController.setContact(selectedContact);

                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(btnEdit.getScene().getWindow());
                stage.setMinHeight(300);
                stage.setMinWidth(400);
                stage.setResizable(false);
                stage.showAndWait();

                tableView.refresh();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Попередження");
            alert.setHeaderText(null);
            alert.setContentText("Будь ласка, виберіть контакт для редагування.");
            alert.showAndWait();
        }
    }
}
