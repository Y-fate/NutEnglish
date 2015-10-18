package com.nutstudio.nutenglish;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import android.view.animation.*;
public class StarGame extends Activity
{
	private String appURL="http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
	private TextView winText;
	private ImageView  shareMyCounter;
	private Button chengHao,startgameimg;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stargame);
		shareMyCounter=(ImageView)this.findViewById(R.id.stargameshare);
		shareMyCounter.setOnClickListener(new gamelisenner());
		winText=(TextView)this.findViewById(R.id.stargameTextView1);
		startgameimg=(Button)this.findViewById(R.id.startgame);
		startgameimg.setOnClickListener(new gamelisenner());
		chengHao=(Button)this.findViewById(R.id.stargameButton1);
		chengHao.setText(getWinCounter());
	}
	public void setAnim(View view){
		Animation ro=new RotateAnimation(0,360);
		ro.setDuration(500);
		view.setAnimation(ro);
	}
	@Override
	protected void onResume()
	{	if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
		{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			
		}
		chengHao.setText(getWinCounter());
		// TODO: Implement this method
		super.onResume();
	}

	public String getWinCounter(){
		SharedPreferences sharedPreferences= getSharedPreferences("win",Activity.MODE_PRIVATE); 
		String winString=sharedPreferences.getString("wincounter","");
		return winString;
	}

	public void itemShare(String string){
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT,string);
		startActivity(intent);
	}
	public class gamelisenner implements OnClickListener
	{

		@Override
		public void onClick(View p1)
		{
			if(p1.getId()==R.id.startgame){
				Intent intent=new Intent();
				intent.setClass(StarGame.this,GameActivity.class);
				startActivity(intent);
			}
			else if(p1.getId()==R.id.stargameshare){
				itemShare("我在坚果英语中得到了"+getWinCounter()+"分，谁敢与我一战"+appURL);
				};
		}
		
		
	}
}
