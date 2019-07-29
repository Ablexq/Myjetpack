
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









