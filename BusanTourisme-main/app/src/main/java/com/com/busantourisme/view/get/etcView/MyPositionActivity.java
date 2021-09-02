package com.com.busantourisme.view.get.etcView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.com.busantourisme.R;
import com.com.busantourisme.controller.TourController;
import com.com.busantourisme.util.GpsTracker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyPositionActivity extends AppCompatActivity {

    private static final String TAG = "MyPositionActivity";

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    //kakaomap://route?sp=37.537229,127.005515&ep=37.4979502,127.0276368&by=PUBLICTRANSIT 예제

    private Context mContext = MyPositionActivity.this;
    private String testurl = "kakaomap://route?sp=37.537229,127.005515&ep=37.4979502,127.0276368&by=PUBLICTRANSIT";
    private String urlForUsing;
    //내 위치 위,경도
    private GpsTracker gpsTracker;
    double latitude;
    double longitude;
    //필요시 String address = getCurrentAddress(latitude, longitude); 대한민국 서울시 종로구 ~~
    //내 위치 위,경도
    private String[] myCoords = new String[2];
    
    //도착지 위,경도
    private String[] destCoords = new String[2];
//    //커스텀해서 만드면 됨!
//    private String urlMoreSimle = "kakaomap://route?sp=" + myCoords[0] + "," + myCoords[1] + "&ep=" + destCoords[0] + "," + destCoords[1] + "&by=PUBLICTRANSIT";

    private static int tourId;

    private MapView mapView;
    private ViewGroup mapViewContainer;

    private LocationManager locationManager;
    
    
    //컨트롤러
    private TourController tourController;
    
    //내 위치
    private FloatingActionButton btnMyPosition;
    //내 위치에서부터 목적지 경로까지 찾기 버튼
    private FloatingActionButton btnFindRoute;
    //내 위치에서터 목적지까지 대중교통 찾기
    private FloatingActionButton btnPublicTransport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        Log.d(TAG, "onCreate: 현재 사용 url "+ urlForUsing);
    }

    @Override
    protected void onStart() {
        super.onStart();
        init(); //초기화 함수
        setContentView(R.layout.activity_my_position);
        setMyPositionAndDestPostion(); //내 위치 셋팅함수 <-initLr보다 먼저와야 내 위치가 변경됨.

        initLr();   //리스너 초기화함수
        Log.d(TAG, "onResume: 현재 url"+urlForUsing);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForUsing));
        startActivity(intent);



    }

    //초기화 메서드
    public void init() {


        btnMyPosition = findViewById(R.id.fBtnMyPosition);// 내 위치 플로팅버튼
        btnFindRoute = findViewById(R.id.fBtnFindRoute); // 내 위치에서 도착지까지 플로팅버튼
        btnPublicTransport = findViewById(R.id.fBtnPublicTransport); //내 위치에서 도착지까지 대중교통 플로팅버튼
//        tourController = new TourController();


    }



    public void initLr() {

//        //현재 내 좌표와 도착지 좌표를 입력받아서 카카오맵 대중교통 api를 실행
//        btnPublicTransport.setOnClickListener(view -> {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForUsing));
//            startActivity(intent);
//
//        });
//
//        //현재 내 좌표와 도착지 좌표를 입력받아서 카카오맵 경로찾기 api를 실행하는 메소드
//        btnFindRoute.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlForUsing));
//            startActivity(intent);
//
//        });
//
//

    }

    public void setMyPositionAndDestPostion(){
        if (!checkLocationServicesStatus()) {  //위치정보 서비스 상태확인이 안될 경우

            showDialogForLocationServiceSetting(); //다이얼로그를 띄우는 함수
        }
        checkRunTimePermission(); //사용자 위치정보 접근 권한을 받음
        gpsTracker = new GpsTracker(mContext);
        latitude = gpsTracker.getLatitude();
        longitude = gpsTracker.getLongitude();
        Log.d(TAG, "onCreate: "+latitude+","+longitude);
        myCoords[0]=String.valueOf(latitude);
        myCoords[1]=String.valueOf(longitude);
        Log.d(TAG, "onCreate: "+myCoords[0]+","+myCoords[1]);

        Intent getIntent = getIntent();
        tourId = getIntent.getIntExtra("tourId",0);
        destCoords[0] = getIntent.getStringExtra("destLat");
        destCoords[1] = getIntent.getStringExtra("destLng");
        if(tourId == 0&&destCoords[0]==null&&destCoords[1]==null) finish();
        Log.d(TAG, "detail액티비티로부터 넘어온 데이터값: tourID: "+tourId);
        Log.d(TAG, "detail액티비티로부터 넘어온 데이터값 도착지 경도"+destCoords[0]);
        Log.d(TAG, "detail액티비티로부터 넘어온 데이터값 도착지 위도"+destCoords[1]);
        urlForUsing="kakaomap://route?sp=" + myCoords[0] + "," + myCoords[1] + "&ep=" + destCoords[0] + "," + destCoords[1] + "&by=PUBLICTRANSIT";
        Log.d(TAG, "수정된 최종 url:  "+urlForUsing);



//통신할 경우
//        tourController.findById(tourId).enqueue(new Callback<CMRespDto<Tour>>() {
//
//            @Override
//            public void onResponse(Call<CMRespDto<Tour>> call, Response<CMRespDto<Tour>> response) {
//
//
//                CMRespDto<Tour> cm = response.body();
//                Headers headers = response.headers();
//
//
//                //응답 객체 바디내용 확인용 로그
//                Log.d(TAG, "onResponse: cm.getCode()"+cm.getCode());
//                Log.d(TAG, "onResponse: cm.getData()"+cm.getData().getLat()+":"+cm.getData().getLng());
//                //응답 객체 헤더내용 확인용 로그(토큰 확인)
//                Log.d(TAG, "onResponse: 토큰 : "+headers.get("Authorization"));
//                destCoords[0]=cm.getData().getLat();
//                destCoords[1]=cm.getData().getLng();
//                urlForUsing=urlMoreSimle;
//            }
//
//            @Override
//            public void onFailure(Call<CMRespDto<Tour>> call, Throwable t) {
//                t.printStackTrace();
//
//            }
//        });

    }

    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(MyPositionActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                } else {

                    Toast.makeText(MyPositionActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MyPositionActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MyPositionActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(MyPositionActivity.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(MyPositionActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MyPositionActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(MyPositionActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MyPositionActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }






}

