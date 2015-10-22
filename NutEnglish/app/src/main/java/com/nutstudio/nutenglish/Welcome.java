package com.nutstudio.nutenglish;
import android.app.*;
import android.widget.*;
import android.os.*;
import android.content.*;
import android.support.v4.view.*;
import java.util.*;
import android.view.*;
import android.view.View.*;

import com.nutstudio.nutenglish.Tools.UITools;

public class Welcome extends Activity
{
	private ImageView tab1btn,tab2btn,tab0btn;
	private TextView first2btn,welcombtn;
	private ViewPager vpager;
	private List<View> content;
	private LayoutInflater inflater;
	private MyAdapter mdapter;
	private boolean isclick=false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		new UITools(this,this).setStatusBar();
		SharedPreferences sharedPreferences= getSharedPreferences("test",Activity.MODE_PRIVATE); 
// 使用getString方法获得value，注意第2个参数是value的默认值 
		String name =sharedPreferences.getString("start_count","");
		if(name.equals("ab")){
			setContentView(R.layout.welcome);
			Start();	
		}else if(name.equals("")) {
			setContentView(R.layout.first_start);
			vpager=(ViewPager)this.findViewById(R.id.first_start_viewpager);
			inflater=LayoutInflater.from(this);	
			content=new ArrayList<View>();
			//给viewpager加载页面
			View view0=inflater.inflate(R.layout.first0,null); 
			View view1=inflater.inflate(R.layout.first1,null);   
			View view2=inflater.inflate(R.layout.first2,null);
			content.add(view0);
			content.add(view1);
			content.add(view2);
			mdapter=new MyAdapter();
			mdapter.notifyDataSetChanged();
			vpager.setAdapter(mdapter);
			startCount("ab");
			Toast.makeText(Welcome.this,"向左滑动进入!!",Toast.LENGTH_LONG).show();
			tab0btn=(ImageView)view0.findViewById(R.id.tab0btn1);
			tab1btn=(ImageView)view1.findViewById(R.id.tab1btn1);
			tab2btn=(ImageView)view2.findViewById(R.id.tab2btn1);
			first2btn=(TextView)view2.findViewById(R.id.first2Button1);
			tab0btn.setOnClickListener(new MyClick());
			tab1btn.setOnClickListener(new MyClick());
			tab2btn.setOnClickListener(new MyClick());
			first2btn.setOnClickListener(new MyClick());
			firstStart();
		}
    }
	public void firstStart(){
		SharedPreferences mySharedPreferences= getSharedPreferences("win",Activity.MODE_PRIVATE); 
//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
//用putString的方法保存数据 
		editor.putString("wincounter","0");
//提交当前数据 
		editor.commit(); 
	}
	public class MyClick implements OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			if(p1.getId()==R.id.tab0btn1||p1.getId()==R.id.tab1btn1||p1.getId()==R.id.tab2btn1||p1.getId()==R.id.first2Button1){
				Intent intent=new Intent();
				intent.setClass(Welcome.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}
	//保存数据
	public void startCount(String ab){
		SharedPreferences mySharedPreferences= getSharedPreferences("test",Activity.MODE_PRIVATE); 
//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
//用putString的方法保存数据 
		editor.putString("start_count",ab); 
//提交当前数据 
		editor.commit(); 
		
	}
	public  void Start() {
		new Thread() {
			public void run() {
					
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent();
				intent.setClass(Welcome.this, MainActivity.class);
				startActivity(intent);
				finish();		
			}
		}.start();
    }
	public class MyAdapter extends PagerAdapter
	{
		//viewpager适配器
		@Override
		public int getCount()
		{
			return content.size();
		}
		@Override
		public boolean isViewFromObject(View p1, Object p2)
		{
			return p1==p2;
		}
		@Override
		public Object instantiateItem(View container, int position)
		{
			View view=content.get(position);
			((ViewPager)container).addView(view);
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			((ViewPager)container).removeView(content.get(position));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}
}
