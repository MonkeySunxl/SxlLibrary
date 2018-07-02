# SxlLibrary
retrofit+okhttp+rxjava 网络请求 + mvp 基础类

使用：
  配置服务器地址：
   BaseUrl.API_BASE_URL="";
  初始化：
   SXLSingle.init(this);
   
   动态设置服务器地址：
   
   BaseUrl.API_BASE_URL="";
   SXLSingle.getBuilder().baseUrl(BaseUrl.API_BASE_URL);
   
  // 添加公共参数拦截器
    SXLSingle.getHttpClientNoCacheBuilder().addInterceptor(new HttpCommonInterceptor());
    
   
    方法一：
        SXLSingle.createService(ApiService.class,false).getVersion1()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseInfoBean<VersionBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseInfoBean<VersionBean> infoBean) {
                        Log.i("检测版本更新2", "onNext: "+infoBean.getInfo().toString());
                        if (infoBean.getStatus() == 1) {
                        }
                    }
                });
                
          方式二：
          封装统一操作：
          public class HomeLoader extends ObjectLoader {
            ApiService apiService;
            public HomeLoader() {
              apiService = SXLSingle.createService(ApiService.class, false);
            }
            public Observable<BaseInfoBean<VersionBean>> getVersonInfo() {
              return baseSubscribe(apiService.getVersion());
            }
          }
        HomeLoader homeLoader = new HomeLoader();
        homeLoader.getVersonInfo().subscribe(new SxlSubscriber<BaseInfoBean<VersionBean>>() {
            @Override
            public void onSuccess(BaseInfoBean<VersionBean> infoBean) {
                Log.i("检测版本更新1", "onNext: "+infoBean.getInfo().toString());
            }
            @Override
            protected void onFinish() {

            }
        });
              
重点：
Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency
	dependencies {
	        implementation 'com.github.MonkeySunxl:SxlLibrary:1'
	}
