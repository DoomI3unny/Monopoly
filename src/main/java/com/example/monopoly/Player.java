package com.example.monopoly;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Player {

    private Bitmap player1 ;
    private GamePlay game;
    private  DatabaseHelper db;
    private Constants constants;
    public static int positionX;
    public static int positionY;
    private TextView textview;
    private int x;
    private int y;
    public static int playerPos;

    public Player(GamePlay game,Bitmap player1,Constants constants, DatabaseHelper db){
        this.db = db;
        this.constants = constants;
        this.game = game;
        this.player1 = player1;
        positionX = constants.getAx();
        positionY = constants.getAy();
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
            String company=db.getCompany(playerPos);
            int money=db.getCompanyCost(playerPos);
            text = company + "\n" + money + "$";
            if(company.equals("CHANCE") || company.equals("Monopoly-Gift")){
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
            String company_owner = db.getCompanyOwner(playerPos);
            boolean dialog = false;


            if(company_owner.equals("Y")){
                //do nothing
            }
            else if(company_owner.equals("Computer")){
                GamePlay.Buy.setVisibility(View.INVISIBLE);
                dialog =true;

            }
            else{
                GamePlay.Buy.setVisibility(View.INVISIBLE);

            }
            if (GamePlay.credit == true){
                GamePlay.pay_Rent.setVisibility(View.INVISIBLE);
            }
            if (GamePlay.credit == false ){
                GamePlay.pay_Rent.setVisibility(View.VISIBLE);
            }
            if (!dialog) GamePlay.myDialog.show();
        }
    }


    public void onDraw(Canvas canvas){
        if(GamePlay.dicenumber != 0)
            update();
        positionX = constants.getPositionXArray()[playerPos];
        positionY = constants.getPositionYArray()[playerPos];
        canvas.drawBitmap(player1, positionX, positionY, null);
    }



}
