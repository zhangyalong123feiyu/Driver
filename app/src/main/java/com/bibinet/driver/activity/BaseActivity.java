package com.bibinet.driver.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.bibinet.driver.R;
import com.bibinet.driver.constant.Constants;
import com.jaeger.library.StatusBarUtil;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by bibinet on 2017-4-18.
 */

public class BaseActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(5);
      //  StatusBarUtil.setColor(BaseActivity.this,);
    }

    public void setLayout(int i) {
        setContentView(i);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.tr_void,R.anim.tr_void);
    }
    //创建一个检测权限是否授权的方法
    public boolean checkHasperisson(String... permissons){
        for(String permisson:permissons){
            if (ContextCompat.checkSelfPermission(this,permisson)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
    //创建一个申请权限的方法
    public void requesPermisson(int code,String... permissons){
        ActivityCompat.requestPermissions(this,permissons,code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.READPHONE_STATE:
                requestPhonePermisson();
                break;

            default:
                break;
        }
    }
    //请求手机状态的权限
    private void requestPhonePermisson() {

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.tr_entry,R.anim.tr_void);
    }

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
        overridePendingTransition(R.anim.tr_void,R.anim.tr_exit);
    }
}
