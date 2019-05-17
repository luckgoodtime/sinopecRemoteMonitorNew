package com.lng.util;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2018082061110001";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJcsaS4yQrv/CaxldjYoWCt1zscDBB0T3N1vKxiX3RNa/6s10eIyUh5ckmwjWin0MS7PjGZQpDz6L+RfiO9SouuKW9bnd28JJ+RbEYcolyqbWD9Fw2ZiL2HKyjOrKe+YL+Ympj/OAV663WqAK8yTcei9vofz8nkv5TgAYV64uU8nIAv9uXBXn45GiByUAebhGFSqPUxD01AK9rwRjqEhA14y2SlrVHuBTlPwBMbEmFZZuexLqhRXeNJvDFXtpfP11xI/aBGMLOYLO7bMyRD/xuOpvLrG1Tt2dEf66UCiAv1yqVzvUIrU7SaSx6h8LKYROMIEdz2/k+hb/kCZmMdOx5AgMBAAECggEAHoBdZrIzsNggEx9C7g5lzrP6wuX9uIl5BSitmIoL3dh2K8xqpfyGS8qiGVdAjb31hvEQfeGgm0HeGQf5KZQfbfGRTsxiFEU6uRnORbhZ7V2a2T7vk2EFnCQdU524m++FkQTPvRH1z/veV2ka+QCyctKUWnCh3FkaXwQ8Z6dBfCywOMVh74NB0kWhjVIT6cbBGoV6hMtR30QUoDT51qmmGtR+LoxAU9V6CBh0vf8HrT/UskZWy0K9u07nfWp81ZXEqdsdIrH+ozCntie+c3+ddzOrdq5TIpKInpD9JQPvXYGSNSsLU74f0wBswyE4DocbEWc419KPO9kByD6nsaSFAQKBgQD+AX3/VzDySo+tL8Ox15q0Z64z3YBH1CA+UEinS8TWslCEcTA+OaADvv6McsfnOJEfQZuyInnEtujEiFz2uRmtQmDt7iX0IboHCCVtpuyyDT98XKrkNb1oUIkAlixeBjMc2xsAyCrqr65Febmd3EGxGXKso/daI6ilgCNnI3cG2QKBgQDLB6br1Zy4CjA0k0aqYAocj3IMlRsThZ1/Q2rE9RNj3OyK+IYmJfZ7CcHqEt6RgSL3eOsxKg8i7kVTAWMkyeoWQHco7jHm+NOyH3dxnMQYLewDbttOh/Ctem+uypOT17tU1k0OatVRtlFXIrSFeanOA+Qpm0DgQwamAYshF2/OoQKBgDpja2w7xcOcgiSEe06xSh24S6Ieg7N/Pp40ZE8YqrnTqZCYWEelhXP2/pqYc/1h0nJF6Z1sMWOXzDAJTJ1mdVGPlap7+PuCmTmulLP9ECHpy7MxthLZi52kDao12yMN98flUfK/EKZ8FepgjduM2wjz2Zl3448z7TuGFh1KWL0ZAoGAVANbmDUxf5p/rhHPaQsKxBy7ledRZ/efX50XHl9HYch2fV8vq7OD1hAIlQKv5MfqHzuDHzoHP24Xc6ycSQngesq8REyki64lc4y/0VyMbHD8O8NwroNkHXUPp+WdwczAcO+ovTGKCiEGcacjyiO5F03W6TycUPCLXMBAwTXAh2ECgYEA9trymXJT2w/3ZhAbB1LpCnRJMIvTGp4T2EboK8aflESw0SHsxETZ8KRfHoE7KH8GIiizUQWT6/0PTJzJcpWqXUT523nzptqEii0ztKzXK7Pov69L6VD/HGnYrqdfS+e9EU/kXTMsWm6F/MBisa9PpGHRNY1hgoCctU5fpHP6In0=";
	//网站服务地址
	public static String SITE_URL = "http://www.yaomaiqi.com";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = SITE_URL + "/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = SITE_URL + "/jspM/wappay/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyXLGkuMkK7/wmsZXY2KFgrdc7HAwQdE9zdbysYl90TWv+rNdHiMlIeXJJsI1op9DEuz4xmUKQ8+i/kX4jvUqLrilvW53dvCSfkWxGHKJcqm1g/RcNmYi9hysozqynvmC/mJqY/zgFeut1qgCvMk3Hovb6H8/J5L+U4AGFeuLlPJyAL/blwV5+ORogclAHm4RhUqj1MQ9NQCva8EY6hIQNeMtkpa1R7gU5T8ATGxJhWWbnsS6oUV3jSbwxV7aXz9dcSP2gRjCzmCzu2zMkQ/8bjqby6xtU7dnRH+ulAogL9cqlc71CK1O0mkseofCymETjCBHc9v5PoW/5AmZjHTseQIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
