# whitelife-spring
---

Spring Framework 를 사용하여 개발 시 필요한 부가 기능

## Documentation
---

### Aspect
- CommonLoggingAspect(#CommonLoggingAspect)

### Log
- ModelAndViewLog

### Model
- Common

### Web MVC
- HttpSessionCheckingListener
- AjaxView
- DownloadView


## Aspect
---

<a name="CommonLoggingAspect" />
### CommonLoggingAspect

Spring WEB MVC 개발 시 Controller Method 를 Target 으로 하여, HttpServletRequest, ModelAndView 에 대한 Log 를 출력 한다.

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