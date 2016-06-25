package myapplication.day34;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import myapplication.day34.beans.TabInfo;
import myapplication.day34.fragment.ContentFragment;



public class HomeActivity extends FragmentActivity {

	private static final String TAG = HomeActivity.class.getSimpleName();
	private TabLayout mTabs;
	private ViewPager viewpager;
	private TabInfo[] tabs = new TabInfo[]{

			new TabInfo("社会热点",6),
			new TabInfo("企业要闻",1),
			new TabInfo("医疗新闻",2),
			new TabInfo("生活贴士",3),
			new TabInfo("药品新闻",4),
			new TabInfo("疾病快讯",7),
			new TabInfo("食品新闻",5)
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	//先找到
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu_toobr, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {


		int id=item.getItemId();
		if(id==R.menu.menu_toobr){
			Intent intent=new Intent();
			intent.setClass(HomeActivity.this,AboutActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	private void initView(){
		mTabs = (TabLayout) findViewById(R.id.home_tbout);
		viewpager = (ViewPager) findViewById(R.id.home_vp);
		viewpager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
		mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
		mTabs.setupWithViewPager(viewpager);
	}

	public class ContentAdapter extends FragmentStatePagerAdapter {

		public ContentAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Log.d(TAG, "getPageTitle() returned: position=" +position );
			ContentFragment cf = new ContentFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("id",tabs[position].class_id);
			cf.setArguments(bundle);
			return cf;
		}

		@Override
		public int getCount() {
			return tabs.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {

			return tabs[position].name;



		}
	}
}
