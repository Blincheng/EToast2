[![](https://jitpack.io/v/Blincheng/EToast2.svg)](https://jitpack.io/#Blincheng/EToast2)

v2.0来啦~客观往下看！

EToast 一个关闭系统消息通知后依然可以显示的Toast，此版本完全是独立于v1.x.x的版本，实现方式上也是完全的不同，尽量的参考系统Toast的源码去实现。
和上代EToast相比，有以下的改动：
1. Context不再依赖于Activity显示。
2. 显示动画完全跟随着系统走，也就是说和系统的Toast动画效果完全一致
3. 多条显示规则还是保留了V1.x的版本的规则，永远只显示一个Toast。
4. 由于实现原理的更改，EToast不再会被Dialog、PopupWindow等弹窗布局覆盖


由于在Android5.0以下无法获取是否打开系统通知权限，所以为了防止用户看不到Toast，最终的逻辑优化了一下：
1. 当用户是5.0以下的机器时，永远只显示EToast
2. 当用户是5.0以上的机器是，如果打开了通知权限，则显示系统Toast；反之则显示Etoast


使用方法和Toast完全一致，如下：

Toast.makeText(MainActivity.this,"我是一个屏蔽通知我也是可以显示的Toast",Toast.LENGTH_SHORT).show();

具体使用如下：
Step 1. Add the JitPack repository to your build file


	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency


  	dependencies {
		compile 'com.github.Blincheng:EToast2:v2.0.2'
	}
  
  Step 3.添加权限（目前发现小米note、魅族pro6及个别厂家的分支版本需要添加以下权限）
  <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
  对于上面的权限我再说明一下，当我们把WindowManager的LayoutParams设置成WindowManager.LayoutParams.TYPE_TOAST;
  这个属性类型以后其实是不需要系统的悬浮窗权限的，但是个别厂家分支有点区别，所以为了保证程序不出现异常，特别强调添加以下权限。
  
  需要注意的是，此Toast非彼Toast，应该使用“import com.mic.etoast2.Toast”，建议在BaseActivity中如下使用：
  
  
	public void showShortText(String text) {
		Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
	}
  
  博文地址：[【Android】当关闭通知消息权限后无法显示系统Toast的解决方案V2.0](http://blog.csdn.net/qq_25867141/article/details/74194503) ~
