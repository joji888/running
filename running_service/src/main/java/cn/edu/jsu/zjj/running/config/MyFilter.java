package cn.edu.jsu.zjj.running.config;

import cn.edu.jsu.zjj.running.utils.Token;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    public static Map<String,Token> tokenMap= new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //处理请求和响应对象
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //获取用户请求路径
        String requestURI = request.getRequestURI();
        System.out.println("访问："+requestURI);

        try {
            if ("/login".equals(requestURI)){//登陆接口不用token
                response.setContentType("application/json;charset=UTF-8");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            //页面数据不用token
            if (requestURI.endsWith(".css")||requestURI.endsWith(".woff")||requestURI.endsWith(".png")||requestURI.endsWith(".js")||requestURI.endsWith(".ioc")||requestURI.endsWith(".html")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            response.setContentType("application/json;charset=UTF-8");
            //获取用户请求头的token
            String token = request.getHeader("token");
            if (token==null||"".equals(token)){//不是登陆接口 判断有无token 提示用户携带token
                response.getWriter().print("{\"code\":503,\"massage\":\"请先登陆\"}");
                return;
            }
            System.out.println("token:"+token);
            Token token_o = tokenMap.get(token);//用token 获取到token对象 做进一步操作
            if (token_o==null){
                response.getWriter().print("{\"code\":401,\"massage\":\"token 无效\"}");
                return;
            }
            if (token_o.getEndTime()<= System.currentTimeMillis()){//判断 token 是否过期 提示用户从新登陆
                response.getWriter().print("{\"code\":401,\"massage\":\"token 已经过期请重新登陆\"}");
                tokenMap.remove(token);
                return;
            }

            //登陆没有过期就将过期时间调整（作用就是用户三个小时没有操作页面就会要求用户重新登陆）
            token_o.setEndTime(System.currentTimeMillis()+1000*60*60*3);

            //正常反问直接交给控制层处理
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){//捕获全局异常
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("{\"code\":500,\"massage\":\"全局异常："+e.getMessage()+"\"}");
            System.out.println("全局异常："+e.getMessage());
            e.printStackTrace();//抛出异常到控制台
            return;
        }
        System.out.println("结束访问："+request.getRequestURI());
    }

}
