package cn.edu.jsu.zjj.running;

/**
 * @author 86182
 * @title PayMentConig
 * @projectName MyWalking_Fashion
 * @description：TODO
 * @create 2022/8/8 19:51
 */
public class PayMentConig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000120617625"; //测试
    // 应用私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfdK6ARW3+qITDPA7HQCr/AxjRU79KaDrkDWD4tOjMAPXVSPJBunPRpX6ZUhqmDWtDh1sZFUxvUuJZzTqTCVu7qbo7FptLy6KCuaaRAoFRzu4LdC4q7nFi+TBtjS2a6uvRDE9ShymicolynhULS1P4d6GV8bJOZe3RVXvxb4s1Q0mzpTlb4dyOQoOIyzTITWV7Y9GwOPZ3jvt/wsjhgNVBxd3gnCyGAKlmyzDz4DxV2P/1r5aX/qzihAZ6Sh5McYHf0QGt4ZmfVeJfB7DaP+dzrbMDN5/iQSc6uWGCzplX1PjjYyxqb1dwbplGIqNcSdZ1uiIZVOG6upJtZQqwjKKpAgMBAAECggEAF1hi7/66Qu1zOo5Bb6SSZvGSIZuMRx2JebgHuYHrD2sYkthyN3NP36LGLTZbFnKigKmBieOXZDkTUKh+aeaV2pzX2izOIcO3kEIUA+kE+/A648xnsftk+GOZVoCz65l3WwzdVCzbyJ/SRcilNhkaOydwrd5MB7RepCZK1idJj0uGhGZQCVWDASMpyzAavMxDWrCa9G0Jl5Rj4qIIZGW9DkzNB4nGGyR5JUBsMKLNvPkgqALnCC/i3TxUh9LeCtMkBt3sUbXhnUD/TcviWyh8wrA9JSamayusT9TQGIq1/a9oNR0/j5bbLrT0cAs98s27Q5gYw6hO+Q4EiWD6Z7mlLQKBgQDNjRupfASDf5VJRCplhJ+d3z8hZaeWeuSuJ9u2UC0fkjfs4KmInqty98l+eUm+FumkOZ6zbd5h+zennUfWPM6pBXYtUyhDWwj1YmVVVbXrqDWxwoC18IsCCudfMYD1gI01U+UVesIqr0oCQnIvDeHrdg/rqhBJZa83tNAUSCVRwwKBgQDGl1+97z6kkpuKTObf8TxiHkrMR0J65CVye2qpD67CJQGQ8NM/KPpwC1buBz96VGqbEhG2mwpcG/XntC1SILvFS7+fq4i3YWSF7OM9lLz/vSQt01s5Z1f6B+ckWj8Vus0yPogTqKsZjl28XgLzaUgjvVPPuJWNwctpdvzBdG9nIwKBgAiP+jkDK4T2yZc1ubZk4elHpW+Utnh0QhZnHqXnjRmlk6LQcP957JZrb/9Ouh2rAqyOoefDieH2/wiCar2n5svvqE6XCjRUFHl0UdhOK7fXV/VBru7lSEUbgyvZ+g8//aHdDsvoQzJX/vM9JViEcYbHcj5U5TzJeZUOeSR/JN7VAoGANLqSkELK1jWbGOCMWJ+b0c2LPKwbCbgz/S6pxlPEqgXbYZmf7WA7Bq7sGO1RvHBXke79Rwfpe0VtSGU9t1W7pYoUShfoiBBS+YBJYdtxR1H4HjbFPPukj12z0KIpp+Jeg125EccyDuoPF2m6zNiCcALgEwJGq0nucYVHXTJGNhMCgYEAlbv0bW8ygnqFJCYPouyGsgjWK9ONvAFiKt16Z8uKEJK7I1hqOEB5qt9gETIvqbM/t2gkQr+sdIkABVoHyKdfmNGOmY97ePkokQdHIXTIhkF/3RoR5bgGnYkzoP3NVVct1li1V0bXu4nI+toRYV456l8OnBuTqVpuLu/Z/vduK+E=";
    // 支付宝公钥, 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0hANAqBRx8VDtP1YQQe8+zUwJW7LB5ScTdC1/p0Z8wMu6aHCVC9S5al97wDNnOSJyTQJUkyM9RNPQXJDC3EXbmLuucLwC1pUoZ5qUR6mU8VNaiJjmCTv0r9pdh6oMezId1qxaCurqdrx1r0gHDRuQBoLgc9TAHTxW4MQuOyCiSf5pUErQf95KO6AlE4lVEngwAl2gotcT5knQagZgQbO0wV3SxsfKoPpn4izuHctdLrDO8RZry4xAvd7f/sz8HERB8Vzt4uLdZ+y4kJhwfQgVngLWoqer+mPBwZyDO7BVLLqd8JhJpfXSWrHI0sGFak2ObyBuD3X/HDo8+iS3M3dYwIDAQAB";
// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问，
    public static String notify_url = "http://localhost:5056/stateMent";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:5056/buyCar";
    // 签名方式
    public static String sign_type = "RSA2";
    // 字符编码格式
    public static String CHARSET = "UTF-8";
    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl =
            "https://openapi.alipaydev.com/gateway.do"; //测试环境，每个人都是一样的

}
