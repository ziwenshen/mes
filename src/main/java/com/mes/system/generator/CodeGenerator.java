package com.mes.system.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

public class CodeGenerator {
    public static void main(String[] args) {
        // 读取 application.yml 配置
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap = null;
        try (InputStream in = CodeGenerator.class.getClassLoader().getResourceAsStream("application.yml")) {
            yamlMap = yaml.load(in);
        } catch (Exception e) {
            System.err.println("无法读取 application.yml，使用默认配置。");
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> spring = yamlMap != null && yamlMap.get("spring") instanceof Map
                ? (Map<String, Object>) yamlMap.get("spring")
                : null;
        @SuppressWarnings("unchecked")
        Map<String, Object> datasource = spring != null && spring.get("datasource") instanceof Map
                ? (Map<String, Object>) spring.get("datasource")
                : null;

        String url = datasource != null ? (String) datasource.get("url") : "jdbc:postgresql://localhost:5432/ziwen_ddd";
        String username = datasource != null ? (String) datasource.get("username") : "postgres";
        String password = datasource != null ? (String) datasource.get("password") : "postgres";

        // 其他参数可自行扩展
        String author = "shenChaoJue";
        String outputDir = "f:/Project/Java/MVC/system/src/main/java";
        String xmlDir = "f:/Project/Java/MVC/system/src/main/resources/mapper";
        String parentPackage = "com.mes.system";

        // 直接在代码中指定表名
        // RBAC
        // String[] tables = { "sys_user", "sys_role", "sys_department",
        // "sys_permission", "sys_user_department",
        // "sys_department_role", "sys_role_permission" };

        // MODEL
        String[] tables = { "model", "property" };
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author)
                            .outputDir(outputDir);
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage)
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlDir));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .entityBuilder().enableLombok()
                            .controllerBuilder().enableRestStyle();
                })
                .execute();
    }
}