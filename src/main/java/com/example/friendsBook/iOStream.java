package com.example.friendsBook;

import javafx.scene.control.ListView;

import java.io.*;
import java.util.ArrayList;

public class iOStream {
    private String textFile;
    private Friend friendInfo;
    private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fr;
    private BufferedReader br;
    private ListView<Friend> friendsList;




    public iOStream(String textFile,Friend friendInfo) {
        this.textFile = textFile;
        this.friendInfo = friendInfo;

    }
    public iOStream(String textFile, ListView<Friend> friendsList) {
        this.textFile = textFile;
        this.friendsList = friendsList;
    }

    public void writer() throws IOException {
        fw = new FileWriter(textFile,true);
        bw = new BufferedWriter(fw);
        bw.write(friendInfo.getName()+" "+friendInfo.getAge()+" "+friendInfo.getGender()+ " "+ friendInfo.getPhoneNumber() + " " + friendInfo.getEmail() +"\n");
        bw.close();


    }
    public ListView<Friend> loader() throws IOException {
        fr = new FileReader(textFile);
        br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            String[] lineArray = line.split(" ");
                Friend addFriend = new Friend(lineArray[0],lineArray[1],lineArray[3],Integer.parseInt(lineArray[2]),Long.parseLong(lineArray[4]),lineArray[5]);
                friendsList.getItems().add(addFriend);
        }
        br.close();
        return friendsList;
    }
    public void delete() throws IOException {
        fw = new FileWriter(textFile);
        bw = new BufferedWriter(fw);

        for (int i = 0; i < friendsList.getItems().size(); i++){
            bw.write(friendsList.getItems().get(i).getName()+" "+friendsList.getItems().get(i).getAge()+" "+friendsList.getItems().get(i).getGender()+ " "+ friendsList.getItems().get(i).getPhoneNumber() + " " + friendsList.getItems().get(i).getEmail() +"\n");
        }
        bw.close();

    }







}
