package com.nutstudio.nutenglish;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.nutstudio.nutenglish.Tools.UITools;

public class MainActivity extends Activity {
    private MainFragment studyFragment = new MainFragment();
    private StartGameFragment startGameFragment = new StartGameFragment();
    private PeopleFragment peopleFragment = new PeopleFragment();
    private String appURL = "http://pan.baidu.com/share/link?shareid=1496095071&uk=1427289872";
    private LinearLayout mainLin, startgameLin, peopleLin;
    private ImageView topSearch, topMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new UITools(this, this).setStatusBar();
        if (savedInstanceState == null) {
            jump2fragment(studyFragment);
        }
        setContentView(R.layout.main);

        mainLin = (LinearLayout) this.findViewById(R.id.lin_main);
        startgameLin = (LinearLayout) this.findViewById(R.id.lin_game);
        peopleLin = (LinearLayout) this.findViewById(R.id.lin_people);
        topMenu = (ImageView) this.findViewById(R.id.im_top_menu);
        topSearch = (ImageView) this.findViewById(R.id.im_top_search);
        setBottomBar(mainLin);
        topSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                jumpActivty(MainActivity.this, SearchActivity.class);
            }
        });
        mainLin.setOnClickListener(new MainLinsenner());
        startgameLin.setOnClickListener(new MainLinsenner());
        peopleLin.setOnClickListener(new MainLinsenner());

    }

    private void jump2fragment(Fragment fragment) {
        if (!studyFragment.isAdded()) {
            getFragmentManager().beginTransaction().add(R.id.container, studyFragment).commit();
        }
        if (!startGameFragment.isAdded()) {
            getFragmentManager().beginTransaction().add(R.id.container, startGameFragment).commit();
        }
        if (!peopleFragment.isAdded()) {
            getFragmentManager().beginTransaction().add(R.id.container, peopleFragment).commit();
        }
        getFragmentManager().beginTransaction().
                hide(studyFragment).hide(startGameFragment).hide(peopleFragment).commit();
        getFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).show(fragment).commit();

     /*   getFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.open_enter, R.anim.open_exit, R.anim.close_enter, R.anim.close_exit).
                show(fragment).commit();*/

    }

    public void jumpActivty(Context context, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        startActivity(intent);
    }

    protected void myDialog() {
        AlertDialog.Builder exitDialog = new AlertDialog.Builder(this);
        exitDialog.setTitle("确定要退出吗？");
        exitDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface p1, int p2) {
                // TODO: Implement this method
                finish();
            }

        });
        exitDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface p1, int p2) {
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
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    // TODO: Implement this m
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            myDialog();
        }
        // TODO: Implement this method
        return super.onKeyDown(keyCode, event);

    }

    public void setBottomBar(LinearLayout lin) {
        lin.setBackgroundColor(getResources().getColor(R.color.main_color));

    }

    public class MainLinsenner implements OnClickListener {
        @Override
        public void onClick(View p1) {
            mainLin.setBackgroundColor(getResources().getColor(R.color.bottom_color));
            startgameLin.setBackgroundColor(getResources().getColor(R.color.bottom_color));
            peopleLin.setBackgroundColor(getResources().getColor(R.color.bottom_color));
            if (p1.getId() == R.id.lin_main) {
                if (studyFragment.isHidden()) {
                    jump2fragment(studyFragment);
                }
                setBottomBar(mainLin);
            } else if (p1.getId() == R.id.lin_game) {
                if (startGameFragment.isHidden()) {
                    jump2fragment(startGameFragment);

                }
                setBottomBar(startgameLin);
            } else if (p1.getId() == R.id.lin_people) {
                if (peopleFragment.isHidden()) {
                    jump2fragment(peopleFragment);

                }
                setBottomBar(peopleLin);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "分享");
        menu.add(1, 2, 1, "关于");
        menu.add(1, 3, 1, "退出");
        // TODO: Implement this method
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == 1) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text2) + appURL);
            startActivity(intent);

        } else if (item.getItemId() == 2) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == 3) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
