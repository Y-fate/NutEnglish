package com.nutstudio.nutenglish;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
public class MainActivity extends Activity
{
	private String appURL="http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
	private ImageView btn1;
	private LinearLayout drawimg,gameimg,otherimg;//guanwang;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		setStatusBar();
		getFragmentManager().beginTransaction().add(R.id.container, new StudyFragment(),"will").commit();
        setContentView(R.layout.main);
		//btn1=(ImageView)this.findViewById(R.id.mainButton1);
		drawimg=(LinearLayout)this.findViewById(R.id.main_draw);
		gameimg=(LinearLayout)this.findViewById(R.id.main_game);
		otherimg=(LinearLayout)this.findViewById(R.id.main_other);
		drawimg.setOnClickListener(new MainLinsenner());
		gameimg.setOnClickListener(new MainLinsenner());
		otherimg.setOnClickListener(new MainLinsenner());
    }
	private void jump2fragment(Fragment fragment) {
		Fragment fragment1 = getFragmentManager().findFragmentByTag("mian");
		Fragment fragment2 = getFragmentManager().findFragmentByTag("will");
		Fragment fragment3 = getFragmentManager().findFragmentByTag("history");
		getFragmentManager().beginTransaction().
				hide(fragment1).hide(fragment2).hide(fragment3).commit();
		getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).show(fragment).commit();
      /*  getFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.open_enter, R.anim.open_exit, R.anim.close_enter, R.anim.close_exit).
                replace(R.id.main_container, fragment).commit();*/

	}
	public void setStatusBar() {
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
							| WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
			);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					//   | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
			);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.text2));
			window.setNavigationBarColor(getResources().getColor(R.color.color1));
		}
	}
	public void jumpActivty(Context context,Class<?> cls){
		Intent intent=new Intent();
		intent.setClass(context,cls);
		startActivity(intent);
	}
	protected void myDialog(){
		AlertDialog.Builder exitDialog=new AlertDialog.Builder(this);
		exitDialog.setTitle("确定要退出吗？");
		exitDialog.setPositiveButton("确定", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					// TODO: Implement this method
					finish();
				}

		});
		exitDialog.setNegativeButton("取消", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					// TODO: Implement this method
				}		
		});
	exitDialog.show();	
	}
	@Override
	protected void onResume() {
		/**
		 * 设置为横屏
		 */
		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
			{
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		super.onResume();
	}
			// TODO: Implement this m
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK){
			myDialog();
		}
		// TODO: Implement this method
		return super.onKeyDown(keyCode, event);

	}

	public class MainLinsenner implements OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			if(p1.getId()==R.id.main_draw){
				jumpActivty(MainActivity.this,Maindraw.class);
			}
			else if(p1.getId()==R.id.main_game){
				jumpActivty(MainActivity.this,StarGame.class);
			}
			/*else if(p1.getId()==R.id.mainButton1){
				openOptionsMenu();
			}*/
			else if(p1.getId()==R.id.main_other){
				Intent intent=new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_text2)+appURL);
				startActivity(intent);
			}
			/*else if(p1.getId()==R.id.mainButton2){
				Uri uri=Uri.parse("http://m.feiltel.icoc.cc/");
				Intent intent=new Intent(Intent.ACTION_VIEW,uri);
				startActivity(intent);
			}*/
		}		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(1,1,1,"分享");
		menu.add(1,2,1,"关于");
		menu.add(1,3,1,"退出");
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if(item.getItemId()==1){
			
			Intent intent=new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_text2)+appURL);
			startActivity(intent);
			
		}
		else if(item.getItemId()==2){
			Intent intent=new Intent();
			intent.setClass(MainActivity.this,AboutActivity.class);
			startActivity(intent);
			
		}
		else if(item.getItemId()==3){
			finish();
		}
		return super.onOptionsItemSelected(item);
}}
