package com.raindrop.oplog;

import com.raindrop.oplog.anno.OpLog;
import com.raindrop.oplog.enums.OpTypeEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wangliang
 */
@RestController
@SpringBootApplication
public class OperationLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperationLogApplication.class, args);
    }

    @OpLog(value = "新增用户", type = OpTypeEnum.ADD)
    @GetMapping("/insert")
    public String index(String name, Integer age) {
        return "insert";
    }

    @OpLog(value = "更新用户", type = OpTypeEnum.UPDATE)
    @PostMapping("/update")
    public String update(Map<String, String> user) {
        return "update";
    }

}
