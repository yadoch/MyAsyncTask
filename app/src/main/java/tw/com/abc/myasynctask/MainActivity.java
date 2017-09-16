package tw.com.abc.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private MyAsyncTask myAsyncTask;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.tv);

    }

    public void test(View view) {
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("brad","TCCA","OK");
        //
    }
    private class MyAsyncTask extends AsyncTask<String,Integer, Void> {
    //private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
/*
        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            Log.i("geoff", "onPostExecute()!! ");
        }
*/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("geoff", "onPreExecute()!! ");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            StringBuffer sb = new StringBuffer();
            for (Integer i : values) {
                sb.append(i+" \n");
        //        Log.i("geoff", "onProgressUpdate()!!: ");
            }
            tv.setText(sb);
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
        //protected Void doInBackground(Void... params) {
        protected Void doInBackground(String... params) {
            for(String name : params) {
                i++;
                Log.i("geoff", "doInBackground()!!"+name);
                //會觸發 onProgressUpdate() 的生命週期
                publishProgress(i,i+10,i+100);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}