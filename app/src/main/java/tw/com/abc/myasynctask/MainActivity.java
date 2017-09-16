package tw.com.abc.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //private TextView tv;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  tv=(TextView) findViewById(R.id.tv);

    }

    public void test(View view) {
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        //"brad","TCCA","OK"
    }
    //private class MyAsyncTask extends AsyncTask<String, Void, Void> {
    private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("geoff", "onPreExecute()!! ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.i("geoff", "onProgressUpdate()!! ");
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
            Log.i("geoff", "onCancelled(Void aVoid)!! ");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("geoff", "onCancelled()!! ");
        }

        @Override
        protected Void doInBackground(Void... params) {
        //protected Void doInBackground(String... params) {

            Log.i("geoff", "doInBackground()!!");


            return null;
        }
    }
}