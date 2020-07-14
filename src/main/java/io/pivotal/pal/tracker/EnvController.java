package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String Port;
    private String MemoryLimit;
    private String CFInstanceIndex;
    private String CFInstanceAddr;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String cfInstancIndex,
                         @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddr){
        this.Port = port;
        this.MemoryLimit = memoryLimit;
        this.CFInstanceIndex = cfInstancIndex;
        this.CFInstanceAddr = cfInstanceAddr;
    }


    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> environment = new HashMap<>();

        environment.put("PORT", Port);
        environment.put("MEMORY_LIMIT", MemoryLimit);
        environment.put("CF_INSTANCE_INDEX", CFInstanceIndex);
        environment.put("CF_INSTANCE_ADDR", CFInstanceAddr);

        return environment;
    }
}
