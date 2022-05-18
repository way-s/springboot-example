package com.example.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: mxx
 * @Description:
 */
@Getter
@Setter
@Component // 只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
@ConfigurationProperties(prefix = "secure.roster")
public class UrlsConfigure {

    private List<String> adminUrls;

    private List<String> userUrls;
    
    private List<String> ignoredUrls;

}
