package com.example.monopoly;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
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
        }
        if(GamePlay.z == GamePlay.dicenumber) {
            String text = GamePlay.company[GamePlay.z>playerPos?GamePlay.z-playerPos:playerPos-GamePlay.z]+" to ";
            if(playerPos == 27){
                playerPos = 9;
            }
            text += GamePlay.company[playerPos];


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
