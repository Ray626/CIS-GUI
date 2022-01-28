package com.example.friendsBook;

import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.security.cert.CertificateNotYetValidException;

public class friendsBookController {
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
    public MenuButton aGroup;
    public MenuItem aFamilies,aFriends,aClassMate;
    /**
     *All Variables on the "Display Friend" page
     */
    public ListView<Friend> displayList= new ListView<> ();
    public Text dName,dAge,dGender,dPhoneNum,dEmail;
    public Button deleteFriendBtn;
    public RadioButton rFriends, rFamilies,rClassmates;




    @FXML
    public void GenderMenuButton(){
        for (MenuItem item : aGender.getItems()){
            item.setOnAction(e-> aGender.setText(item.getText()));
        }
    }
    public void GroupMenuButton(){
        for (MenuItem item : aGroup.getItems()){
            item.setOnAction(e-> aGroup.setText(item.getText()));
        }
    }
    public void addFriend() throws IOException {
        if (aFirstName.getText().equals("")||aLastName.getText().equals("")
                ||aAge.getText().equals("")||aEmail.getText().equals("")||aPhoneNum.getText().equals("")
                ||aGender.getText().equals("Choose Gender")||aGroup.getText().equals("Group")){
            System.out.println("Invalid ");
        }else{
            Friend friend = new Friend(aFirstName.getText(),aLastName.getText(),aGender.getText(), Integer.parseInt(aAge.getText()) ,Long.parseLong(aPhoneNum.getText()),aEmail.getText());
            iOStream in = new iOStream(aGroup.getText()+".txt",friend);
            in.writer();
            aFirstName.clear();
            aLastName.clear();
            aGender.setText("Choose Gender");
            aAge.clear();
            aPhoneNum.clear();
            aEmail.clear();
            //displayList.getItems().add(friend);

        }
    }
    public void radiobuttonAction() throws IOException {
        displayList.getItems().clear();
        if(rFriends.isSelected()||rClassmates.isSelected()||rFamilies.isSelected()){
            if (rFriends.isSelected()){
                iOStream out = new iOStream( "Friends.txt",displayList);
                displayList=out.loader();
                rClassmates.setDisable(true);
                rFamilies.setDisable(true);
            }else if(rClassmates.isSelected()){
                iOStream out = new iOStream( "Classmates.txt",displayList);
                displayList=out.loader();
                rFamilies.setDisable(true);
                rFriends.setDisable(true);
            }else if(rFamilies.isSelected()){
                iOStream out = new iOStream( "Families.txt",displayList);
                displayList=out.loader();
                rFriends.setDisable(true);
                rClassmates.setDisable(true);
            }
        }else{
            rFriends.setDisable(false);
            rClassmates.setDisable(false);
            rFamilies.setDisable(false);
            dName.setText("BLANK");
            dAge.setText("BLANK");
            dGender.setText("BLANK");
            dPhoneNum.setText("BLANK");
            dEmail.setText("BLANK");
            deleteFriendBtn.setDisable(true);

        }



    }

    public void displayInfo() {

        if(displayList.getItems().size() > 0){
            Friend friendInList = displayList.getSelectionModel().getSelectedItem();
            if(friendInList != null){
                dName.setText(friendInList.getName());
                dAge.setText(String.valueOf(friendInList.getAge()));
                dGender.setText(friendInList.getGender());
                dPhoneNum.setText(String.valueOf(friendInList.getPhoneNumber()));
                dEmail.setText(friendInList.getEmail());
                deleteFriendBtn.setDisable(false);
            }

        }


    }
    public void deleteFriend() throws IOException {

        displayList.getItems().remove(displayList.getSelectionModel().getSelectedItem());
        dName.setText("BLANK");
        dAge.setText("BLANK");
        dGender.setText("BLANK");
        dPhoneNum.setText("BLANK");
        dEmail.setText("BLANK");
        deleteFriendBtn.setDisable(true);
        if (rFriends.isSelected()){
            iOStream delete = new iOStream("Friends.txt",displayList);
            delete.delete();
        }else if(rClassmates.isSelected()){
            iOStream delete = new iOStream("Classmates.txt",displayList);
            delete.delete();
        }else if (rFamilies.isSelected()){
            iOStream delete = new iOStream("Families.txt",displayList);
            delete.delete();
        }

    }
}
