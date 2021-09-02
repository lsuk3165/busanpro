package com.com.busantourisme.view.bar;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.com.busantourisme.R;
import com.com.busantourisme.view.auth.JoinActivity;
import com.com.busantourisme.view.auth.LoginActivity;
import com.com.busantourisme.view.get.area.GijangActivity;
import com.com.busantourisme.view.get.area.HeaundaeActivity;
import com.com.busantourisme.view.get.area.JinguActivity;
import com.com.busantourisme.view.get.area.NamguActivity;

public class AppBarActivity extends AppCompatActivity {

    private static final String TAG = "CustomAppBarActivity";
    private Context mContext = this;
    //자신만 호출
    protected void onAppBarSettings(boolean isBackButton) {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(isBackButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itLogin:
                    Intent intent1 = new Intent(
                            mContext,
                            LoginActivity.class
                    );
                    startActivity(intent1);
                return true;
            case R.id.itSign:
                Intent intent2 = new Intent(
                        mContext,
                        JoinActivity.class
                );
                startActivity(intent2);
                return true;
            case R.id.itHeaundae:
                Intent intent3 = new Intent(
                        mContext,
                        HeaundaeActivity.class
                );
                startActivity(intent3);
                return true;
            case R.id.itJingu:
                Intent intent4 = new Intent(
                        mContext,
                        JinguActivity.class
                );
                startActivity(intent4);
                return true;
            case R.id.itNamgu:
                Intent intent5 = new Intent(
                        mContext,
                        NamguActivity.class
                );
                startActivity(intent5);
                return true;
            case R.id.itGijjang:
                Intent intent6 = new Intent(
                        mContext,
                        GijangActivity.class
                );
                startActivity(intent6);
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }

    }
}