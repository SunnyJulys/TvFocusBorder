# 欢迎使用Android TV端焦点框框架 TvFocusBorder [ ![Download](https://api.bintray.com/packages/zhousuqiang/maven/tv-focusborder/images/download.svg) ](https://bintray.com/zhousuqiang/maven/tv-focusborder/_latestVersion)

>* 支持[TvRecyclerView](https://github.com/zhousuqiang/TvRecyclerView)焦点移动;
>* 支持颜色或图片作为焦点框;
>* 支持焦点框圆角变化;

### 效果

![](https://github.com/zhousuqiang/TvFocusBorder/blob/master/image/focus3.gif)

### Gradle 引入
```java
//support版本
implementation 'com.owen:tv-focusborder:1.1.4'

//androidx版本
implementation 'com.owen:tv-focusborder:2.0.0'
```

### 使用
```java
/** 颜色焦点框 */
FocusBorder mColorFocusBorder = new FocusBorder.Builder().asColor()
        //阴影宽度(方法shadowWidth(18f)也可以设置阴影宽度)
        .shadowWidth(TypedValue.COMPLEX_UNIT_DIP, 20f)
        //阴影颜色
        .shadowColor(Color.parseColor("#3FBB66"))
        //边框宽度(方法borderWidth(2f)也可以设置边框宽度)
        .borderWidth(TypedValue.COMPLEX_UNIT_DIP, 3.2f)
        //边框颜色
        .borderColor(Color.parseColor("#00FF00"))
        //padding值
        .padding(2f)
        //动画时长
        .animDuration(300)
        //不要闪光动画
        //.noShimmer()
        //闪光颜色
        .shimmerColor(Color.parseColor("#66FFFFFF"))
        //闪光动画时长
        .shimmerDuration(1000)
        //不要呼吸灯效果
        //.noBreathing()
        //呼吸灯效果时长
        .breathingDuration(3000)
        //边框动画模式
        .animMode(AbsFocusBorder.Mode.SEQUENTIALLY)
        .build(this);

//焦点监听 方式一:绑定整个页面的焦点监听事件
mColorFocusBorder.boundGlobalFocusListener(new FocusBorder.OnFocusCallback() {
    @Override
    public FocusBorder.Options onFocus(View oldFocus, View newFocus) {
        if(null != newFocus) {
            switch (newFocus.getId()) {
                case R.id.round_frame_layout_1:
                case R.id.round_frame_layout_6:
                    float scale = 1.2f;
                    return FocusBorder.OptionsFactory.get(scale, scale, dp2px(radius) * scale);

                default:
                    break;
            }
        }
        //返回null表示不使用焦点框框架
        return null;
    }
});


/** 图片焦点框 */
FocusBorder mDrawableFocusBorder = new FocusBorder.Builder().asDrawable()
        .borderDrawableRes(R.mipmap.focus)
        .titleBackgroundRes(R.drawable.shape_item_title_bg)
        .titleMarginBottomAutoAlignBorder()
        .titleMargin(10, 0, 10, 4)
        .titlePadding(10)
        .titleTextColor(Color.LTGRAY)
        .titleTextSize(24)
        .titleWidth(ViewGroup.LayoutParams.MATCH_PARENT)
        ...
        .build(this);

//焦点监听 方式二:单个的焦点监听事件
view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus) {
            mDrawableFocusBorder.onFocus(v, FocusBorder.OptionsFactory.get(1.2f, 1.2f));
        }
    }
});

```

### 更详细的使用请见exmaple

------

扣 扣 群：484790001（注：加群时请写明来源及目的）

![](https://github.com/zhousuqiang/TvRecyclerView/blob/master/images/qq.png)

TvRecyclerView框架[TvRecyclerView](https://github.com/zhousuqiang/TvRecyclerView)

Tab框架[TvTabLayout](https://github.com/zhousuqiang/TvTabLayout)

Tv控件[TvWidget](https://github.com/zhousuqiang/TvWidget)


作者 [owen](https://github.com/zhousuqiang)
