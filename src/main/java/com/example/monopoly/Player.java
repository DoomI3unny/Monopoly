package com.example.monopoly;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Player {

    private Bitmap player1 ;
    private GamePlay game;
    public static int positionX;
    public static int positionY;
    private TextView textview;
    private int x;
    private int y;
    public static int playerPos;

    public Player(GamePlay game,Bitmap player1){
        this.game = game;
        this.player1 = player1;
        positionX = GamePlay.ax;
        positionY = GamePlay.ay;
        x=0;
        y=0;
        playerPos = 0;
    }

    private void update(){
        playerPos  = playerPos + 1;

        if(playerPos > 35){
            playerPos = 0;
            GamePlay.money2 = GamePlay.money2 + 200;
            if (GamePlay.credit == true){
                GamePlay.creditDialog.show();
                GamePlay.yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GamePlay.creditDialog.dismiss();
                        GamePlay.money2-=250;
                        GamePlay.credit=false;
                    }
                });
                GamePlay.no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GamePlay.creditDialog.dismiss();

                    }
                });
            }
        }
        if(GamePlay.z == GamePlay.dicenumber) {
            String text;
            if(playerPos == 27){
                playerPos = 9;
            }
            text = GamePlay.company[playerPos] + "\n" + GamePlay.money[playerPos] + "$";
            if(GamePlay.company[Player.playerPos].equals("CHANCE") || GamePlay.company[Player.playerPos].equals("Monopoly-Gift")){
                Random r = new Random();
                int z= r.nextInt()%2;
                int x = (r.nextInt()%30);
                x=x<0?-x:x;
                x=x+20;
                if(z==0){
                    text = "You Get $"+x;
                    GamePlay.money2 += x;
                    GamePlay.scorePlayer += x;
                }

            }
            GamePlay.status.setText(text);
            if(GamePlay.company_owner[playerPos].equals("Y")){
                //do nothing
            }
            else if(GamePlay.company_owner[playerPos].equals("Computer")){
                GamePlay.Buy.setVisibility(View.INVISIBLE);
            }
            else{
                GamePlay.Buy.setVisibility(View.INVISIBLE);
            }
            if (GamePlay.credit == true){
                GamePlay.pay_Rent.setVisibility(View.INVISIBLE);
            }
            if (GamePlay.credit == false){
                GamePlay.pay_Rent.setVisibility(View.VISIBLE);
            }
            GamePlay.myDialog.show();


        }
    }
    public void onDraw(Canvas canvas){
        if(GamePlay.dicenumber != 0)
            update();
        positionX = (GamePlay.positionXArray[playerPos]);
        positionY = (GamePlay.positionYArray[playerPos]);
        canvas.drawBitmap(player1, (GamePlay.positionXArray[playerPos]), (GamePlay.positionYArray[playerPos]), null);

    }


}
