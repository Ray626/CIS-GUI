package com.example.friendBook;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

public class friendBookController {
    /**
     * ALl variables on the "Add Friend" page
     */
    public TextField aFirstName;
    public TextField aLastName;
    public TextField aAge;
    public MenuButton aGender;
    public MenuItem aMale;
    public MenuItem aOthers;
    public MenuItem aFemale;
    public TextField aPhoneNum;
    public TextField aEmail;
    public Button generate;
    /**
     *All Variables on the "Display Friend" page
     */
    public ListView<Friend> displayList= new ListView<> ();
    public Text dName,dAge,dGender,dPhoneNum,dEmail;
    public Button deleteFriendBtn;



    @FXML
    public void GenderMenuButton(){
        for (MenuItem item : aGender.getItems()){
            item.setOnAction(e-> aGender.setText(item.getText()));
        }
    }
    public void addFriend(){
        if (aFirstName.getText().equals("")||aLastName.getText().equals("")
        ||aAge.getText().equals("")||aEmail.getText().equals("")||aPhoneNum.getText().equals("")
        ||aGender.getText().equals("Choose Gender")){
            System.out.println("Invalid ");
        }else{
            Friend friend = new Friend(aFirstName.getText(),aLastName.getText(),aGender.getText(), Integer.parseInt(aAge.getText()) ,Long.parseLong(aPhoneNum.getText()),aEmail.getText());
            aFirstName.clear();
            aLastName.clear();
            aGender.setText("Choose Gender");
            aAge.clear();
            aPhoneNum.clear();
            aEmail.clear();
            displayList.getItems().add(friend);
        }
    }
    public void displayInfo(){
        if(displayList.getItems().size() > 0){
            Friend friendInList = displayList.getSelectionModel().getSelectedItem();
            dName.setText(friendInList.getName());
            dAge.setText(String.valueOf(friendInList.getAge()));
            dGender.setText(friendInList.getGender());
            dPhoneNum.setText(String.valueOf(friendInList.getPhoneNumber()));
            dEmail.setText(friendInList.getEmail());
            //deleteFriendBtn.setDisable(false);
        }


    }
    public void deleteFriend(){
        displayList.getItems().remove(displayList.getSelectionModel().getSelectedItem());
        dName.setText("BLANK");
        dAge.setText("BLANK");
        dGender.setText("BLANK");
        dPhoneNum.setText("BLANK");
        dEmail.setText("BLANK");
        if (displayList.getItems().size() < 1){
            deleteFriendBtn.setDisable(true);
        }
    }



}
