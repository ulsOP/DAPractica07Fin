package com.example.usuario.dapractica07fin;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String [] datos={"toast","Toast Personalizado","SnackBar","Alert Bialog","Alert Dialog List","Notification","Big text notification"
    ,"Inbox Style Notification","Picture Notification","Progres Dialog","Progres Dialog Notification","Spinner"};

    final CharSequence[] colors={"Rojo","Negro","Azul","Naranja"};
    ArrayList<Integer> slist=new ArrayList();
    boolean icount[]=new boolean[colors.length];
    String msg= " ";
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    int id = 004;

    ListView listView;
    TextView texto;
    ProgressDialog progressDialog;
    private int progressStatus=0;
    private Handler handler=new Handler();

    public static final String NOTIFICACION= "NOTIFICACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView) findViewById(R.id.listView);
        texto=(TextView) findViewById(R.id.textView);

        listViewWork();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void listViewWork(){
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(getApplicationContext(), "Ejemplo de toast", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        LayoutInflater inflater=getLayoutInflater();
                        View layout = inflater.inflate((R.layout.toast_layout), (ViewGroup)findViewById(R.id.toasLinearLayout));


                        TextView tv= new TextView(MainActivity.this);
                        tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        tv.setTextColor(30);
                        tv.setTextSize(30);
                        tv.setPadding(10,10,10,10);
                        tv.setText("Ejemplo de Toast");

                        Toast toast=new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                        break;
                    case 2:
                        Snackbar.make(view,"Ejemplo de snackBar",Snackbar.LENGTH_LONG).setAction("Action",null).show();

                        break;
                    case 3:
                        showAlertDialog();
                        break;
                    case 4:
                        showAlertDialogList();
                        break;
                    case 5:
                        showNotification();
                        break;
                    case 6:
                        showNotificationBigText();
                        break;
                    case 7:
                        showNotificationInbox();
                        break;
                    case 8:
                        showNotificationPicture();
                        break;
                    case 9:
                        showProgressDialog();
                        break;
                    case 10:
                    showNotificationProgressDialog();
                        break;
                    case 11:
                        showSpinner();
                        break;
                }
            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Ejemplo de AlertDialog");
        dialog.setMessage("¿Deseas salir?");
        dialog.setIcon(android.R.drawable.ic_dialog_map);
        dialog.setCancelable(false);
        dialog.setNegativeButton("No quiero pnche madre", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.setPositiveButton("Si mi rey :D", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.show();
    }

    public void showAlertDialogList(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Elige colores").setMultiChoiceItems(colors, icount, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    slist.add(which);
                }
                else if(slist.contains(which)) {
                    slist.remove(Integer.valueOf(which));
                }
            }
        })
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        msg="";
                        for (int i=0;i<slist.size();i++){
                            msg=msg+"\n"+(i+1)+" : "+colors[slist.get(i)];
                        }
                        Toast.makeText(getApplicationContext(),"Total" +slist.size()+"Items seleccionados"+"\n"
                        +msg,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Nada seleccionado",Toast.LENGTH_SHORT).show();
                    }
                })
        .show();

    }


    public void showNotification(){
        int icono = R.drawable.ic_notification;
        CharSequence titulo = "Notificacion de Practica";
        CharSequence titulobar = "Barra de Notificacion";
        CharSequence texto = "Ejemplo de lanzamiento de notificacion Android";
        String txtoNotifica = "Saludos desde \n" +
                getResources().getString(R.string.app_name);
        Intent i = new Intent(getApplicationContext(),
                SecondActivity.class);
        i.putExtra(NOTIFICACION, txtoNotifica);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi =
                PendingIntent.getActivity(getApplicationContext(),
                        0, i, 0);
        Notification notification = new
                Notification.Builder(getApplicationContext())
                .setTicker(titulo)
                .setContentTitle(titulobar)
                .setContentText(texto)
                .setSmallIcon(icono)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        NotificationManager nm =
                (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, notification);


    }


    public void showNotificationBigText(){
        Bitmap icon =
                BitmapFactory.decodeResource(getResources(),
                        R.drawable.notification_icon);
        String textoNotifica = "La Facultad de Estudios Superiores Aragón es una entidad académica multidisciplinaria de la UNAM, ubicada en la zona norte del municipio de Nezahualcóyotl, Estado de México";
        //Asignar el estilo BigText a la nitificacion
        NotificationCompat.BigTextStyle bigText = new
                NotificationCompat.BigTextStyle();
        bigText.bigText(textoNotifica);
        bigText.setSummaryText("Por: FesAragon");
        NotificationCompat.Builder notification = new
                NotificationCompat.Builder(MainActivity.this, "")
                .setContentTitle("Big Text Notification Example")
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(icon)
                .setStyle(bigText);
        notification.setDefaults(Notification.DEFAULT_ALL);
        Intent i = new Intent(getApplicationContext(),
                SecondActivity.class);
        i.putExtra(NOTIFICACION, textoNotifica);
        PendingIntent pi =
                PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
        notification.setContentIntent(pi);
        // Asignar un ID a la Ntificacion
        int nId = 001;
        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        nm.notify(nId, notification.build());
    }



    public void showNotificationInbox(){
        NotificationCompat.InboxStyle iStyle = new
                NotificationCompat.InboxStyle();
        for (int m = 1; m <= 5; m++)
            iStyle.addLine("Nuevo Mensaje " + m);
        iStyle.setSummaryText("+2 mas");
        NotificationCompat.Builder notification = new
                NotificationCompat.Builder(getApplicationContext(), "")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Ejemplo Inbox Style Notification")
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setStyle(iStyle);
        Intent i = new Intent(getApplicationContext(),
                SecondActivity.class);
        PendingIntent pi =
                PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
        notification.setContentIntent(pi);
        int nId = 003;
        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        nm.notify(nId, notification.build());

    }

    public void showNotificationPicture(){
        NotificationCompat.BigPictureStyle bpStyle = new
                NotificationCompat.BigPictureStyle();

        bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(
        ), R.drawable.mujer)).build();
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.unam.mx/"));
        PendingIntent pi =
                PendingIntent.getActivity(MainActivity.this, 0, i, 0);
        NotificationCompat.Builder notification = new
                NotificationCompat.Builder(getApplicationContext(), "")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Ejemplo de Notificacion Big Picture")
                                .addAction(android.R.drawable.ic_menu_share,
                                        "Compartir", pi)
                                .setStyle(bpStyle)
                                .setDefaults(Notification.DEFAULT_ALL)
                                .setContentIntent(pi);
        int nId = 002;
        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        nm.notify(nId, notification.build());
    }



    public void showNotificationProgressDialog(){
        {
            mNotifyManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new
                    NotificationCompat.Builder(getApplicationContext(), "");
            mBuilder.setContentTitle("Descargar Archivo")
                    .setContentText("Descarga en Progreso")

                    .setSmallIcon(android.R.drawable.arrow_down_float);
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            int incr;
                            for (incr = 0; incr <= 100; incr+=5) {

                                mBuilder.setProgress(100, incr,
                                        false);
                                // Muestra el progress dialog por primera vez
                                mNotifyManager.notify(id,
                                        mBuilder.build());
                                // Duerme el hilo, simulando una operación
                                try {
                                    Thread.sleep(1*1000);
                                } catch (InterruptedException e) {
                                    Log.d("TAG", "sleep failure");
                                }
                            }

                            mBuilder.setContentText("Descarga Completa")
                                            // Quitar el ProgressBar
                                            .setProgress(0,0,false);
                            mNotifyManager.notify(id,
                                    mBuilder.build());
                        }
                    }
            ).start();
        }

    }

    public void showProgressDialog(){
        progressDialog= new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Descargando Archivo");
        progressDialog.setTitle("Ejemplo de Progress Dialog");

        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus <
                                progressDialog.getMax()){
                            try{
                                Thread.sleep(200);
                                progressStatus += 5;
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    progressDialog.setProgress(progressStatus);
                                }
                            });
                        }
                        progressDialog.dismiss();
                        progressStatus = 0;

                        progressDialog.setProgress(progressStatus);
                    }}
        ).start();

    }

    public void showSpinner(){
        Intent i = new Intent(MainActivity.this,ImageActivity.class);
    startActivity(i);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
