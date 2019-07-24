
# ActivityLifecycleCallbacks

# LiveData与 ObservableField的区别 

使用ViewModel+Data Binding解决内存泄漏问题时用到了ObservableInt/ObservableField，
但其实还可以使用 LiveData ，它不仅可以实现与ObservableField相同的功能，
而且有以下好处

•ObservableField只有在数据发生改变时UI才会收到通知，而LiveData不同，只要你postValue或者setValue，
UI都会收到通知，不管数据有无变化 ， LiveData支持多线程

•LiveData能感知Activity的生命周期，在Activity不活动的时候不会触发，例如一个Activity不在任务栈顶部




# LifecycleOwner
``` 
public interface LifecycleOwner {
    @NonNull
    Lifecycle getLifecycle();
}
```
LifecycleRegistryOwner
``` 
@Deprecated
public interface LifecycleRegistryOwner extends LifecycleOwner {...}
```



# 被观察者 ： Lifecycle
``` 
public abstract class Lifecycle {
  
    @MainThread
    public abstract void addObserver(@NonNull LifecycleObserver observer);

    @MainThread
    public abstract void removeObserver(@NonNull LifecycleObserver observer);

    @MainThread
    @NonNull
    public abstract State getCurrentState();

    @SuppressWarnings("WeakerAccess")
    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY
    }

    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;
        public boolean isAtLeast(@NonNull State state) {
            return compareTo(state) >= 0;
        }
    }
}

```

LifecycleRegistry:

``` 
public class LifecycleRegistry extends Lifecycle {
}
```

# 观察者： LifecycleObserver

空接口，用户自定义实现，方法上添加【@OnLifecycleEvent(Lifecycle.Event.XXX)】可根据生命周期做出响应
``` 
public interface LifecycleObserver {

}
```




``` 
android {
   
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    
    // work-runtime-ktx 2.1.0 and above now requires Java 8
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
```



CardView
``` 
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <attr format="reference" name="cardViewStyle"/>
    <color name="cardview_dark_background">#FF424242</color>
    <color name="cardview_light_background">#FFFFFFFF</color>
    <color name="cardview_shadow_end_color">#03000000</color>
    <color name="cardview_shadow_start_color">#37000000</color>
    <declare-styleable name="CardView">
        <!-- Background color for CardView. -->
        <attr format="color" name="cardBackgroundColor"/>
        <!-- Corner radius for CardView. -->
        <attr format="dimension" name="cardCornerRadius"/>
        <!-- Elevation for CardView. -->
        <attr format="dimension" name="cardElevation"/>
        <!-- Maximum Elevation for CardView. -->
        <attr format="dimension" name="cardMaxElevation"/>
        <!-- Add padding in API v21+ as well to have the same measurements with previous versions. -->
        <attr format="boolean" name="cardUseCompatPadding"/>
        <!-- Add padding to CardView on v20 and before to prevent intersections between the Card content and rounded corners. -->
        <attr format="boolean" name="cardPreventCornerOverlap"/>
        <!-- Inner padding between the edges of the Card and children of the CardView. -->
        <attr format="dimension" name="contentPadding"/>
        <!-- Inner padding between the left edge of the Card and children of the CardView. -->
        <attr format="dimension" name="contentPaddingLeft"/>
        <!-- Inner padding between the right edge of the Card and children of the CardView. -->
        <attr format="dimension" name="contentPaddingRight"/>
        <!-- Inner padding between the top edge of the Card and children of the CardView. -->
        <attr format="dimension" name="contentPaddingTop"/>
        <!-- Inner padding between the bottom edge of the Card and children of the CardView. -->
        <attr format="dimension" name="contentPaddingBottom"/>
        <!-- Workaround to read user defined minimum width -->
        <attr name="android:minWidth"/>
        <!-- Workaround to read user defined minimum height -->
        <attr name="android:minHeight"/>
    </declare-styleable>
    <dimen name="cardview_compat_inset_shadow">1dp</dimen>
    <dimen name="cardview_default_elevation">2dp</dimen>
    <dimen name="cardview_default_radius">2dp</dimen>
    <style name="Base.CardView" parent="android:Widget">
        <item name="cardCornerRadius">@dimen/cardview_default_radius</item>
        <item name="cardElevation">@dimen/cardview_default_elevation</item>
        <item name="cardMaxElevation">@dimen/cardview_default_elevation</item>
        <item name="cardUseCompatPadding">false</item>
        <item name="cardPreventCornerOverlap">true</item>
    </style>
    <style name="CardView" parent="Base.CardView">
    </style>
    <style name="CardView.Dark">
        <item name="cardBackgroundColor">@color/cardview_dark_background</item>
    </style>
    <style name="CardView.Light">
        <item name="cardBackgroundColor">@color/cardview_light_background</item>
    </style>
</resources>
```

SCardView

``` 
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <attr format="reference" name="cardViewStyle"/>
    <color name="sl_cardview_dark_background">#FF424242</color>
    <color name="sl_cardview_light_background">#FFFFFFFF</color>
    <color name="sl_cardview_shadow_end_color">#03000000</color>
    <color name="sl_cardview_shadow_start_color">#37000000</color>
    <declare-styleable name="SCardView">
        <attr format="color" name="cardBackgroundColor"/>
        <attr format="dimension" name="cardCornerRadius"/>
        <attr format="dimension" name="cardElevation"/>
        <attr format="dimension" name="cardMaxElevation"/>
        <attr format="boolean" name="cardUseCompatPadding"/>
        <attr format="boolean" name="cardPreventCornerOverlap"/>
        <attr format="boolean" name="cardUseCornerArea"/>
        <attr format="dimension" name="contentPadding"/>
        <attr format="dimension" name="contentPaddingLeft"/>
        <attr format="dimension" name="contentPaddingRight"/>
        <attr format="dimension" name="contentPaddingTop"/>
        <attr format="dimension" name="contentPaddingBottom"/>
        <attr name="cardLightDirection">
            <enum name="left" value="1"/>
            <enum name="right" value="2"/>
            <enum name="top" value="3"/>
            <enum name="bottom" value="4"/>
            <enum name="LT" value="5"/>
            <enum name="RT" value="6"/>
            <enum name="LB" value="7"/>
            <enum name="RB" value="8"/>
            <enum name="none" value="9"/>
        </attr>
        <attr name="cardCornerVisibility">
            <enum name="noLeftCorner" value="1"/>
            <enum name="noRightCorner" value="2"/>
            <enum name="noTopCorner" value="3"/>
            <enum name="noBottomCorner" value="4"/>
            <enum name="noLT_RBCorner" value="5"/>
            <enum name="noRT_LBCorner" value="6"/>
            <enum name="none" value="7"/>
        </attr>
        <attr format="color" name="cardShadowStartColor"/>
        <attr format="color" name="cardShadowEndColor"/>
        <attr name="android:minWidth"/>
        <attr name="android:minHeight"/>
    </declare-styleable>
    <dimen name="cardview_compat_inset_shadow">1dp</dimen>
    <dimen name="cardview_default_elevation">2dp</dimen>
    <dimen name="cardview_default_radius">2dp</dimen>
    <string name="app_name">library</string>
    <style name="Base.CardView" parent="android:Widget">
        <item name="cardCornerRadius">@dimen/cardview_default_radius</item>
        <item name="cardElevation">@dimen/cardview_default_elevation</item>
        <item name="cardMaxElevation">@dimen/cardview_default_elevation</item>
        <item name="cardUseCompatPadding">false</item>
        <item name="cardPreventCornerOverlap">true</item>
    </style>
    <style name="CardView" parent="Base.CardView"/>
    <style name="CardView.Dark">
        <item name="cardBackgroundColor">@color/sl_cardview_dark_background</item>
    </style>
    <style name="CardView.Light">
        <item name="cardBackgroundColor">@color/sl_cardview_light_background</item>
    </style>
</resources>
```


