package com.example.itay.itayelbazmenudialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder adb;
    LinearLayout ll;
    final String[] colors={"Red","Green","Blue"};
    int[]color={0,0,0};
    Intent si;

    /**
     * @author Itay Elbaz
     * @since 6.7.2003
     * ll is the Linear Layout object
     * String[] is the color options
     * color[] is the rgb numbers array
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.ll);
    }

    /**
     * creates the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * when the button "credits" is clicked we move to the credits activity
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if (st.equals("credits")) {
            si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

    /**
     * This method changes the color of the screen with one option color which was clicked
     */

    public void RGB(View view) {
        color=new int[]{0,0,0};
        adb=new AlertDialog.Builder(this);
        adb.setTitle("COLOR CHOOSE");
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                color[which]=255;
                ll.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setNegativeButton("x", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad=adb.create();
        ad.show();
    }

    /**
     * This method changes the color of the screen with combination of options of colors which were clicked
     */
    public void MIXCOLOR(View view) {
        adb.setCancelable(false);
        adb=new AlertDialog.Builder(this);
        adb.setTitle("MIX COLOR CHOOSE");
        adb.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                    color[which]=255;
                else if (color[which]==255)
                    color[which]=0;
                ll.setBackgroundColor(Color.rgb(color[0],color[1],color[2]));
            }
        });
        adb.setNegativeButton("x", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad=adb.create();
        ad.show();
    }

    /**
     * This method clears the background color
     */
    public void CLEAR(View view) {
        ll.setBackgroundColor(Color.WHITE);
    }

    /**
     * This method get message in an alert dialog-edit text and shows the message on a toast
     */
    public void TOAST(View view) {
        adb=new AlertDialog.Builder(this);
        adb.setTitle("TOAST");
        final EditText et=new EditText(this);
        et.setHint("type anything");
        adb.setView(et);
        adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String st=et.getText().toString();
                Toast.makeText(MainActivity.this, ""+st, Toast.LENGTH_SHORT).show();
            }
        });
        adb.setNeutralButton("x", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setCancelable(false);
        AlertDialog ad=adb.create();
        ad.show();
    }
}