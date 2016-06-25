package myapplication.day34;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import myapplication.day34.beans.InfoMessages;
import myapplication.httplib.HttpHelper;
import myapplication.httplib.Request;
import myapplication.httplib.StringRequest;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView detail_title_tv,detail_keyword_tv,detail_data_tv,detail_tv;
    private ImageButton toolbar_collect,toolbar_share;
    private String[]colums={"id"};
    private int position;
    public InfoMessages infoMessage=new InfoMessages();
    public List<InfoMessages>infoMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_details);
       initView();
    }

    private void initView() {
        detail_title_tv= (TextView) findViewById(R.id.detail_title_tv);
        detail_keyword_tv= (TextView) findViewById(R.id.detail_keyword_tv);
        detail_data_tv= (TextView) findViewById(R.id.detail_tv);
        detail_tv= (TextView) findViewById(R.id.detail_tv);
        toolbar_collect= (ImageButton) findViewById(R.id.toolbar_collect);
        toolbar_share= (ImageButton) findViewById(R.id.toolbar_share);
        toolbar_collect.setOnClickListener(this);
        toolbar_share.setOnClickListener(this);
        Intent intent=getIntent();
        int id = intent.getIntExtra("id",0);
        String url = null;
        if (id!=0){
            url="http://www.tngou.net/api/info/show?id="+id;
        }
        Log.i("testi", "LineNum:55-->DetailsActivity-->initView: url:" + url);
        infoMessages=new ArrayList<InfoMessages>();
        StringRequest req=new StringRequest(url, Request.Method.GET, new Request.Callback<String>() {
            @Override
            public void onEror(Exception e) {
            }
            @Override
            public void onResonse(String response) {
                Log.i("testi", "LineNum:60-->DetailsActivity-->onResonse: json:" + response);
                try {
                    JSONObject object=new JSONObject(response);
//                    List<Info>listinfo=parseJSONList(object);
                 /*   final String msg= (String) object.get("message");*/
                    final String title= (String) object.get("title");
                    infoMessage.setTitle(title);
                    final String keywords= (String) object.get("keywords");
                    infoMessage.setKeywords(keywords);
                    final long time = object.getLong("time");
                    infoMessage.setTime(time + "");
                    final String date=new SimpleDateFormat("yyMMDD:hhmmss").format(time);
                    infoMessage.setData(date);
                    int id=object.getInt("id");

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            detail_title_tv.setText(title);
                            detail_keyword_tv.setText(keywords);
                             detail_tv.setText(time+"");
                            detail_data_tv.setText(date);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        HttpHelper.addRequest(req);
    }
    @Override
    public void onClick(View v) {

     switch (v.getId()){
         case R.id.toolbar_collect:
             Intent intent=new Intent();
             intent.setClass(DetailsActivity.this,AboutActivity.class);
             startActivity(intent);
             Toast.makeText(this,"收藏成功",Toast.LENGTH_LONG).show();
             break;
         case R.id.toolbar_share:
       break;

     }
    }
}

