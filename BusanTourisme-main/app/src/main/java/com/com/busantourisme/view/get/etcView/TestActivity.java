package com.com.busantourisme.view.get.etcView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.com.busantourisme.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

public class TestActivity extends AppCompatActivity{


    private static final String LOG_TAG = "TestActivity";

    private TestActivity mContext = TestActivity.this;
    private MapView mapView;
    private ViewGroup mapViewContainer;
    private FloatingActionButton fBtnMyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // java code
        mapView = new MapView(mContext);
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);





        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(11, true);

        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), 9, true);

        // 줌 인
        mapView.zoomIn(true);

        // 줌 아웃
        mapView.zoomOut(true);

//        //마커 달기
//        MapPOIItem[] marker = new MapPOIItem[1];
//        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.53737528,127.00557633);
//        marker[0].setItemName("Default Marker");
//        marker[0].setTag(0);
//        marker[0].setMapPoint(MARKER_POINT);
//        marker[0].setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//        marker[0].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
//        //마커2 달기
//
//        MapPoint MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.545024,127.03923);
//        marker[1].setItemName("Default Marker");
//        marker[1].setTag(0);
//        marker[1].setMapPoint(MARKER_POINT2);
//        marker[1].setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//        marker[1].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
//        mapView.addPOIItems(marker);

        //마커 1
        MapPOIItem marker = new MapPOIItem();
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.53737528,127.00557633);
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);

//        //마커 2
//        MapPOIItem marker2 = new MapPOIItem();
//        MapPoint MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.545024,127.03923);
//        marker.setItemName("Default Marker");
//        marker.setTag(0);
//        marker.setMapPoint(MARKER_POINT2);
//        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
////        mapView.addPOIItem(marker2);
//
//        MapPOIItem [] markers = new MapPOIItem[2];
//        markers[0]=marker;
//        markers[1]=marker2;


//        for(int i=0;i<markers.length;i++){
//            mapView.addPOIItem(markers[i]);
//        }



        //검색 지점과 현재 내 위치간의 폴리라인 긋기
        MapPolyline polyline = new MapPolyline();
        polyline.setTag(1000);
        polyline.setLineColor(Color.argb(128, 255, 51, 0)); // Polyline 컬러 지정.

        // Polyline 좌표 지정.
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.537229, 127.005515));//현재 내위치 받기
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(37.545024,127.03923));//도착지 위치 받기(서버를 통해서 좌표를 받아야함)

        // Polyline 지도에 올리기.
        mapView.addPolyline(polyline);


        // 지도뷰의 중심좌표와 줌레벨을 Polyline이 모두 나오도록 조정.
        MapPointBounds mapPointBounds = new MapPointBounds(polyline.getMapPoints());
        int padding = 100; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));




    }

    public void initlr(){
        fBtnMyPosition.setOnClickListener(view -> {
            //인텐트 객체 생성
            Intent intent = new Intent(
                    mContext,
                    MyPositionActivity.class  //  메인 리사이클러뷰 액티비티로 이동 >>이동하기 원하는 액티비티로 수정 요망(  Login -> List)
            );
            startActivity(intent);
        });
    }


}