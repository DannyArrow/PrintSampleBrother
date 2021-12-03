package com.example.printsamplebrother;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.brother.ptouch.sdk.Printer;
import com.brother.ptouch.sdk.PrinterInfo;
import com.brother.ptouch.sdk.PrinterStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private PrinterStatus printerResult2;
    Printer myprinter;
    PrinterInfo myprinterInfo;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        myprinter = new Printer();
        myprinterInfo = new PrinterInfo();



        LayoutInflater inflater = LayoutInflater.from(this);
        View secondView = inflater.inflate(R.layout.print_view, null, false);
        ConstraintLayout constraintLayout = secondView.findViewById(R.id.constrainlayout);
        imageView.setImageBitmap(getBitmapFromView(constraintLayout));


//        // set printer information
//        myprinterInfo.port = PrinterInfo.Port.USB;
//        myprinterInfo.paperSize = PrinterInfo.PaperSize.CUSTOM;
//        myprinterInfo.orientation = PrinterInfo.Orientation.LANDSCAPE;
//        myprinterInfo.valign = PrinterInfo.VAlign.MIDDLE;
//        myprinterInfo.align = PrinterInfo.Align.CENTER;
//        myprinterInfo.printMode = PrinterInfo.PrintMode.ORIGINAL;
//        myprinterInfo.numberOfCopies = 1;
//
//
//
//        // cutting method
//        myprinterInfo.printerModel = PrinterInfo.Model.QL_1100;
//        myprinterInfo.isAutoCut = true;
//        myprinterInfo.isCutAtEnd = false;
//        myprinterInfo.isHalfCut = false;
//        myprinterInfo.isSpecialTape = false;
//
//        myprinter.setPrinterInfo(myprinterInfo);


    }

    public void StartPrint(){
        PrinterThread printerThread = new PrinterThread();
        printerThread.start();
    }

    @SuppressLint("ResourceAsColor")
    public Bitmap getBitmapFromView(View view){

        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();


        Bitmap returnedBitmap = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgdrawable = view.getBackground();
        if(bgdrawable != null){
            bgdrawable.draw(canvas);
        }else {
            canvas.drawColor(android.R.color.white);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    protected class PrinterThread extends Thread{
        @Override
        public void run(){
            printerResult2 = new PrinterStatus();
            myprinter.startCommunication();
            //printerResult2 = myprinter.printPDF();

        }
    }
}