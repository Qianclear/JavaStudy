## 一、什么是servlet？

servelt就是一个特殊的java类，用来相应客户端的请求。它必须继承HttpServlet类，客户端请求基本分为两种，通过实现doGet()和doPost()方法来完成相应。

## 二、Servlet的配置

在web.xml中配置servlet，根据我的观察，它是这样配置的

```
<servlet>
    <servlet-name>'class名'</servlet-name>
    <servlet-class>'所在包名.class名'</servlet-class>
</servlet>
    <!-- 给servlet提供(映射)一个可供客户端访问的URI -->
<servlet-mapping>
    <servlet-name>'同上上'</servlet-name>
    <url-pattern>/demo1'算是一个代号吧'</url-pattern>
</servlet-mapping>
```

这好像算是一个映射，然后，通配符*代表任意字符串，

``` 
url-pattern:*.do        以*.字符的请求都可以访问
url-pattern:/*          任意字符串都可以访问
url-pattern:  /action/* 以/action开头的请求都可以访问
```

优先级：从高到低

​				绝对匹配  >  /开头匹配  >  扩展名方式匹配



## 三、tomcat...

 http://tomcat.apache.org/ 下载地址在这里(这里好像是需要下载与servlet对应的版本)

下载完之后，cd进入tomcat对应的bin文件夹，然后用```startup.bat``` 进行下载

> cmd中选中加回车即复制

> ps：产生的命令行窗口，可以点褐色区域暂停执行，点右键回复执行

> 如果发现是出现了乱码，可以再conf文件夹下将**logging.properties** 文件中的UTF-8,全部改为GBK，
>
> 或者用**#** 注释掉(打开方式为sublime或者vscode)
>
> 或者是复制文件，添加后缀.bak然后修改，这样防止改炸了

如果在启动时失败，发现tomcat的logs文件夹中的catalina.log文件(启动日志)说xxx初始化失败，百度后发现tomcat默认端口是8080，得到结论：8080端口被别的应用占用



**然后：** 在cmd中使用```netstat -aon | findstr "端口"``` 来查看占用端口的应用所对应的PID(任务管理器中可以查看)，这样可以查看应用



接着，可以在conf文件夹下打开server.xml来更改启动的默认端口

## 另一个问题

eclipse中没有找到 Dynamic Web Project 该如何解决



 Help -> Install New Software 再根据eclipse版本选择对应网站，接着选择 Web, XML, Java EE and OSGi Enterprise Development 这个，下载完之后再重启即可

## 四：eclipse配置tomcat

点击window----preferences----Server目录下找到Runtime Enviroment----单击add----找到对应tomcat版本----next----browse到tomcat对应的文件夹下----finish即可

> 右击project，点击Build Path----Configure Build Path...然后就可以添加libraries（jar包）了

## 五、servlet的生命周期

（1）客户端第一次请求时创建servlet实例。（也可能是在web应用程序启动时创建。通过配置load-on-startup实现）。

（2）web容器调用servlet的init()方法，对servlet进行初始化。

（3）通过doGet()或doPost()方法响应客户端请求。

（4）web容器通过调用destory()方法销毁servlet.

### init() 方法

只调用一次，用于一次性初始化

init 方法的定义如下：

```
public void init() throws ServletException {
  // 初始化代码...
}
```

### service() 方法

该方法的特征：

```
public void service(ServletRequest request,ServletResponse response) 
      throws ServletException, IOException{
}
```

感觉好像没啥

### doGet() 方法

GET 请求来自于一个 URL 的正常请求，或者来自于一个未指定 METHOD 的 HTML 表单，它由 doGet() 方法处理。

```
public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException, IOException {
    // Servlet 代码
}
```

### doPost() 方法

POST 请求来自于一个特别指定了 METHOD 为 POST 的 HTML 表单，它由 doPost() 方法处理。

```
public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
    throws ServletException, IOException {
    // Servlet 代码
}
```

### destroy() 方法

destroy() 方法只会被调用一次，在 Servlet 生命周期结束时被调用。destroy() 方法可以让您的 Servlet 关闭数据库连接、停止后台线程、把 Cookie 列表或点击计数器写入到磁盘，并执行其他类似的清理活动。

在调用 destroy() 方法之后，servlet 对象被标记为垃圾回收。destroy 方法定义如下所示：

```
  public void destroy() {
    // 终止化代码...
  }
```

> ps ,以上对于各种方法的解释均来自菜鸟教程，因为本人对其了解不深

## 六、额，网上看到的

| Accept              | 这个头信息指定浏览器或其他客户端可以处理的 MIME 类型。值 **image/png** 或 **image/jpeg** 是最常见的两种可能值。 |
| ------------------- | ------------------------------------------------------------ |
| Accept-Charset      | 这个头信息指定浏览器可以用来显示信息的字符集。例如 ISO-8859-1。 |
| Accept-Encoding     | 这个头信息指定浏览器知道如何处理的编码类型。值 **gzip** 或 **compress** 是最常见的两种可能值。 |
| Accept-Language     | 这个头信息指定客户端的首选语言，在这种情况下，Servlet 会产生多种语言的结果。例如，en、en-us、ru 等。 |
| Authorization       | 这个头信息用于客户端在访问受密码保护的网页时识别自己的身份。 |
| Connection          | 这个头信息指示客户端是否可以处理持久 HTTP 连接。持久连接允许客户端或其他浏览器通过单个请求来检索多个文件。值 **Keep-Alive** 意味着使用了持续连接。 |
| Content-Length      | 这个头信息只适用于 POST 请求，并给出 POST 数据的大小（以字节为单位）。 |
| Cookie              | 这个头信息把之前发送到浏览器的 cookies 返回到服务器。        |
| Host                | 这个头信息指定原始的 URL 中的主机和端口。                    |
| If-Modified-Since   | 这个头信息表示只有当页面在指定的日期后已更改时，客户端想要的页面。如果没有新的结果可以使用，服务器会发送一个 304 代码，表示 **Not Modified** 头信息。 |
| If-Unmodified-Since | 这个头信息是 If-Modified-Since 的对立面，它指定只有当文档早于指定日期时，操作才会成功。 |
| Referer             | 这个头信息指示所指向的 Web 页的 URL。例如，如果您在网页 1，点击一个链接到网页 2，当浏览器请求网页 2 时，网页 1 的 URL 就会包含在 Referer 头信息中。 |
| User-Agent          | 这个头信息识别发出请求的浏览器或其他客户端，并可以向不同类型的浏览器返回不同的内容。 |

## 七、一些东西

ServletConfig

```
String encoding = config.getInitParameter("encoding");//获得配置文件中的信息
syso(encoding);

or

String value = this.getServletConfig().getInitParameter("encoding");
syso(value);

or

String value = this.getInitParameter("encoding");
syso(value);
```



ServletContext

代表的是整个应用，一个应用只有一个ServletContext对象。单实例。

String getRealPath(String path);//根据资源名称得到资源的绝对路径



```
String path = this.getServletContext().getRealPath("/adlaldjadak");
syso...
```



将请求向下传递（转发）

ServletContext application = this.getServletContext();

application.getRequestDispatcher("/servlet/demo3").forward(request,response);