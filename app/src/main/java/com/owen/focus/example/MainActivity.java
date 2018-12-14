package com.owen.focus.example;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.owen.adapter.CommonRecyclerViewAdapter;
import com.owen.adapter.CommonRecyclerViewHolder;
import com.owen.focus.AbsFocusBorder;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.SimpleOnItemListener;
import com.owen.tvrecyclerview.widget.TvRecyclerView;

/**
 * @author ZhouSuQiang
 */
public class MainActivity extends AppCompatActivity {
    /** 颜色焦点框 */
    private FocusBorder mColorFocusBorder;
    /** 图片焦点框 */
    private FocusBorder mDrawableFocusBorder;

    private TvRecyclerView mTvRecyclerView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvRecyclerView = findViewById(R.id.list);
        mTvRecyclerView.setSpacingWithMargins((int) dp2px(10), (int) dp2px(10));
        initListDatas();
        
        initBorder();
    }
    
    private void initBorder() {
        /** 颜色焦点框 */
        mColorFocusBorder = new FocusBorder.Builder().asColor()
                //阴影宽度(方法shadowWidth(18f)也可以设置阴影宽度)
                .shadowWidth(TypedValue.COMPLEX_UNIT_DIP, 20f)
                //阴影颜色
                .shadowColor(Color.parseColor("#3FBB66"))
                //边框宽度(方法borderWidth(2f)也可以设置边框宽度)
                .borderWidth(TypedValue.COMPLEX_UNIT_DIP, 3.5f)
                //边框颜色
                .borderColor(Color.parseColor("#00FF00"))
                //padding值
                .padding(2f)
                //动画时长
                .animDuration(300)
                //不要闪光效果动画
//                .noShimmer()
                //闪光颜色
                .shimmerColor(Color.parseColor("#66FFFFFF"))
                //闪光动画时长
                .shimmerDuration(1000)
                //不要呼吸灯效果
//                .noBreathing()
                //呼吸灯效果时长
                .breathingDuration(3000)
                //边框动画模式
                .animMode(AbsFocusBorder.Mode.TOGETHER)
                .build(this);
        
        //焦点监听 方式一:绑定整个页面的焦点监听事件
        mColorFocusBorder.boundGlobalFocusListener(new FocusBorder.OnFocusCallback() {
            @Override
            public FocusBorder.Options onFocus(View oldFocus, View newFocus) {
                if(null != newFocus) {
                    switch (newFocus.getId()) {
                        case R.id.round_frame_layout_1:
                        case R.id.round_frame_layout_6:
                            return createColorBorderOptions(0);
                        case R.id.round_frame_layout_2:
                        case R.id.round_frame_layout_7:
                            return createColorBorderOptions(20);
                        case R.id.round_frame_layout_3:
                        case R.id.round_frame_layout_8:
                            return createColorBorderOptions(40);
                        case R.id.round_frame_layout_4:
                        case R.id.round_frame_layout_9:
                            return createColorBorderOptions(60);
                        case R.id.round_frame_layout_5:
                        case R.id.round_frame_layout_10:
                            return createColorBorderOptions(90);
        
                        default:
                            break;
                    }
                }
                mColorFocusBorder.setVisible(false);
                //返回null表示不使用焦点框框架
                return null;
            }
        });
        
    
        /** 图片焦点框 */
        mDrawableFocusBorder = new FocusBorder.Builder().asDrawable()
                .borderDrawableRes(R.mipmap.focus)
                //标题相关配置
                .titleBackgroundRes(R.drawable.shape_item_title_bg)
                .titleMarginBottomAutoAlignBorder()
                .titleMargin(10, 0, 10, 4)
//                .titlePadding(10)
                .titleWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                .titleTextColor(Color.LTGRAY)
                .titleTextSize(24)
                //边框动画模式
                .animMode(AbsFocusBorder.Mode.SEQUENTIALLY)
                .build(this);
    
        //焦点监听 方式二:绑定单独控件的焦点监听事件
        mTvRecyclerView.setOnItemListener(new SimpleOnItemListener() {
            @Override
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                mDrawableFocusBorder.onFocus(itemView, FocusBorder.OptionsFactory.get(1.2f, 1.2f, "标题文本 " + position));
            }
        });
    }
    
    private FocusBorder.Options createColorBorderOptions(int radius) {
        mDrawableFocusBorder.setVisible(false);
        float scale = 1.2f;
        return FocusBorder.OptionsFactory.get(scale, scale, dp2px(radius) * scale);
    }
    
    private float dp2px(int dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }


    private void initListDatas() {
        mTvRecyclerView.setAdapter(new CommonRecyclerViewAdapter(getApplicationContext()) {
            @Override
            public int getItemCount() {
                return 30;
            }

            @Override
            public int getItemViewType(int position) {
                return position % 2 == 0 ? 0 : 1;
            }

            @Override
            public int getItemLayoutId(int viewType) {
                return viewType == 0 ? R.layout.item_list : R.layout.item_list2;
            }

            @Override
            public void onBindItemHolder(CommonRecyclerViewHolder helper, Object item, int position) {
            }

        });
    }
}
