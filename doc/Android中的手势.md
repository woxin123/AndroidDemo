# Android 中的手势检测

Android 给我们提供了 `GestureDetector`(手势检测)类，通过这个类可以识别很多手势，在识别之后，具体的逻辑就可以交给我们来控制了。

`GestureDetector` 类对外提供了两个接口（OnGestureListener、OnDoubleTapListener） 和一个外部类（SimpleOnGestureListener）。这个外部类其实是两个接口中的函数的集合，它包含了这两个接口里所有必须实现的函数，而且都已经被重写，所有的函数体都是空的。该类是一个静态类，我们可以在外部集成这个类，重写里面的手势处理的函数。

### GestureDetector.OnGestureListener 接口

如果我们写一个类并继承 OnGestureListener，则会提示有几个必须重写的函数。代码如下：

```kotlin
private class GestureListener : GestureDetector.OnGestureListener {
   override fun onShowPress(e: MotionEvent?) {
       TODO("Not yet implemented")
   }

   override fun onSingleTapUp(e: MotionEvent?): Boolean {
       TODO("Not yet implemented")
   }

   override fun onDown(e: MotionEvent?): Boolean {
       TODO("Not yet implemented")
   }

   override fun onFling(
       e1: MotionEvent?,
       e2: MotionEvent?,
       velocityX: Float,
       velocityY: Float
   ): Boolean {
       TODO("Not yet implemented")
   }

   override fun onScroll(
       e1: MotionEvent?,
       e2: MotionEvent?,
       distanceX: Float,
       distanceY: Float
   ): Boolean {
       TODO("Not yet implemented")
   }

   override fun onLongPress(e: MotionEvent?) {
       TODO("Not yet implemented")
   }

}
```

这里重写了 6 个函数，这些函数在什么情况下才会被触发呢？
- `onDown(MotionEvent e)`: 用户按下屏幕就会触发。
- `onShowPress(MotionEvent e)`: 如果用户按下的时间超过了瞬间，而且在按下的时候 没有松开或者是拖动的，改函数就会被触发。
- `onLongPress(MotionEvent e)`: 长按屏幕，超过一定时长，就会触发函数。

触发顺序: `onDown` -> `onShowPress` -> `onLongPress`

- onSingleTapUp(MotionEvent e): 从名字中也可以看出，一次单独的轻击抬起操作，也就是轻击一下屏幕，立刻抬起来，才会触发这个函数。当然，如果除 `down` 以外还有其他操作，就不再算是单独操作，也就不会触发这个函数了。

单击一下非常快的（不滑动）Touchup，触发顺序为:

```
onDown -> onSingleTapUp -> onSingleTapConfirmed
```

单击一下稍微慢一点（不滑动）TouchUp，触发顺序为：

```
onDown -> onShowPress -> onSingleTapUp -> onSingleTapConfirmed
```

- `onFling(MotionEvent e1. MotionEvent e2, float velocityX, float velocityY)`: 滑屏，用户按下触摸屏，快速移动后松开，由一个 `MotionEvent` `ACTION_DOWN`、多个 `ACTION_MOVE`、一个 `ACTION_UP` 触发。

- `onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)`: 在屏幕上拖动事件。无论是用手拖动 View，还是以抛的动作滚动，都会触发多次这个函数，在 `ACTION_MOVE` 动作发生时就会触发该函数。

滑屏，即手指触动屏幕后，稍微滑动后立即松开，触发顺序为：

```
onDown -> onScroll -> onScroll -> onScroll -> ... -> onFling
```

拖动，触发顺序为：

```
onDown -> onScroll -> onScroll -> onFling
```

可见，无论是滑屏还是拖动，影响的只是中间的 onScroll 被触发的次数，最终都会触发 onFling 事件。

下面我们来看看一个简单的示例:

要使用 `GestureDetector`, 需要如下的步骤。

**步骤1**

创建 `OnGestureListener` 监听事件。

```kotlin
private val gestureListener = object : GestureDetector.OnGestureListener
```

**步骤2**

创建 `GestureDetector` 实例。


```kotlin
val gestureDetector = GestureDetector(context, gestureListener)
```

当然 `GestureDetector` 也有很多重载的，可以根据需要选择。

**步骤3**

对 touch 事件进行拦截，可以给一个自定义 View 设置 

```kotlin
override fun onTouchEvent(event: MotionEvent?): Boolean {
  return gestureDetector.onTouchEvent(event)
}
```

当然也可以将其绑定到某一个控件上。

```kotlin
val v = View(context)
v.setOnTouchListener { view, event ->
    gestureDetector.onTouchEvent(event)
}
```

### GestureDetector.OnDoubleTapListener 接口

我们可以通过 `GestureDetector.OnDoubleTapListener` 接口来设置对双击事件的监听。

有两种方式设置监听。

**方法1**

新建一个类，同时派生自 `OnGestureListener` 和 `OnDoubleTapListener`。

```kotlin
private class GestureListener : GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
```

**方法2**

使用 `GestureDetector.setOnDoubleTapListener()` 函数设置双击监听。

```koltin
gestureDetector.setOnDoubleTapListener(onDoubleTapListener)
```

接下来我们来看看 `GestureDetector.OnDoubleTapListener` 中的这些函数：

```kotlin
private val onDoubleTapListener = object : GestureDetector.OnDoubleTapListener {
  override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTap")
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTapEvent")
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.d(TAG, "onSingleTapConfirmed")
        return true
    }

}
```

- `onSingleTapConfirmed(MotionEvent e)`: 单击事件，用来判断该次单击是 `SingleTap`，而不是 `DoubleTap`。如果连续单击两次，就是 `DoubleTap` 手势；如果只单击一次，系统等待一段时间后如果没有收到第二次单击，则判定该次单击为 `SingleTap`，而不是 `DoubleTap`，然后触发 `OnSingleTapConfirmed` 事件。触发顺序是: onDown -> onSingleTapUp -> onSingleTapConfirmed。
-

