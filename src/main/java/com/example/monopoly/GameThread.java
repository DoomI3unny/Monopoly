package com.example.monopoly;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.MotionEvent;


public class GameThread extends Thread {

    private GamePlay game;
    private GamePlay2D game2D;
    private boolean running;

    public GameThread(GamePlay game){
        this.game = game;
    }
    public GameThread(GamePlay2D game){
        this.game2D = game;
    }

    public void setRunning(boolean run){
        running = run;
    }
    @SuppressLint("WrongCall")
    @Override
    public void run(){

        // while(running){
        Canvas c = null;
        if (game != null) {
            try {
                c = game.getHolder().lockCanvas();
                synchronized (game.getHolder()) {
                    game.onDraw(c);
                }
            } finally {
                if (c != null) {
                    game.getHolder().unlockCanvasAndPost(c);
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (game2D != null) {
            try {
                c = game2D.getHolder().lockCanvas();
                synchronized (game2D.getHolder()) {
                    game2D.onDraw(c);
                }
            } finally {
                if (c != null) {
                    game2D.getHolder().unlockCanvasAndPost(c);
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




        // }
    }

}
