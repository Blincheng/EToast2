[![](https://jitpack.io/v/Blincheng/EToast2.svg)](https://jitpack.io/#Blincheng/EToast2)

v2.0.0来啦~客观往下看！

EToast 一个关闭系统消息通知后依然可以显示的Toast，并且用在作者自己的所有项目中，会跟随Android的发展持续更新迭代

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
		compile 'com.github.Blincheng:EToast2:v2.0.0'
	}
  
  
  需要注意的是，此Toast非彼Toast，应该使用“import com.mic.etoast2.Toast”，建议在BaseActivity中如下使用：
  
  
	public void showShortText(String text) {
		Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
	}
  
  博文地址：[【Android】当关闭通知消息权限后无法显示系统Toast的解决方案](http://blog.csdn.net/qq_25867141/article/details/52807705) ~
