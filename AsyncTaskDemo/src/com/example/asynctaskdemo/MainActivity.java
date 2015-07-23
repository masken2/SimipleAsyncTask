package com.example.asynctaskdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author mask
 * @date 2015-7-23
 * @category 简单异步线程案列
 */

public class MainActivity extends Activity {

	private Button Start;
	private ProgressBar bar;
	private TextView percent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	/**
	 * 
	 *@author Administrator  
	 *@date 2015-7-23
	 */
	private void init() {
		Start = (Button) findViewById(R.id.start);
		bar = (ProgressBar) findViewById(R.id.bar);
		percent = (TextView) findViewById(R.id.percent);
		Start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MyAsyncTask asyncTask = new MyAsyncTask();
				asyncTask.execute();
				
			}
		});
	}

	public class MyAsyncTask extends AsyncTask<Object, Integer, String> {

		@Override
		protected void onPreExecute() {
			bar.setVisibility(View.VISIBLE);
			percent.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Object... arg0) {
			for (int i = 0; i < 100; i+=10) {
				System.out.println("doInBackground:"+Thread.currentThread().getName());
				publishProgress(i);//执行
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			bar.setProgress(values[0]);
			percent.setText(values[0]+"%");
		}

		@Override
		protected void onPostExecute(String result) {
			bar.setVisibility(View.GONE);
			percent.setVisibility(View.GONE);
			super.onPostExecute(result);
		}
	}

}
