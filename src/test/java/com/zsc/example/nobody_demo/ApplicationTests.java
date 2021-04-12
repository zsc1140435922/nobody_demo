package com.zsc.example.nobody_demo;

import org.junit.jupiter.api.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
class ApplicationTests {
//    @Resource
//    public UserService userService;

//    @Autowired
//    public RestTemplate restTemplate;
    @Test
    void contextLoads() {
        System.out.println(1);
        String ip = "125.70.11.136";

//        String sina = restTemplate.getForObject("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip={ip}", String.class,ip);
//        SinaIpVo sinaIpVo = new Gson().fromJson(sina, SinaIpVo.class);
//        if(sinaIpVo.getRet()!=-1){
//            System.out.println(sinaIpVo.getProvince());
//            System.out.println(sinaIpVo.getCity());
//        }else{
//            String object = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip={ip}", String.class,ip);
//            IpVo ipVo = new Gson().fromJson(object, IpVo.class);
//            // XX表示内网
//            if(ipVo.getCode()==0 && !ipVo.getAddress().getRegion().equals("XX")){
//                System.out.println(ipVo.getAddress().getRegion());
//                System.out.println(ipVo.getAddress().getCity());
//            }
//        }
    }

    @Test
    void userLoads() {

    }

}
