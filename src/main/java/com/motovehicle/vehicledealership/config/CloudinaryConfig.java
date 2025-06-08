package com.motovehicle.vehicledealership.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = ObjectUtils.asMap(
                "cloud_name", "diar4w543",        // 🔁 Replace this
                "api_key", "331673352869273",              // 🔁 Replace this
                "api_secret", "4c_Ldbbk8cug9_RpnMkGXbHxpaw"         // 🔁 Replace this
        );
        return new Cloudinary(config);
    }
}
