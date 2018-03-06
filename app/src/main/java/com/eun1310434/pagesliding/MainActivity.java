/*===============================================================================
06.03.2018
eun1310434@naver.com
https://blog.naver.com/eun1310434
참고) Do it android app programming
===============================================================================*/
package com.eun1310434.pagesliding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //페이지가 열려 있는지 알기 위한 플래그
    boolean is_Right_PageOpen = false;
    boolean is_Left_PageOpen = false;

    //애니메이션 객체
    Animation translateLeftAnim_Right;
    Animation translateRightAnim_Right;
    Animation translateLeftAnim_Left;
    Animation translateRightAnim_Left;

    //슬라이딩으로 보여지는 페이지 레이아웃
    LinearLayout page_right;
    LinearLayout page_left;

    //버튼
    Button button_left;
    Button button_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_left = (Button) findViewById(R.id.button_left);
        button_right = (Button) findViewById(R.id.button_right);

        // 슬라이딩으로 보여질 레이아웃 객체 참조
        page_right = (LinearLayout) findViewById(R.id.page_right);
        page_left = (LinearLayout) findViewById(R.id.page_left);

        // 애니메이션 객체 로딩
        translateLeftAnim_Right = AnimationUtils.loadAnimation(this, R.anim.right_translate_left); //오른쪽 위치, 왼쪽으로 이동
        translateRightAnim_Right = AnimationUtils.loadAnimation(this, R.anim.right_translate_right);//오른쪽 위치, 오른쪽으로 이동
        // 애니메이션 객체에 리스너 설정
        SlidingPageAnimationListener_Right animListenerRight = new SlidingPageAnimationListener_Right();
        translateRightAnim_Right.setAnimationListener(animListenerRight);
        translateLeftAnim_Right.setAnimationListener(animListenerRight);


        // 애니메이션 객체 로딩
        translateLeftAnim_Left = AnimationUtils.loadAnimation(this, R.anim.left_translate_right);//왼쪽 위치, 오른쪽으로 이동
        translateRightAnim_Left = AnimationUtils.loadAnimation(this, R.anim.left_translate_left);//왼쪽 위치, 왼쪽으로 이동
        SlidingPageAnimationListener_Left animListenerLeft = new SlidingPageAnimationListener_Left();
        translateRightAnim_Left.setAnimationListener(animListenerLeft);
        translateLeftAnim_Left.setAnimationListener(animListenerLeft);
    }

    public void onButton1Clicked(View v) {
        // 애니메이션 적용
        if (is_Right_PageOpen) {
            page_right.startAnimation(translateRightAnim_Right);
        } else {
            page_right.setVisibility(View.VISIBLE);
            page_right.startAnimation(translateLeftAnim_Right);
        }
    }

    public void onButton2Clicked(View v) {
        // 애니메이션 적용
        if (is_Left_PageOpen) {
            page_left.startAnimation(translateRightAnim_Left);
        } else {
            page_left.setVisibility(View.VISIBLE);
            page_left.startAnimation(translateLeftAnim_Left);
        }
    }

    //애니메이션 리스너 정의
    private class SlidingPageAnimationListener_Right implements Animation.AnimationListener {
        //애니메이션이 끝날 때 호출되는 메소드
        public void onAnimationEnd(Animation animation) {
            if (is_Right_PageOpen) {//열린것을 닫음
                page_right.setVisibility(View.INVISIBLE);
                button_right.setText("Right Open");
                is_Right_PageOpen = false;
            } else {//닫은것을 열음
                button_right.setText("Right Close");
                is_Right_PageOpen = true;
            }
        }
        public void onAnimationRepeat(Animation animation) {}

        public void onAnimationStart(Animation animation) {}

    }

    //애니메이션 리스너 정의
    private class SlidingPageAnimationListener_Left implements Animation.AnimationListener {
        //애니메이션이 끝날 때 호출되는 메소드
        public void onAnimationEnd(Animation animation) {
            if (is_Left_PageOpen) {//열린것을 닫음
                page_left.setVisibility(View.INVISIBLE);
                button_left.setText("Left Open");
                is_Left_PageOpen = false;
            } else {//닫은것을 열음
                button_left.setText("Left Close");
                is_Left_PageOpen = true;
            }
        }
        public void onAnimationRepeat(Animation animation) {}

        public void onAnimationStart(Animation animation) {}

    }
}
