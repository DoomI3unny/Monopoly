package com.example.monopoly;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;



public class GamePlay2D extends SurfaceView {

    private Bitmap player1;
    private Bitmap player2;
    private Bitmap icon1;
    private Bitmap icon2;
    private Bitmap diceroll;
    private Bitmap one;
    private Bitmap two;
    private Bitmap three;
    private Bitmap four;
    private Bitmap five;
    private Bitmap six;
    private Bitmap finishturn;
    public Bitmap playersign;
    public Bitmap computersign;

    public static int scorePlayer;
    public static int scoreComputer;
    public static TextView textview;
    public static  TextView textview2;
    private Bitmap background;
    private SurfaceHolder holder;
    private GameThread gameThread;
   // private Player myplayer;
   // private Computer comp;
    public static int number;
    private LinearLayout layout;
    public static int money1;
    public static int money2;
    public static int count;
    public static int dicenumber;
    private static int dicenumber1;
    private static int dicenumber2;
    public static int positionXArray[];
    public static int positionYArray[];


    public static boolean playerturn;
    public static boolean computerturn;
    public static int z;

    public static String text;

    public static int maxH;
    public static int maxW;
    public static Dialog myDialog;
    public static Dialog playerDialog;
    public static Dialog computerDialog;
    public static Dialog creditDialog;
    public static ImageView playerImage;
    public static TextView status;
    public static TextView smscomp;
    public static Button Buy;
    public static Button end;
    public static Button click;
    public static Button pay_Rent;
    public static Button no;
    public static Button yes;
    public boolean xyz;
    public static boolean credit;
    public static boolean order;
    public static TextView playerBal;
    public static TextView companyPlayer;
    public static TextView playername;
    private DatabaseHelper db;
    private Constants constants;
    BluetoothSocket bluetoothSocket;
    BluetoothDevice bluetoothDevice;
    public static ConnectedThread connectedThread;
    WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
    Display display=wm.getDefaultDisplay();




    public GamePlay2D(Context context, AttributeSet attrs){
        super(context,attrs);
        holder = getHolder();
        gameThread = new GameThread(this);
        Constants c=new Constants(context);
        db=new DatabaseHelper(context);


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


        scoreComputer=0;
        scorePlayer=0;
        myDialog = new Dialog(context);
        playerDialog = new Dialog(context);
        computerDialog = new Dialog(context);
        creditDialog = new Dialog(context);
        int w=display.getHeight();
        int h=display.getWidth();
        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg), (h / 12) * 10, w, true);
        player1 = BitmapFactory.decodeResource(getResources(), R.mipmap.player_1);
        player2 = BitmapFactory.decodeResource(getResources(), R.mipmap.player_2);
        icon1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.playersign), (h / 12) * 2, (w / 12) * 2, true);
        icon2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.computersign), (h / 12) * 2, (w / 12) * 2, true);
        diceroll = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dice3droll), (h / 12) * 2, (w / 12) * 2, true);
        one = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.one), (h / 12) * 2, (w / 12) * 2, true);
        two = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.two), (h / 12) * 2, (w/12)*2, true);
        three = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.three), (h / 12) * 2, (w/12)*2, true);
        four = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.four), (h / 12) * 2, (w / 12) * 2, true);
        five = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.five), (h / 12) * 2, (w / 12) * 2, true);
        six = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.six), (h / 12) * 2, (w/12)*2, true);
        finishturn = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.finish), (h / 12) * 2,(w / 12) * 4, true);
        playersign = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.playersign), (h / 40),(w / 40), true);
        computersign = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.computersign), (h / 40),(w / 40), true);


        maxH = (display.getWidth() / 12) * 10;
        maxW = (display.getHeight() / 12) * 4;

        z=0;

        xyz = true;
        credit=false;




        textview = new TextView(getContext());
        textview2 = new TextView(getContext());
      //  myplayer = new Player(this,player1,constants,db);
     //   comp = new Computer(this,player2,constants,db);
        dicenumber = 0;
        dicenumber1 = 0;
        dicenumber2 = 0;
        playerturn = false;
        computerturn = false;

        textview.setText(" ");
        number =40;
        money1 = 1500;
        money2 = 1500;
        count=0;


        //complete money array

        myDialog.setContentView(R.layout.dialogfile);
        myDialog.setTitle("Player Turn");
        myDialog.setCanceledOnTouchOutside(true);
        myDialog.setCancelable(false);
        status=(TextView) myDialog.findViewById(R.id.status_id);
        Buy = (Button) myDialog.findViewById(R.id.popButton);
        end = (Button) myDialog.findViewById(R.id.endButton);
        pay_Rent = (Button) myDialog.findViewById(R.id.pay_Rent);

        //player profile dialog
        playerDialog.setContentView(R.layout.playerprofile);
        playerDialog.setTitle("Player 1");
        playerDialog.setCanceledOnTouchOutside(true);
        playerDialog.setCancelable(true);
        playerBal = (TextView)playerDialog.findViewById(R.id.textView3);
        playername = (TextView)playerDialog.findViewById(R.id.textView);
        companyPlayer = (TextView)playerDialog.findViewById(R.id.textView5);
        playerImage = (ImageView)playerDialog.findViewById(R.id.imageViewPlayer);
        //computer finish Turn Dialog
        computerDialog.setContentView(R.layout.compdialog);
        computerDialog.setTitle("Computer Turn");
        computerDialog.setCanceledOnTouchOutside(true);
        computerDialog.setCancelable(true);
        smscomp = (TextView)computerDialog.findViewById(R.id.sms);
        click = (Button)computerDialog.findViewById(R.id.click);

        creditDialog.setContentView(R.layout.creditdialog);
        creditDialog.setTitle("Credit");
        creditDialog.setCanceledOnTouchOutside(true);
        creditDialog.setCancelable(true);
        no = (Button) creditDialog.findViewById(R.id.no);
        yes = (Button) creditDialog.findViewById(R.id.yes);

        order=true;
        positionXArray=constants.getPositionXArray();
        positionYArray=constants.getPositionYArray();
    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        int x = (display.getWidth() / 2);

        canvas.drawBitmap(background, (0), (0), null);
        canvas.drawBitmap(icon2, (display.getWidth() / 12) * 10, 0, null);
        canvas.drawBitmap(icon1, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 10, null);
        canvas.drawBitmap(diceroll, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 4, null);
        canvas.drawBitmap(diceroll, (display.getWidth() / 12) * 10, (display.getHeight() / 12) * 6, null);

        for(int i=0;i<36;i++){
            String company_owner = db.getCompanyOwner(i);
            if(i<=10) {
                if (company_owner.equals("Player")) {
                    canvas.drawBitmap(playersign, GamePlay.positionXArray[i], GamePlay.positionYArray[i], null);
                }
                if (company_owner.equals("Computer")) {
                    canvas.drawBitmap(computersign, GamePlay.positionXArray[i], GamePlay.positionYArray[i], null);
                }
            }
            else if(i<19){
                if (company_owner.equals("Player")) {
                    canvas.drawBitmap(playersign, GamePlay.positionXArray[i - 1], GamePlay.positionYArray[i - 1], null);
                }
                if (company_owner.equals("Computer")) {
                    canvas.drawBitmap(computersign, GamePlay.positionXArray[i - 1], GamePlay.positionYArray[i - 1], null);
                }
            }
            else if(i<28){
                if (company_owner.equals("Player")) {
                    canvas.drawBitmap(playersign, GamePlay.positionXArray[i], GamePlay.positionYArray[i], null);
                }
                if (company_owner.equals("Computer")) {
                    canvas.drawBitmap(computersign, GamePlay.positionXArray[i], GamePlay.positionYArray[i], null);
                }
            }
            else if(i<35){
                if (company_owner.equals("Player")) {
                    canvas.drawBitmap(playersign, GamePlay.positionXArray[i + 1], GamePlay.positionYArray[i + 1], null);
                }
                if (company_owner.equals("Computer")) {
                    canvas.drawBitmap(computersign, GamePlay.positionXArray[i + 1], GamePlay.positionYArray[i + 1], null);
                }
            }
            else{

            }
        }
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
            canvas.drawBitmap(player2,(Computer.posX),(Computer.posY),null);
       //     myplayer.onDraw(canvas);
        }
        else{
            canvas.drawBitmap(player1,(Player.positionX),(Player.positionY),null);
         //   comp.onDraw(canvas);
        }



        LinearLayout layout = new LinearLayout(getContext());
        textview.setVisibility(View.VISIBLE);
        textview.setText(" $ " + money1);
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
        textview2.setText(" $ " + money2);
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
    protected void checkMovement(float x2,float y2){

        int xRight = (display.getWidth() / 12) * 10;
        int xLeft = display.getWidth();
        int yRight = (display.getHeight() / 12) * 4;
        int yLeft = (display.getHeight() / 12) * 8;
        int y1Right = 0 ;
        int y1Left = (display.getHeight() / 12) * 2;
        int y2Right = (display.getHeight() / 12) * 10;
        int y2Left = (display.getHeight() / 12) * 12;
        if(x2>xRight && x2<xLeft  && y2>y1Right && y2<y1Left  ){
            playerDialog.setTitle("Computer");
            playerImage.setImageResource(R.drawable.computersign);
            playername.setText("Computer");
            String company_by_player ;
            company_by_player = db.getPlayerCompanies("Computer");
            // if(company_owner.equals("Computer"))
            //        company_by_player += company[i]+"\n"; //update company owner

            companyPlayer.setText(company_by_player);
            playerBal.setText("  $ "+Integer.toString(GamePlay.money1));
            playerDialog.show();

        }
        if(x2>xRight && x2<xLeft  && y2>y2Right && y2<y2Left  ){
            playerDialog.setTitle("Player");
            playerImage.setImageResource(R.drawable.playersign);
            playername.setText("Player");
            String company_by_player;
            company_by_player = db.getPlayerCompanies("Player");
            Log.d ("dd",company_by_player);
               /* if(company_owner.equals("Player"))
                    company_by_player += company[i]+"\n"; //update company owner*/
            companyPlayer.setText(company_by_player);
            playerBal.setText("  $ " + Integer.toString(GamePlay.money2));
            playerDialog.show();
        }
        if(x2>xRight && x2<xLeft  && y2>yRight && y2<yLeft && order==true  ) {
            order=false;

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

            Buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int money = db.getCompanyCost(Player.playerPos);
                    GamePlay.money2 = GamePlay.money2 - money;
                    db.updateCompanyOwner(Player.playerPos,"Player");
                    db.getCompanyOwner(Player.playerPos);
                    scorePlayer += money;
                    myDialog.dismiss();
                    GamePlay.Buy.setVisibility(View.VISIBLE);
                    GamePlay.pay_Rent.setText("Credit");
                    if (money2 < 0) {
                        gameOver();
                    }
                    computerTurn();
                    count = ++count % 2;

                }
            });
            end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myDialog.dismiss();
                    GamePlay.Buy.setVisibility(View.VISIBLE);
                    GamePlay.pay_Rent.setText("Credit");
                    if (money2 < 0) {
                        gameOver();
                    }
                    computerTurn();
                    count = ++count % 2;
                }
            });
            pay_Rent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (credit == false) {
                        GamePlay.money2 = GamePlay.money2 + 200;
                        credit = true;
                    }
                    myDialog.dismiss();
                    computerTurn();
                    count = ++count % 2;
                }
            });


        }

    }

    public void gameOver(){
        final Dialog exitGame = new Dialog(getContext());

        String res="";

        if(money1 > money2){
            res = "You Lose the Game.";
        }
        else{
            res = "You Win the Game.";
        }
        exitGame.setTitle(res);
        String result = "Computer  "+scoreComputer+"\n"+"Player  "+scorePlayer;
        Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT).show();
        exitGame.setCanceledOnTouchOutside(false);
        exitGame.show();

    }
    @SuppressLint("WrongCall")
    public void computerTurn(){
        if (connectedThread != null) {
            String status = "";
          //  status = status.concat(String.valueOf(money1) + ";" + String.valueOf(playerPos) + ";" + choice);
            byte[] ByteArray = status.getBytes();
            connectedThread.write(ByteArray);
        }
        dicenumber = 0;
        Random r = new Random();
        dicenumber1 = (r.nextInt(6) + 1);
        dicenumber += dicenumber1;
        dicenumber2 = (r.nextInt(6) + 1);
        dicenumber += dicenumber2;
        text = db.getCompany(Computer.playerPos);
        z = 1;
        while(z <= dicenumber) {
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
        if(text.contains("Vision")){

        }
        else {
            text = " Computer is on " + db.getCompany(Computer.playerPos);
        }
        smscomp.setText(text);
        computerDialog.show();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computerDialog.dismiss();
                if (money1 < 0) {
                    gameOver();
                }
            }
        });
        order=true;

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

    public class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private int cnt = 0;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e("e", "temp sockets not created", e);
            }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            Log.i("e", "BEGIN mConnectedThread");
            if (cnt == 0) {
                try {
                    byte[] ByteArray = BluetoothActivity.MyName.getBytes();
                    connectedThread.write(ByteArray);
                    cnt++;
                } catch (Exception e) {
                    Log.d("e", e.getMessage());
                }
            }
            byte[] buffer = new byte[1024];
            int bytes;
            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    Log.i("e", "BEGIN Listening");
                    // Read from the InputStream
                    String readMessage = "";
                    bytes = mmInStream.read(buffer);
                    readMessage = new String(buffer, 0, bytes);
                    Log.i("e", "Listening : " + readMessage);
                    if (readMessage.contains(";")) {
                        // Send the obtained bytes to the UI Activity
                       /* enemyMoney = (int)(readMessage.charAt(0)-48);
                        enemyPos = (int) ()

                        String str = "" + a[0][0] + a[0][1] + a[0][2] + a[1][0] + a[1][1] + a[1][2] + a[2][0] + a[2][1] + a[2][2] + ";" + turn;

                        Log.i("e", "GOT : " + str);

                        touchEnabled = true;

                        if (!oncewin && !oncedrawen) {
                            postInvalidate();
                            check();*/
                        }
                    /*} else if (readMessage.equals("REMATCH")) {
                        Log.d("e", "rematch");
                        oppontentRematch = true;
                        init();
                        postInvalidate();
                        TwoDevice2P.act_2p.runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(TwoDevice2P.act_2p, TwoDevice2P_names.MyName + " vs " + TwoDevice2P_names.OpponentName, Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    } else if (readMessage.equals("END")) {
                        break;
                    }*/ else {
                        try {
                            Log.i("e", "Hello");
                            BluetoothActivity.OpponentName = readMessage;
                           /* Log.i("e", TwoDevice2P_names.MyName + " vs " + TwoDevice2P_names.OpponentName);
                            TwoDevice2P.act_2p.runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(TwoDevice2P.act_2p, TwoDevice2P_names.MyName + " vs " + TwoDevice2P_names.OpponentName, Toast.LENGTH_SHORT).show();
                                        }
                                    }*/
                           // );
                        } catch (Exception e) {
                            Log.d("e", e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    //Log.e(TAG, "disconnected", e);
                    break;
                }
            }
        }

        public void write(byte[] buffer) {

                try {
                    Log.d("e", "Writing ");
                    mmOutStream.write(buffer);
                } catch (IOException e) {
                    Log.e("e", "Exception during write", e);
                }

        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e("e", "close() of connect socket failed", e);
            }
        }
    }
}





