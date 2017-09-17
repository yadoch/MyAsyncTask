package tw.com.abc.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAsyncTask myAsyncTask;
    private TextView tv;
    //private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView) findViewById(R.id.tv);

    }

    public void test1(View view) {
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("brad","TCCA","OK","a","b","c");
        // 造成Cancel 不能正常運作的程式區段

//        for(int i=0;i<10;i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void test2(View view){
        Log.i("geoff","test2");
        if(myAsyncTask!=null && !myAsyncTask.isCancelled()){
            Log.i("geoff","test!!!");
            myAsyncTask.cancel(true);
        }
    }

    private class MyAsyncTask extends AsyncTask<String,Integer, String> {
        //private class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        /*
        @Override
        protected Void onPostExecute(String result) {

            super.onPostExecute(result);

        }
        */
        // i放到MainActivity 會造成Cancel 無法正常運作的狀況
        private int i;
        @Override
        // 傳遞參數三
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.i("geoff", "onPostExecute()!! :" + result);
            tv.setText(result);
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("geoff", "onPreExecute()!! ");
        }

        @Override
        // 傳遞參數二
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            StringBuffer sb = new StringBuffer();
            for (Integer i : values) {
                sb.append(i+" \n");
                //        Log.i("geoff", "onProgressUpdate()!!: ");
            }
            tv.setText(sb);
        }
        // 改為String
        @Override
        protected void onCancelled(String error) {
            super.onCancelled(error);
            Log.i("geoff", "onCancelled(Void aVoid)!! "+error);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("geoff", "onCancelled()!! ");
        }

        @Override
        // 傳遞參數一
        //protected Void doInBackground(Void... params) {
        protected String doInBackground(String... params) {
            String result = "OK";
            for(String name : params) {
                i++;
                Log.i("geoff", "doInBackground()!!"+name);
                //會觸發 onProgressUpdate() 的生命週期
                publishProgress(i,i+10,i+100);
                if(isCancelled()) {
                    result="NOT OK";

                    break;
                }else{
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return result;
        }
    }
}