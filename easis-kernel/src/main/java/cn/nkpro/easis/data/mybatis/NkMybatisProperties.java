package cn.nkpro.easis.data.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mybatis")
public class NkMybatisProperties {
    private String dialect = "cn.nkpro.sasis.config.mybatis.pagination.dialect.MySQLDialect";
}
