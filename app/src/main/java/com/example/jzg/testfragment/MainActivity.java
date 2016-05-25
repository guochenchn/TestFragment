package com.example.jzg.testfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 实现了三种方法传值
     * 1.通过Tag值的方法得到当前显示的Fragment对象,通过暴露方法的方式传值
     * 2.通过Bundle传值给Fragment传值,Bundle有机会可以看下源码,相当强大,想传什么都行
     * 3.在Fragment里面得到Activity回调传值
     * 4.这一种没有写,你要在XML文件里面写死,在Activity通过findById的方法也可以得到Fragment的实例.自己百度
     * 你们可能都是用的repalce来替换fragment切换Fragment,这种方法有点弊端就是用replace他每次都会调用onCreateView
     * 这个方法遍历布局文件,这是很耗内存的,你可以自己打印研究一下,通过对源码的分析,建议用我这种写法,hide-add-show
     * 的方法显示,如果被替换的Fragment无须再次使用，可以使用replace方法。
     * Fragment与Fragment之间是不能传值的,官方推荐做法是：先Fragment1跟它的Activity通信，可以在Fragment1类中定义一个接口，并在Activity中实现该接口，然后在Activity中与Fragment2通信。
     * 这些差不多够你面试用了,面试官指不定还用的replace方法呢
     */

    private FrameLayout frameLayout;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private static final String TAG_ONE = "one";
    private static final String TAG_TWO = "two";
    private static final String TAG_THREE = "three";
    private String currentTag;//存储当前Fragment的Tag值
    private FragmentManager fragmentManager;
    private Fragment currentFragment;//用来存储当前的Fragment
    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {//这里要记得判断,不然......
            fragmentManager.beginTransaction().add(R.id.ft,
                    fragment1, TAG_ONE).commit();
            currentFragment = fragment1;
            currentTag = TAG_ONE;
        } else {
            fragment1 = fragmentManager.findFragmentByTag(TAG_ONE);
            fragment2 = fragmentManager.findFragmentByTag(TAG_TWO);
            fragment3 = fragmentManager.findFragmentByTag(TAG_THREE);
            fragmentManager.beginTransaction()
                    .show(fragment1)
                    .hide(fragment2)
                    .hide(fragment3)
                            // .hide(...)
                    .commit();

        }

    }


    private void initView() {
        //初始化Fragment
        fragment1 = new Fragment1();
//        fragment2 = new Fragment2();
        fragment2 = Fragment2.newInstance("通过Bundle传值");//通过Bundle在Activity里面给Fragment传值
        fragment3 = new Fragment3();
        //初始化控件
        frameLayout = (FrameLayout) findViewById(R.id.ft);
        textView1 = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);
        textView3 = (TextView) findViewById(R.id.tv3);
        textView4 = (TextView) findViewById(R.id.tv4);
        //设置监听
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                switchContent(currentFragment, fragment1, TAG_ONE);
                break;
            case R.id.tv2:
                switchContent(currentFragment, fragment2, TAG_TWO);
                break;
            case R.id.tv3:
                switchContent(currentFragment, fragment3, TAG_THREE);
                break;
            case R.id.tv4:
                /**
                 * 通过Tag值我们可以得到Frgament对象,想怎么传和得到都行
                 */
                Fragment fragment = fragmentManager.findFragmentByTag(currentTag);
                if (currentTag.equals(TAG_ONE)) {
                    Toast.makeText(MainActivity.this, ((Fragment1) fragment).getValue(), Toast.LENGTH_SHORT).show();
                } else if (currentTag.equals(TAG_TWO)) {
                    Toast.makeText(MainActivity.this, ((Fragment2) fragment).getValue(), Toast.LENGTH_SHORT).show();
                } else if (currentTag.equals(TAG_THREE)) {
                    Toast.makeText(MainActivity.this, ((Fragment3) fragment).getValue(), Toast.LENGTH_SHORT).show();
                }
                break;
            default:

                break;
        }

    }

    public void switchContent(Fragment from, Fragment to, String Tag) {
        if (currentFragment != to) {
            currentFragment = to;
            currentTag = Tag;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.ft, to, Tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    public String getActivityValue() {

        return "从Activity得到值";
    }
}
