import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author weifw
 * @date 2021/5/26
 * 文档参考<a>https://baomidou.com/reference/code-generator-configuration/</a>
 */
public class CodeGenerator {


    public static void main(String[] args) {
        // 创建 generator 对象（生成的对象）
        AutoGenerator autoGenerator = new AutoGenerator();

        // 数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql:///moowu?useUnicode=true&characterEncoding=UTF-8&nullCatalogMeansCurrent=true");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                //tinyint转换成Boolean
                if (fieldType.toLowerCase().contains("smallint")) {
                    return DbColumnType.SHORT;
                }
                //将数据库中datetime转换成date
                if (fieldType.toLowerCase().contains("tinyint(4)")) {
                    return DbColumnType.BYTE;
                }
                //将数据库中datetime转换成date
                if (fieldType.toLowerCase().contains("tinyint(1)")) {
                    return DbColumnType.BOOLEAN;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        autoGenerator.setDataSource(dataSourceConfig);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/mybatisplus-generator/src/main/java");   // 设置生成的目录
        globalConfig.setOpen(false);  // 生成成功后不会自动打开这个文件夹
        globalConfig.setAuthor("weifw");  // 生成的作者
        globalConfig.setServiceName("%sService");  // Service接口去掉前面的I
        globalConfig.setServiceImplName("%sDao");
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setFileOverride(true); // 设置文件存在时是否覆盖
        autoGenerator.setGlobalConfig(globalConfig);


        //自定义模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setServiceImpl("templates/dao.java");
        templateConfig.setService("templates/service.java");
        //不生成controller
        templateConfig.setController("");
        autoGenerator.setTemplate(templateConfig);

        // 包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.moowu.system");  // 设置父包
        packageConfig.setService("service");
        packageConfig.setServiceImpl("dao");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        autoGenerator.setPackageInfo(packageConfig);

        // 配置策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("sys_dept",
                "sys_dict_data",
                "sys_dict_type",
                "sys_menu",
                "sys_role",
                "sys_user");  // 要生成的表名
        strategyConfig.setEntityLombokModel(true);  // 自动添加 Lombok 注解
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);  // 数据库表映射到实体的命名策略，下划线转驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);  // 数据库表字段映射到实体的命名策略，下划线转驼峰命名
        autoGenerator.setStrategy(strategyConfig);


        // 执行
        autoGenerator.execute();
    }

}
