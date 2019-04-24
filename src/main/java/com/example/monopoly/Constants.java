package com.example.monopoly;

import android.content.Context;
import android.view.Display;
import android.view.SurfaceView;
import android.view.WindowManager;


public final class Constants extends SurfaceView {
    private static String[] company;
    private static String[] company_owner;
    private static int money[];
    private static int rent[];
    private static int positionXArray[];
    private static int positionYArray[];
    private static int ax,ay;
    private static int diffX;
    private static int diffY;
    private DatabaseHelper db;


    Constants(Context context) {
        super(context);
        db=new DatabaseHelper(context);
        WindowManager wm=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display=wm.getDefaultDisplay();
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

        money = new int[36];
        rent = new int[36];
        // complete the money array
        money[0] = 0;rent[0]=0;
        money[1] = 50;rent[1]=5;
        money[2] = 0;rent[2]=0;
        money[3] = 50;rent[3]=5;
        money[4] = 0;rent[4]=0;
        money[5] = 100;rent[5]=10;
        money[6] = 0;rent[6]=0;
        money[7] = 100;rent[7]=10;
        money[8] = 100;rent[8]=10;
        money[9] = 100;rent[9]=0;
        money[10] = 150;rent[10]=15;
        money[11] = 150;rent[11]=15;
        money[12] = 150;rent[12]=15;
        money[13] = 150;rent[13]=15;
        money[14] = 200;rent[14]=20;
        money[15] = 0;rent[15]=0;
        money[16] = 200;rent[16]=20;
        money[17] = 200;rent[17]=20;
        money[18] = 0;rent[18]=0;
        money[19] = 250;rent[19]=25;
        money[20] = 250;rent[20]=25;
        money[21] = 0;rent[21]=0;
        money[22] = 250;rent[22]=25;
        money[23] = 300;rent[23]=30;
        money[24] = 0;rent[24]=0;
        money[25] = 300;rent[25]=30;
        money[26] = 300;rent[26]=30;
        money[27] = 0;rent[27]=0;
        money[28] = 350;rent[28]=35;
        money[29] = 350;rent[29]=35;
        money[30] = 150;rent[30]=15;
        money[31] = 350;rent[31]=35;
        money[32] = 0;rent[32]=0;
        money[33] = 400;rent[33]=40;
        money[34] = 0;rent[34]=0;
        money[35] = 400;rent[35]=40;

        int w=display.getHeight();
        int h=display.getWidth();

        ax = ( ((h / 12) * 10)/12)*11;
        ay = (w/12)*10;

        diffX = ( ((h / 12) * 10)/12);
        diffY = w/12;

        positionXArray = new int[36];

        positionXArray[0] = ax;
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

        positionYArray[0] = ay;
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

        initDatabase();


    }


    private void initDatabase(){
        db.deleteAll();
        for(int i=0;i<36;i++){
            db.addCompanies(company[i], money[i], i, rent[i], company_owner[i]);
        }

    }

    public static int[] getPositionXArray() {
        return positionXArray;
    }

    public static int[] getPositionYArray() {
        return positionYArray;
    }

    public static int getAx() {
        return ax;
    }

    public static int getAy() {
        return ay;
    }
}
