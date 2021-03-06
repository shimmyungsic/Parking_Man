package com.cookandroid.parking_man;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class Parking_main extends AppCompatActivity {

    private String[] items = {"현대백화점 무역센터점 (임시A)","현대백화점 앞구정점 (임시 B)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parking_main);

        Button btn_local_naxt,btn_search,btn_star;
        final AutoCompleteTextView autotext_search;

        btn_local_naxt = (Button)findViewById(R.id.btn_local_naxt);
        btn_search = (Button)findViewById(R.id.btn_search);
        btn_star = (Button)findViewById(R.id.btn_star);
        autotext_search = (AutoCompleteTextView) findViewById(R.id.autotext_search);


        // 자동완성 텍스트뷰 가 출력되는 방식과 안에 들어갈 목록이 들어갑니다 목록은 위에선언한 String[] items 입니다
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,items);
        autotext_search.setAdapter(adapter);



        // 지역선택 클릭 이벤트
        btn_local_naxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Parking_local_select.class);
                startActivity(Intent);
                // 화면 전환 애니메이션입니다.
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        // 돋보기검색 아이콘 클릭 이벤트
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String word = autotext_search.getText().toString();
                if(word.equals(items[0])== true ) // 무역센터 선택시 이동
                {
                    Intent Intent = new Intent(getApplicationContext(),Parking_hyundai_mu_select.class);
                    startActivity(Intent);
                }
                else if (word.equals(items[1])== true) // 앞구정점 선택시 이동
                {
                    Intent Intent = new Intent(getApplicationContext(),Parking_hyundai_ap_select.class);
                    startActivity(Intent);
                }
                else // 다른값 넣을때 대화상자 출력
                {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Parking_main.this);
                    dlg.setTitle("잘못된 검색");
                    dlg.setMessage("검색된 주차장이 없습니다.");
                    dlg.setPositiveButton("확인", null);
                    dlg.show();
                }

            }
        });


        // 즐겨찾기 클릭 이벤트
        btn_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(),Parking_favorites.class);
                startActivity(Intent);
            }
        });



    }

    // 취소버튼 동작 코드입니다
    private long backKeyPressedTime = 0;
    private Toast toast;
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
            System.runFinalization();
            System.exit(0);
        }
    }
}
