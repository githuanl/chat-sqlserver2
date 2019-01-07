package com.centersoft;

import com.centersoft.entity.UserEntity;
import com.centersoft.service.UserSerivice;
import com.centersoft.utils.SessionUtil;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootApplication
public class Application {
//public class Application extends SpringBootServletInitializer {
    @Value("${im.server.host}")
    private String host;
    @Value("${im.server.port}")
    private Integer port;

    @Autowired
    UserSerivice userSerivice;

    @Bean
    public SocketIOServer socketIOServer() {

        Configuration config = new Configuration();
//        config.setHostname(host);
        config.setPort(port);
        config.setPingInterval(5000);
        config.setPingTimeout(3000);
        config.setWorkerThreads(100);
        config.setContext("/im");

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        config.setSocketConfig(socketConfig);

        //设置最大每帧处理数据的长度，防止他人利用大数据来攻击服务器
        config.setMaxFramePayloadLength(1024 * 1024);
        //设置http交互最大内容长度
        config.setMaxHttpContentLength(1024 * 1024);

//        // Instantiate Redisson configuration
//        Config redissonConfig = new Config();
//        redissonConfig.useSingleServer().setAddress("127.0.0.1:6379");
//
//// Instantiate Redisson connection
//        RedissonClient redisson = Redisson.create(redissonConfig);
//
//// Instantiate RedissonClientStoreFactory
//        RedissonStoreFactory redisStoreFactory = new RedissonStoreFactory(redisson);
//        config.setStoreFactory(redisStoreFactory);

        config.setAuthorizationListener(hd -> {

            System.out.println(hd.getUrlParams());

            String auth_token = hd.getSingleUrlParam("auth_token");
            System.out.println("auth_token---------------->" + auth_token);
            if (StringUtil.isNullOrEmpty(auth_token)) {
                return false;
            }

            UserEntity userEntity = userSerivice.findUserByToken(auth_token);

            //同一个账号登录多次登录 关闭之前的连接
            if (userEntity != null && SessionUtil.userId_socket_Map.containsKey(userEntity.getId())) {
                SocketIOClient socketIOClient = SessionUtil.userId_socket_Map.get(userEntity.getId());
                socketIOClient.sendEvent("otherLogin");
                socketIOClient.disconnect();
                return false;
            }

            // 移动设备不能同时登录 (android ios) 待处理

            //是否拦截 socket.io 连接
            return userEntity == null ? false : true;
        });

        final SocketIOServer server = new SocketIOServer(config);
        return server;
    }


    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("updata_frozen"));
        container.addMessageListener(listenerAdapter, new PatternTopic("updata_normal"));
        container.addMessageListener(listenerAdapter, new PatternTopic("update_pwd"));
        container.addMessageListener(listenerAdapter, new PatternTopic("update_emp_info"));
        container.addMessageListener(listenerAdapter, new PatternTopic("add_update"));
        return container;
    }


    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        // 注意这里要指向原先用main方法执行的Application启动类
//        return builder.sources(Application.class);
//    }
}
