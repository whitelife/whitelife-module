# whitelife-spring

Spring Framework 를 사용하여 개발 시 필요한 부가 기능

## Documentation

### Aspect
- [`CommonLoggingAspect`](#CommonLoggingAspect)

### Log
- [`ModelAndViewLog`](#ModelAndViewLog)

### Model
- [`Common`](#Common)

### Web MVC
- [`HttpSessionCheckingListener`](#HttpSessionCheckingListener)
- [`AjaxView`](#AjaxView)
- [`DownloadView`](#DownloadView)


## Aspect

<a name="CommonLoggingAspect" />
### CommonLoggingAspect

Spring WEB MVC 개발 시 Controller Method 를 Target 으로 하여, ServletRequest, ModelAndView 에 대한 Log 를 출력 한다. 원하는 Class에 자유롭게 expression 를 작성하여 사용 하면 된다.

__Examples__

applicationContext.xml 에 아래 내용을 추가

```xml
<beans:bean id="commonLoggingAspect" class="kr.co.whitelife.spring.aspect.logging.CommonLoggingAspect" />

<aop:config proxy-target-class="true">
	<aop:pointcut id="controllerAround" expression="execution(public * *..*Controller.*(..))"/>

	<aop:aspect id="aloggingAspect" ref="commonLoggingAspect" order="1">
		<aop:around method="loggingAspect" pointcut-ref="controllerAround"/>
	</aop:aspect>
</aop:config>
```


__ModelAndView Logging__

ModelAndView Logger 샘플 이다. Debug 레벨 에서만 동작 한다.

```
-----------------------------------------ModelAndView Information----------------------------------------
view        : ...
viewname    : ...
objects     [
    { sample1 - ... }
    { sample2 - ... }
]
---------------------------------------------------------------------------------------------------------
```


__Servlet Logging__

Servlet Logger 샘플 이다. Debug, Info 레벨 에서만 동작 한다.

```
---------------------------------------Servlet Request Information---------------------------------------
processTime : 0 ms
method      : ...
requestUrl  : ...
remoteAddr  : 0:0:0:0:0:0:0:1
remoteHost  : 0:0:0:0:0:0:0:1
remotePort  : ...
cookies     [
    { JSESSIONID - ... }
]
parameters  [
    { sample1 - 1 }
    { sample2 - 2 }
]
objects     [
    { sample1 - 1 }
    { sample2 - 2 }
]
---------------------------------------------------------------------------------------------------------
```

---------------------------------------


## Log

<a name="ModelAndViewLog" />
### ModelAndViewLog

Spring WEB MVC 개발 시 ModelAndView 에 대한 정보 출력 Logger 샘플은 [CommonLoggingAspect](#CommonLoggingAspect) 를 참고 하자.

__Examples__

```java
ModelAndViewLog modelAndViewLog = new ModelAndViewLog();
modelAndViewLog.resultLog(result, logger);
```

---------------------------------------


## Model

<a name="Common" />
### Common

각 모듈에서 공통으로 사용하는 Model 이다.

__Fields__

- [Ajax] dataType: ajax 요청 시 사용
- [File] uploadFiles: 파일 업로드 시 사용되는 Key Array 이다.
- [Paging] pageSize: 게시 글 수
- [Paging] firstPageNo: 첫 번째 페이지 번호
- [Paging] prevPageNo: 이전 페이지 번호
- [Paging] startPageNo: 시작 페이지 (페이징 네비 기준) 
- [Paging] pageNo: 페이지 번호
- [Paging] endPageNo: 끝 페이지 (페이징 네비 기준)
- [Paging] nextPageNo: 다음 페이지 번호
- [Paging] finalPageNo: 마지막 페이지 번호
- [Paging] totalCount: 게시 글 전체 수

__Examples__

paging 기능 사용 방법 Custom Model 사용 시 Custom extends Common 하여 사용하면 된다.
pageSize 설정 후, totalCount 를 넣어주면 된다. 자동으로 나머지 paging 값을 넣어 준다.

View 에서는 자유롭게 구성 하도록 한다.

```java
Common common = new Common();
common.setPageSize(10); // (Default: 10)
common.setTotalCount(100000);
```

---------------------------------------


## Web MVC

<a name="HttpSessionCheckingListener" />
### HttpSessionCheckingListener

HttpSession 모니터링 기능이다.

__Examples__

web.xml 에 아래 내용을 추가 session-timeout 은 session 유효 시간 이다.

```xml
<session-config>
	<session-timeout>30</session-timeout>
</session-config>
<listener>
	<listener-class>kr.co.whitelife.spring.web.mvc.listener.HttpSessionCheckingListener</listener-class>
</listener>
```


__Session Logging__

```
2014-10-07 14:06:05,957  DEBUG HttpSessionCheckListener - Session IDD31C36AE223F8C14BB1087F6BFCEBD35 created at Tue Oct 07 14:06:05 KST 2014
2014-10-07 14:08:06,068  DEBUG HttpSessionCheckListener - Session IDD31C36AE223F8C14BB1087F6BFCEBD35 destroyed at Tue Oct 07 14:08:06 KST 2014
```
---------------------------------------


<a name="AjaxView" />
### AjaxView

Spring AbstractView 를 확장하여 구현 되어 있다. Ajax 기능을 담당 한다.

__Examples__

applicationContext.xml 에 아래 내용을 추가

```xml
<beans:bean id="ajaxView" class="kr.co.whitelife.spring.web.mvc.view.AjaxView" />
```

Spring 3.0 이상 부터는 @ResponseBody 를 사용하면 쉽게 해결이 가능 하다. 공통으로 사용하고 싶은 경우 사용 한다.
@ModelAttribute 기능 사용 시 ModelAndView 에 자동 바인딩 되어 값이 응답 되기 때문에 라이브러리에서 제공 되는 공통 Model Common 을 사용해야 Model 이 제외된 값을 응답 한다.

Controller 작성

공통 Model Common 에 dataType 값을 사용 한다. 요청시 dataType=json 설정을 한다. 현재는 json 만 지원 한다.

```java
@RequestMapping(value="/ajax", method=RequestMethod.GET)
public ModelAndView ajax(Common common) throws Exception {
	try {
		ModelAndView mav = new ModelAndView();

		// ajax
		mav.setView(this.ajaxView);
		mav.addObject("dataType", common.getDataType());

		// Doing...

		return mav;
	} catch (Exception e) {
		throw e;
	}
}
```

---------------------------------------


<a name="DownloadView" />
### DownloadView

Spring AbstractView 를 확장하여 구현 되어 있다. Download 기능을 담당 한다.

__Examples__

applicationContext.xml 에 아래 내용을 추가

```xml
<beans:bean id="downloadView" class="kr.co.whitelife.spring.web.mvc.view.DownloadView" />
```

Controller 작성 

Common Model 사용 시 fileName, oriFileName Key 로 요청이 들어오는 경우 @ModelAttribute 자동 바인딩 되어 사용이 가능 하다. Custom Model 사용 시 Custom extends Common 하여 사용하면 된다.

Model 을 사용하지 않는 경우, addObject 포맷만 지켜주면 사용이 가능 하다.

```java
@RequestMapping(value="/downloadFile", method=RequestMethod.GET)
public ModelAndView downloadFile(Common common) throws Exception {
	ModelAndView mav = new ModelAndView();
	
	mav.setView(this.downloadView); // DownloadView Config
	
	// Common Model Using
	String fileName = common.getFileName(); // Saving File
	String oriFileName = common.getOriFileName(); // Origin File
	
	File downloadFile = new File(fileName); // Create File
	mav.addObject("downloadFile", downloadFile); // File Object
	mav.addObject("oriFileName", oriFileName); // Origin File
	
	return mav;
}
```

---------------------------------------

