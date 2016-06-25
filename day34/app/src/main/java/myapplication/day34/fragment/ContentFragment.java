package myapplication.day34.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import myapplication.day34.DetailsActivity;
import myapplication.day34.R;
import myapplication.day34.adapter.InfoListAdapter;
import myapplication.day34.beans.Info;
import myapplication.httplib.HttpHelper;
import myapplication.httplib.Request;
import myapplication.httplib.StringRequest;


public class ContentFragment extends Fragment {

	private ListView mLv;
	private int class_Id;
	private PtrClassicFrameLayout mRefreshView;
	private InfoListAdapter adapter;
	private List<Info> infos = new ArrayList<>();


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle bundle) {
		View view = View.inflate(getActivity(), R.layout.fragment,null);
		initView(view);
		class_Id =getArguments().getInt("id");
		if (bundle != null){
			Info[] ins = (Info[]) bundle.getParcelableArray("cache");
			if (ins != null && ins.length != 0){
				infos = Arrays.asList(ins);
				adapter = new InfoListAdapter(infos);
				mLv.setAdapter(adapter);
			}else {
				getDataFromNetwork();
			}
		}else {
			getDataFromNetwork();
		}

		return view;
	}

	private void initView(View view){
		mLv = (ListView) view.findViewById(R.id.frag_content_lv);
		mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent =new Intent(getActivity(), DetailsActivity.class);
				Log.i("testi", "LineNum:74-->ContentFragment-->onItemClick:position:" + position + ", infos:" + infos.toString());
				Info info = infos.get(position);
				int mID = (int) info.getId();
				Log.i("testi", "LineNum:74-->ContentFragment-->onItemClick: info_id:" + info.getId()+"--"+mID);
				intent.putExtra("id",mID);
				startActivity(intent);
			}
		});
		mRefreshView = (PtrClassicFrameLayout) view.findViewById(R.id.rotate_header_list_view_frame);
		mRefreshView.setResistance(1.7f);
		mRefreshView.setRatioOfHeaderHeightToRefresh(1.2f);
		mRefreshView.setDurationToClose(200);
		mRefreshView.setDurationToCloseHeader(1000);
		// default is false
		mRefreshView.setPullToRefresh(true);
		// default is true
		mRefreshView.setKeepHeaderWhenRefresh(true);
		mRefreshView.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
			}

			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				getDataFromNetwork();
			}
		});
	}


	private void getDataFromNetwork(){
		String url = "http://www.tngou.net/api/info/list?id="+class_Id;
		Log.i("testi", "LineNum:107-->ContentFragment-->getDataFromNetwork: url:" + url);
		StringRequest req = new StringRequest(url, Request.Method.GET, new Request.Callback<String>() {
			@Override
			public void onEror(Exception e) {

			}

			@Override
			public void onResonse(String response) {

				try {
					JSONObject jsonObject = new JSONObject(response);
					List<Info> listinfo = parseJson2List(jsonObject);
					if (listinfo != null){
						infos.clear();
						infos.addAll(listinfo);
						Log.i("testi", "LineNum:122-->ContentFragment-->onResonse: infos:" + infos.toString());
						if (adapter == null){
							adapter = new InfoListAdapter(infos);
							getActivity().runOnUiThread(new Runnable() {
								@Override
								public void run() {
									mLv.setAdapter(adapter);
								}
							});
						}else {
							getActivity().runOnUiThread(new Runnable() {
								@Override
								public void run() {
									adapter.notifyDataSetChanged();
								}
							});

						}
						Log.i("testi", "LineNum:140-->ContentFragment-->onResonse: infos:" + infos);
					}
				} catch (JSONException e) {
					e.printStackTrace();

				}
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mRefreshView.refreshComplete();
					}
				});

			}
		});

		HttpHelper.addRequest(req);
	}


	private List<Info> parseJson2List(JSONObject jsonObject) throws JSONException {

		if (jsonObject == null)return  null;
		JSONArray array = jsonObject.getJSONArray("tngou");
		if (array== null ||array.length() ==0)return null;

		List<Info> list = new ArrayList<>();
		int len = array.length();
		JSONObject obj = null;
		Info info =null;
		for (int i = 0; i <len ; i++) {
			obj = array.getJSONObject(i);
			info = new Info();
			info.setDescription(obj.optString("description"));
			info.setRcount(obj.optInt("rcount"));
			long time = obj.getLong("time");
			String str = new SimpleDateFormat("yyyyMMdd:hhmmss").format(time);
			info.setTime(str);
			info.setImg(obj.optString("img"));
			info.setId(obj.optInt("id"));
			list.add(info);
		}

		return list;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (infos == null || infos.size() == 0) return;
		Info[] parce = new Info[infos.size()];
		infos.toArray(parce);
		outState.putParcelableArray("cache", (Parcelable[]) parce);
	}

}
