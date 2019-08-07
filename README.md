
# DataBinding

[leavesC/DataBindingSamples](https://github.com/leavesC/DataBindingSamples)

[DataBinding实用指南](https://www.jianshu.com/p/015ad08c2c75)

``` 
android:onClick="@{presenter.onClick()}" //1.方法引用
android:onClick="@{()->presenter.onClick()}" //2.lamda表达式
android:onClick="@{(view)->presenter.onClick(view)}" //3.lamda表达式
android:onClick="@{()->presenter.onClick(item)}"//4.带参数lamda表达式
android:onClick="@{(view)->presenter.onClick(view, item)}"//5.带参数lamda表达式
```


# ActivityLifecycleCallbacks

# LiveData与 ObservableField的区别 

使用ViewModel+Data Binding解决内存泄漏问题时用到了ObservableInt/ObservableField，
但其实还可以使用 LiveData ，它不仅可以实现与ObservableField相同的功能，
而且有以下好处

•ObservableField只有在数据发生改变时UI才会收到通知，而LiveData不同，只要你postValue或者setValue，
UI都会收到通知，不管数据有无变化 ， LiveData支持多线程

•LiveData能感知Activity的生命周期，在Activity不活动的时候不会触发，例如一个Activity不在任务栈顶部

# BaseObservable

``` 
BaseObservable (androidx.databinding)
    BaseObservableField (androidx.databinding)
        ObservableDouble (androidx.databinding)
        ObservableChar (androidx.databinding)
        ObservableByte (androidx.databinding)
        ObservableField (androidx.databinding)
            ObservableParcelable (androidx.databinding)
        ObservableBoolean (androidx.databinding)
        ObservableInt (androidx.databinding)
        ObservableFloat (androidx.databinding)
        ObservableShort (androidx.databinding)
        ObservableLong (androidx.databinding)
    ViewDataBinding (androidx.databinding)
```

``` 
public class BaseObservableUser extends BaseObservable {

    private String name;

    @Bindable   //get方法上添加@Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name); //编译之后生成BR文件
    }
}
```

``` 
public class ObservableUser {
    public final ObservableField<String> name = new ObservableField<>();
}
```


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



#  jdk版本

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

# kotlin错误

WARNING: API 'variant.getPackageLibrary()' is obsolete and has been replaced with 'variant.getPackageLibraryProvider()'.
It will be removed at the end of 2019.
For more information, see https://d.android.com/r/tools/task-configuration-avoidance.
To determine what is calling variant.getPackageLibrary(), use -Pandroid.debug.obsoleteApi=true on the command line to display more information.
Affected Modules: myscardview

``` 
gradlew  -Pandroid.debug.obsoleteApi=true
```

Configure project :myscardview
WARNING: API 'variant.getPackageLibrary()' is obsolete and has been replaced with 'variant.getPackageLibraryProvider()'.
It will be removed at the end of 2019.
For more information, see https://d.android.com/r/tools/task-configuration-avoidance.
REASON: The Kotlin plugin is currently calling this API. We are working to solve this.
WARNING: Debugging obsolete API calls can take time during configuration. It's recommended to not keep it on at all times.

解决：

换个kotlin版本即可


# SCardView地址：

https://github.com/meetsl/SCardView-master

# cardview阴影


```
CardViewImpl
	CardViewApi21Impl 
	CardViewBaseImpl 
	    CardViewApi17Impl 
```

CardViewApi21Impl:

```
@Override
public void setElevation(CardViewDelegate cardView, float elevation) {
    cardView.getCardView().setElevation(elevation);
}
```

CardViewBaseImpl:

```
@Override
public void setElevation(CardViewDelegate cardView, float elevation) {
    getShadowBackground(cardView).setShadowSize(elevation);
}

```
RoundRectDrawableWithShadow : 
``` 
class RoundRectDrawableWithShadow extends Drawable {

    mCornerShadowPaint.setShader(new RadialGradient(0, 0, mCornerRadius + mShadowSize,
            new int[]{mShadowStartColor, mShadowStartColor, mShadowEndColor},
            new float[]{0f, startRatio, 1f},
            Shader.TileMode.CLAMP));
    
    // we offset the content shadowSize/2 pixels up to make it more realistic.
    // this is why edge shadow shader has some extra space
    // When drawing bottom edge shadow, we use that extra space.
    mEdgeShadowPaint.setShader(new LinearGradient(0, -mCornerRadius + mShadowSize, 0,
            -mCornerRadius - mShadowSize,
            new int[]{mShadowStartColor, mShadowStartColor, mShadowEndColor},
            new float[]{0f, .5f, 1f}, Shader.TileMode.CLAMP));
    mEdgeShadowPaint.setAntiAlias(false);

}

```
当 API 高于或等于21时，使用的是从API21开始才有的Elevation属性设置阴影效果的，
而低于21时是通过Drawable来绘制阴影效果。


# LifecycleOwner

``` 
LifecycleOwner (androidx.lifecycle)
    LifecycleRegistryOwner (androidx.lifecycle)

//实现LifecycleOwner的activity
Context (android.content)
    ContextWrapper (android.content)
        ContextThemeWrapper (android.view)
            Activity (android.app)
                ComponentActivity (androidx.core.app)  implements LifecycleOwner
                    FragmentActivity (androidx.fragment.app)
                        AppCompatActivity (androidx.appcompat.app)

//实现LifecycleOwner的fragment
Fragment (androidx.fragment.app) implements LifecycleOwner
    ListFragment (androidx.fragment.app)
    DialogFragment (androidx.fragment.app)
        AppCompatDialogFragment (androidx.appcompat.app)
            BottomSheetDialogFragment (com.google.android.material.bottomsheet)

```

``` 
public interface LifecycleOwner {
    @NonNull
    Lifecycle getLifecycle();
}
```
LifecycleOwner接口（Lifecycle持有者）：

实现该接口的类持有生命周期(Lifecycle对象)，
该接口的生命周期(Lifecycle对象)的改变会被其注册的观察者LifecycleObserver观察到并触发其对应的事件。


# 被观察者
``` 
Lifecycle (androidx.lifecycle)
    LifecycleRegistry (androidx.lifecycle)
```

Lifecycle 是一个类，它持有关于组件（如 Activity 或 Fragment）生命周期状态的信息，并且允许其他对象观察此状态。

Lifecycle(生命周期)：

和LifecycleOwner不同的是，LifecycleOwner本身持有Lifecycle对象，
LifecycleOwner通过其Lifecycle getLifecycle()的接口获取内部Lifecycle对象。


Event(当前生命周期改变对应的事件)：当Lifecycle发生改变，如进入onCreate,会自动发出ON_CREATE事件。


# 观察者：
``` 
LifecycleObserver (androidx.lifecycle)
    FullLifecycleObserver (androidx.lifecycle)
    GenericLifecycleObserver (androidx.lifecycle)
        ReflectiveGenericLifecycleObserver (androidx.lifecycle)
        SingleGeneratedAdapterObserver (androidx.lifecycle)
        LifecycleBoundObserver in LiveData (androidx.lifecycle)
        FullLifecycleObserverAdapter (androidx.lifecycle)
        CompositeGeneratedAdaptersObserver (androidx.lifecycle)
```
LifecycleObserver接口（ Lifecycle观察者）：

实现该接口的类，通过注解的方式，可以通过被LifecycleOwner类的addObserver(LifecycleObserver o)方法注册,
被注册后，LifecycleObserver便可以观察到LifecycleOwner的生命周期事件。


# activity 与 OnLifecycleEvent 周期

``` 
Activity======onCreate================================
LifecycleObserver======onCreate========
LifecycleObserver======onAny========
LifecycleObserver======onStart========
LifecycleObserver======onAny========
Activity======onStart=================================
LifecycleObserver======onResume========
LifecycleObserver======onAny========
Activity======onResume===============================
LifecycleObserver======onPause========
LifecycleObserver======onAny========
Activity======onPause==================================
LifecycleObserver======onStop========
LifecycleObserver======onAny========
Activity======onStop================================
LifecycleObserver======onDestroy========
LifecycleObserver======onAny========
Activity======onDestroy=============================
```

``` 
public enum State {
    /**
     * Destroyed state for a LifecycleOwner. After this event, this Lifecycle will not dispatch
     * any more events. For instance, for an {@link android.app.Activity}, this state is reached
     * <b>right before</b> Activity's {@link android.app.Activity#onDestroy() onDestroy} call.
     */
    DESTROYED,

    /**
     * Initialized state for a LifecycleOwner. For an {@link android.app.Activity}, this is
     * the state when it is constructed but has not received
     * {@link android.app.Activity#onCreate(android.os.Bundle) onCreate} yet.
     */
    INITIALIZED,

    /**
     * Created state for a LifecycleOwner. For an {@link android.app.Activity}, this state
     * is reached in two cases:
     * <ul>
     *     <li>after {@link android.app.Activity#onCreate(android.os.Bundle) onCreate} call;
     *     <li><b>right before</b> {@link android.app.Activity#onStop() onStop} call.
     * </ul>
     */
    CREATED,

    /**
     * Started state for a LifecycleOwner. For an {@link android.app.Activity}, this state
     * is reached in two cases:
     * <ul>
     *     <li>after {@link android.app.Activity#onStart() onStart} call;
     *     <li><b>right before</b> {@link android.app.Activity#onPause() onPause} call.
     * </ul>
     */
    STARTED,

    /**
     * Resumed state for a LifecycleOwner. For an {@link android.app.Activity}, this state
     * is reached after {@link android.app.Activity#onResume() onResume} is called.
     */
    RESUMED;

    /**
     * Compares if this State is greater or equal to the given {@code state}.
     *
     * @param state State to compare with
     * @return true if this State is greater or equal to the given {@code state}
     */
    public boolean isAtLeast(@NonNull State state) {
        return compareTo(state) >= 0;
    }
}

```


This navigation graph is not referenced from any layout files 
(expected to find it in at least one layout file with a NavHostFragment with app:navGraph="@navigation/nav_graph" attribute). 
Navigation resource files must be referenced from a NavHostFragment in a layout in order to be relevant.  
Issue id: UnusedNavigation


单向数据绑定

实现数据变化自动驱动UI刷新的方式有三种：BaseObservable，ObservableField，ObservableCollection

BaseObservable

一个纯净的ViewModel类被更新后，并不会让UI自动更新。
而数据绑定后，我们自然会希望数据变更后UI会即时刷新，Observable就是为此而生的概念。
BaseObservable提供了notifyChange（）和notifyPropertyChanged（）两个方法，
前者会刷新所有的值域，后者则只更新对应的BR的标志，该BR的生成通过注释@Bindable生成，
可以通过BR notify特定属性关联的视图

``` 
package androidx.databinding;

import androidx.annotation.NonNull;

public class BaseObservable implements Observable {
    private transient PropertyChangeRegistry mCallbacks;

    public BaseObservable() {
    }

    /**
     * 用于监听属性变化
     */
    @Override
    public void addOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                mCallbacks = new PropertyChangeRegistry();
            }
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(@NonNull OnPropertyChangedCallback callback) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.remove(callback);
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     * 通知侦听器此实例的所有属性都已更改。
     */
    public void notifyChange() {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, 0, null);
    }

    /**
     * Notifies listeners that a specific property has changed. 
     * 通知侦听器特定属性已更改。
     * The getter for the property that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     * get方法上添加@Bindable注解，build后生成BR（项目包名+BR）文件中可获取fieldId用于该方法的参数
     * @param fieldId The generated BR id for the Bindable field.
     */
    public void notifyPropertyChanged(int fieldId) {
        synchronized (this) {
            if (mCallbacks == null) {
                return;
            }
        }
        mCallbacks.notifyCallbacks(this, fieldId, null);
    }
}
```

示例：

``` 
public class BaseObservableUser extends BaseObservable {

    private String name;

    @Bindable   //get方法上添加@Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name); //编译之后生成BR文件
    }
}
```





















