package cn.edu.jsu.zjj.running;


import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 86182
 * @title PaymentController
 * @projectName MyWalking_Fashion
 * @description：TODO
 * @create 2022/8/8 19:48
 */

@RestController
@CrossOrigin
@RequestMapping("/payMent")
public class PaymentController {
    @Autowired
    private IOrderService service;

    @ApiOperation(value = "支付操作")
    @GetMapping("/payPage")
    public void showPayment(HttpServletRequest request, HttpServletResponse
            response) {
        //可以在这里进行订单状态的设置，对数据库操作
            //获得初始化的AlipayClient
        AlipayClient alipayClient = new
                DefaultAlipayClient(PayMentConig.gatewayUrl,
                PayMentConig.APP_ID,
                PayMentConig.APP_PRIVATE_KEY,
                "json", PayMentConig.CHARSET,
                PayMentConig.ALIPAY_PUBLIC_KEY,
                PayMentConig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new
                AlipayTradePagePayRequest();
        //同步回调，如果本地调度只会走同步，这里的界面就是你付款后跳转的界面
//        String return_url = "http://localhost:8090/payMent/returnUrl?orderId="+byId.getoId();
        alipayRequest.setReturnUrl(PayMentConig.return_url);
        //异步回调，没有服务器实际上我们用不到
        alipayRequest.setNotifyUrl(PayMentConig.notify_url);
        //订单名称，必填


        String subject = "网上蛋糕销售"+orderId+"号订单";



        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = UUID.randomUUID().toString();


        //付款金额，必填 ShopName
        String total_amount = byId.getoAmount()+"";



        //商品描述，可空
        String body = "网上蛋糕销售订单支付";
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如1.5 h，可转换为 90 m。
        String timeout_express = "1h";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no +
                "\"," + "\"total_amount\":\"" + total_amount + "\"," + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\"," + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            System.out.println(form.toString());
            response.setContentType("text/html;charset=" + PayMentConig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //更新订单状态
        

    }

//    @PostMapping("/returnUrl")
//    public String returnUrl(String orderId){
//        service.updateState(orderId);
//        return "http://localhost:6060/buyCar";
//    }
}
