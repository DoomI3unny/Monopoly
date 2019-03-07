package com.example.monopoly;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;


public class GamePlay extends SurfaceView {

    private Bitmap player1;
    private Bitmap diceroll;
    private Bitmap one;
    private Bitmap two;
    private Bitmap three;
    private Bitmap four;
    private Bitmap five;
    private Bitmap six;
    private Bitmap finish;

    public static int scorePlayer;
    public static  TextView textview;
    public static  TextView textview2;
    private Bitmap background;
    private SurfaceHolder holder;
    private GameThread gameThread;
    public static int number;
    public static int money2;
    public static int count;
    public static int dicenumber;
    private static int dicenumber1;
    private static int dicenumber2;
    public static int money[];
    public static int positionXArray[];
    public static int positionYArray[];
    public static boolean playerturn;
    public static int z;
    public static String[] company;
    public static String[] company_owner;
    public static String text;
    public static int ax,ay;
    public static int diffX;
    public static int diffY;
    public static int maxH;
    public static int maxW;
    public static Dialog myDialog;
    public static Dialog playerDialog;
    public static TextView status;
    public static Button Buy;
    public static Button pay_Rent;
    public boolean xyz;
    private Player myplayer;
    public static ImageView finishImage;
    WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display=wm.getDefaultDisplay();


    public GamePlay(Context context){
        super(context);

        holder = getHolder();
        gameThread = new GameThread(this);

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                gameThread.setRunning(true);
                try {
                    gameThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }

        });

        company_owner = new String[36];
        for(int i=0;i<36;i++){
            company_owner[i]="Y";
        }
        company_owner[2]="N";company_owner[12]="N";company_owner[30]="N";company_owner[34]="N";
        company_owner[4]="N";company_owner[6]="N";company_owner[9]="N";company_owner[15]="N";
        company_owner[18]="N";company_owner[21]="N";company_owner[24]="N";company_owner[27]="N";
        company_owner[32]="N";company_owner[0]="N";
        company = new String[36];
        company[0]="Home";company[1]="NERF";company[2]="Rival-Tower-Tax";company[3]="TransFormers";
        company[4]="Monopoly-Gift";company[5]="Spotify";company[6]="CHANCE";company[7]="BeatsAudio";
        company[8]="Tender";company[9]="JAIL";company[10]="JET-BLUE";
        company[11]="EA";company[12]="Electric Company";company[13]="Hasbro";company[14]="Under-Armour";
        company[15]="CHANCE";company[16]="CARNIVAL";company[17]="YAHOO!";company[18]="Free-Parking";
        company[19]="Paramount";company[20]="CHEVROLOT";company[21]="CHANCE";company[22]="Ebay";company[23]="X-Games";
        company[24]="Monopoly-Gift";company[25]="Ducati";company[26]="McDonald's";company[27]="Go-To-JAIL";
        company[28]="intel";company[29]="X-BOX";company[30]="Water-Works";company[31]="Nestle";
        company[32]="CHANCE";company[33]="SAMSUNG";company[34]="Towar-Tax";company[35]="Coca-Cola";
        scorePlayer=0;
        myDialog = new Dialog(context);
        playerDialog = new Dialog(context);
        int w=display.getHeight();
        int h=display.getWidth();
        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg), (h / 12) * 10, w, true);
        player1 = BitmapFactory.decodeResource(getResources(), R.drawable.player1_50);
        diceroll = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dice3droll), (h / 12) * 2, (w / 12) * 2, true);
        one = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.one), (h / 12) * 2, (w / 12) * 2, true);
        two = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.two), (h / 12) * 2, (w/12)*2, true);
        three = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.three), (h / 12) * 2, (w/12)*2, true);
        four = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.four), (h / 12) * 2, (w / 12) * 2, true);
        five = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.five), (h / 12) * 2, (w / 12) * 2, true);
        six = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.six), (h / 12) * 2, (w/12)*2, true);
        finish = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.finish), (h / 12) * 2, (w / 12) * 2, true);


        ax = ( ((h / 12) * 10)/12)*11;
        ay = (w/12)*10;

        diffX = ( ((h / 12) * 10)/12);
        diffY = w/12;
        maxH = (display.getWidth() / 12) * 10;
        maxW = (display.getHeight() / 12) * 4;

        z=0;

        xyz = true;


        //position array calculating..........
        positionXArray = new int[36];

        positionXArray[0] = GamePlay.ax;
        positionXArray[1] = ax- (2*diffX);
        positionXArray[2] = ax- (3*diffX);
        positionXArray[3] = ax- (4*diffX);
        positionXArray[4] = ax- (5*diffX);
        positionXArray[5] = ax- (6*diffX);
        positionXArray[6] = ax- (7*diffX);
        positionXArray[7] = ax- (8*diffX);
        positionXArray[8] = ax- (9*diffX);
        positionXArray[9] = 0;
        positionXArray[10] = 0;
        positionXArray[11] = 0;
        positionXArray[12] = 0;
        positionXArray[13] = 0;
        positionXArray[14] = 0;
        positionXArray[15] = 0;
        positionXArray[16] = 0;
        positionXArray[17] = 0;
        positionXArray[18] = 0;
        positionXArray[19] = 2*diffX;
        positionXArray[20] = 3*diffX;
        positionXArray[21] = 4*diffX;
        positionXArray[22] = 5*diffX;
        positionXArray[23] = 6*diffX;
        positionXArray[24] = 7*diffX;
        positionXArray[25] = 8*diffX;
        positionXArray[26] = 9*diffX;
        positionXArray[27] = 11*diffX;
        positionXArray[28] = 11*diffX;
        positionXArray[29] = 11*diffX;
        positionXArray[30] = 11*diffX;
        positionXArray[31] = 11*diffX;
        positionXArray[32] = 11*diffX;
        positionXArray[33] = 11*diffX;
        positionXArray[34] = 11*diffX;
        positionXArray[35] = 11*diffX;


        //positon Y Array
        positionYArray = new int[36];

        positionYArray[0] = GamePlay.ay;
        positionYArray[1] = ay;
        positionYArray[2] = ay;
        positionYArray[3] = ay;
        positionYArray[4] = ay;
        positionYArray[5] = ay;
        positionYArray[6] = ay;
        positionYArray[7] = ay;
        positionYArray[8] = ay;
        positionYArray[9] = ay;
        positionYArray[10] = ay - 2*diffY;
        positionYArray[11] = ay - 3*diffY;
        positionYArray[12] = ay - 4*diffY;
        positionYArray[13] = ay - 5*diffY;
        positionYArray[14] = ay - 6*diffY;
        positionYArray[15] = ay - 7*diffY;
        positionYArray[16] = ay - 8*diffY;
        positionYArray[17] = ay - 9*diffY;
        positionYArray[18] = 0;
        positionYArray[19] = 0;
        positionYArray[20] = 0;
        positionYArray[21] = 0;
        positionYArray[22] = 0;
        positionYArray[23] = 0;
        positionYArray[24] = 0;
        positionYArray[25] = 0;
        positionYArray[26] = 0;
        positionYArray[27] = 0;
        positionYArray[28] = diffY;
        positionYArray[29] = 2*diffY;
        positionYArray[30] = 3*diffY;
        positionYArray[31] = 4*diffY;
        positionYArray[32] = 5*diffY;
        positionYArray[33] = 6*diffY;
        positionYArray[34] = 7*diffY;
        positionYArray[35] = 8*diffY;



        textview = new TextView(getContext());
        textview2 = new TextView(getContext());
        dicenumber = 0;
        dicenumber1 = 0;
        dicenumber2 = 0;
        playerturn = true;
        textview.setText(" ");
        number =40;
        money2 = 1500;
        count=0;

        money = new int[36];

        money[0] = 0;
        money[1] = 50;
        money[2] = 0;
        money[3] = 50;
        money[4] = 0;
        money[5] = 100;
        money[6] = 0;
        money[7] = 100;
        money[8] = 100;
        money[9] = 100;
        money[10] = 150;
        money[11] = 150;
        money[12] = 150;
        money[13] = 150;
        money[14] = 200;
        money[15] = 0;
        money[16] = 200;
        money[17] = 200;
        money[18] = 0;
        money[19] = 250;
        money[20] = 250;
        money[21] = 0;
        money[22] = 250;
        money[23] = 300;
        money[24] = 0;
        money[25] = 300;
        money[26] = 300;
        money[27] = 0;
        money[28] = 350;
        money[29] = 350;
        money[30] = 150;
        money[31] = 350;
        money[32] = 0;
        money[33] = 400;
        money[34] = 0;
        money[35] = 400;

        myplayer = new Player(this,player1);

        myDialog.setContentView(R.layout.dialogfile);
        myDialog.setCanceledOnTouchOutside(true);
        myDialog.setCancelable(false);
        status=(TextView) myDialog.findViewById(R.id.status_id);
        Buy = (Button) myDialog.findViewById(R.id.popButton);
        pay_Rent = (Button) myDialog.findViewById(R.id.pay_Rent);


    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        int x = (display.getWidth()/2);

        canvas.drawBitmap(background,(0),(0),null);
        canvas.drawBitmap(diceroll, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
        canvas.drawBitmap(diceroll, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);

        switch(dicenumber1){
            case 0:
                canvas.drawBitmap(diceroll, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                canvas.drawBitmap(diceroll, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;
            case 1:
                canvas.drawBitmap(one, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                break;
            case 2:
                canvas.drawBitmap(two, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                break;
            case 3:
                canvas.drawBitmap(three, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                break;
            case 4:
                canvas.drawBitmap(four, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                break;
            case 5:
                canvas.drawBitmap(five, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                break;
            case 6:
                canvas.drawBitmap(six, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
                break;

        }
        switch(dicenumber2){
            case 0:
                break;
            case 1:
                canvas.drawBitmap(one, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;
            case 2:
                canvas.drawBitmap(two, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;
            case 3:
                canvas.drawBitmap(three, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;
            case 4:
                canvas.drawBitmap(four, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;
            case 5:
                canvas.drawBitmap(five, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;
            case 6:
                canvas.drawBitmap(six, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);
                break;

        }


        if(count==0) {
            myplayer.onDraw(canvas);
        }


        LinearLayout layout = new LinearLayout(getContext());
        textview.setVisibility(View.VISIBLE);
        textview.setText(" $ " + money2);
        textview.setTextColor(Color.BLUE);
        textview.setTextSize(25);

        if(textview.getParent()!=null)
            ((ViewGroup)textview.getParent()).removeView(textview); // <- fix
        layout.addView(textview);
        layout.measure(0, 0);
        layout.layout(0, 0, 0, 0);
        canvas.translate((display.getWidth() / 12) * 10, (display.getHeight() / 12) * 2);
        layout.draw(canvas);

        LinearLayout layout2 = new LinearLayout(getContext());
        textview2.setVisibility(View.VISIBLE);
        textview2.setTextColor(Color.GREEN);
        textview2.setTextSize(25);

        if(textview2.getParent()!=null)
            ((ViewGroup)textview2.getParent()).removeView(textview2); // <- fix
        layout2.addView(textview2);
        layout2.measure(200, 200);
        layout2.layout(100, 100, 200, 200);
        canvas.translate(0, (display.getHeight() / 12) * 7);
        layout2.draw(canvas);

        canvas.translate(-((display.getWidth() / 12) * 10), -(((display.getHeight() / 12) * 7) + ((display.getHeight() / 12) * 2)));
    }


    @SuppressLint("WrongCall")
    protected void checkMovement(float x2,float y2) {

        int xRight = (display.getWidth() / 12) * 10;
        int xLeft = display.getWidth();
        int yRight = (display.getHeight() / 12) * 4;
        int yLeft = (display.getHeight() / 12) * 8;
        int y2Right = (display.getHeight() / 12) * 10;
        int y2Left = (display.getHeight() / 12) * 12;

        if(x2>xRight && x2<xLeft  && y2>y2Right && y2<y2Left  ){
            finishImage.setImageResource(R.drawable.finish);
            playerturn=false;

        }

        if (x2 > xRight && x2 < xLeft && y2 > yRight && y2 < yLeft && playerturn == true) {

            dicenumber = 0;
            Random r = new Random();
            dicenumber1 = (r.nextInt(6) + 1);
            dicenumber += dicenumber1;
            dicenumber2 = (r.nextInt(6) + 1);
            dicenumber += dicenumber2;
            z = 1;

            while (z <= dicenumber) {

                Canvas canvas = holder.lockCanvas(null);
                onDraw(canvas);
                holder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                z++;
            }
            count = ++count % 2;
        }

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GamePlay.money2 = GamePlay.money2 - GamePlay.money[Player.playerPos];
                company_owner[Player.playerPos] = "Player";
                scorePlayer += GamePlay.money[Player.playerPos];
                myDialog.dismiss();
                GamePlay.Buy.setVisibility(View.VISIBLE);
                count = ++count % 2;
            }
        });

          pay_Rent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GamePlay.money2 += 500;
                    myDialog.dismiss();
                    GamePlay.Buy.setVisibility(View.VISIBLE);
                    count = ++count % 2;
                }
            });
        }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){

        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            if(!holder.getSurface().isValid()) {

            }
            else{
                checkMovement(event.getX(), event.getY());
            }
        }
        event.setAction(0);
        return true;
    }
}
