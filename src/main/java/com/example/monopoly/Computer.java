package com.example.monopoly;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;


public class Computer {
    private Bitmap player2 ;
    private GamePlay game;
    private Constants constants;
    private DatabaseHelper db;
    public static int posX;
    public static int posY;
    private TextView textview;
    private int x;
    private int y;
    public static int playerPos;



    public Computer(GamePlay game,Bitmap player1,Constants constants, DatabaseHelper db){
        this.db = db;
        this.constants = constants;
        this.game = game;
        this.player2 = player1;
        posX =constants.getAx();     //GamePlay.posarray[0][0];
        posY = constants.getAy();     /// GamePlay.posarray[0][1];
        x=0;
        y=0;
        playerPos = 0;
    }



    private void update(){

        playerPos  = playerPos + 1;
        if(playerPos > 35){
            playerPos = 0;
            GamePlay.money1 = GamePlay.money1 + 200;
        }

        if(GamePlay.z == GamePlay.dicenumber){
            if(playerPos == 27){
                playerPos = 9;
            }
            String company = db.getCompany(playerPos);
            String company_owner = db.getCompanyOwner(playerPos);
            int money = db.getCompanyCost(playerPos);
            if(company.equals("CHANCE") || company.equals("Monopoly-Gift")){
                Random r = new Random();
                int z= r.nextInt()%2;
                int y = r.nextInt();
                if(y<0){
                    y=Math.abs(y);
                }
                int x = (y%30);
                x=x+20;
                if(z==0){
                    GamePlay.text = "Vision Get $"+x;
                    GamePlay.money1 += x;
                    GamePlay.scoreComputer += x;
                }
                else{
                    GamePlay.text = "Vision Pay Tax $"+x;
                    GamePlay.money1 -= x;
                }
            }
           if(company_owner.equals("Player")){
               int rent = db.getCompanyRent(playerPos);
                GamePlay.money1 = GamePlay.money1 -rent;
                GamePlay.money2 += rent;
            }
            else if(company_owner.equals("Y")){
                Random r = new Random();
                int x = r.nextInt();
                if(x%2==0){
                    GamePlay.money1 = GamePlay.money1 - money;
                    db.updateCompanyOwner(playerPos, "Computer");
                    GamePlay.scoreComputer += money;
                }
              /*  else{
                    GamePlay.money1 = GamePlay.money1 - GamePlay.rent[playerPos];
                }*/
            }
            else{
                GamePlay.money1 = GamePlay.money1 - money;
            }
        }


    }
    public void onDraw(Canvas canvas){
        if(GamePlay.dicenumber != 0)
            update();
        posX = constants.getPositionXArray()[playerPos];
        posY = constants.getPositionYArray()[playerPos];
        canvas.drawBitmap(player2,posX,posY,null);

    }

    public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}
