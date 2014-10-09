# whitelife-spring

Spring Framework 를 사용하여 개발 시 필요한 부가 기능


## Using

target/whitelife-spring-x.x.x.jar 추가 하여 사용


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
