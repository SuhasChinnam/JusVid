package com.jusvid.JusVid.audit;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JusVidInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> eazyMap = new HashMap<String, String>();
        eazyMap.put("App Name", "JusVid");
        eazyMap.put("App Description", "Video Streaming Application");
        eazyMap.put("App Version", "1.0.0");
        eazyMap.put("Contact Email", "info@jusvid.com");
        eazyMap.put("Contact Mobile", "+1(21) 673 4587");
        builder.withDetail("jusvid-info", eazyMap);
    }

}

