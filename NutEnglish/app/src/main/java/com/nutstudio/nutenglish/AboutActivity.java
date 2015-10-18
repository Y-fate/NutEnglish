package com.nutstudio.nutenglish;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class AboutActivity extends Activity
{
	private String appURL="http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
	private Button erweima,guanwang;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		erweima=(Button)this.findViewById(R.id.erweima);
		erweima.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					Toast.makeText(AboutActivity.this,"扫描二维码，或者长按下载新版APP。",Toast.LENGTH_SHORT).show();
					// TODO: Implement this method
				}
				
			
		});
		erweima.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{

					Uri uri=Uri.parse(appURL);
					Intent intent=new Intent(Intent.ACTION_VIEW,uri);
					startActivity(intent);
			
				
					// TODO: Implement this method
					return false;
				}
				
			
		});
		guanwang=(Button)this.findViewById(R.id.guanwang);
		guanwang.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					
					 Uri uri=Uri.parse("http://m.feiltel.icoc.cc/");
					 Intent intent=new Intent(Intent.ACTION_VIEW,uri);
					 startActivity(intent);
					 
					// TODO: Implement this method
				}
				
			
		});
		
		 
	}
	
}
