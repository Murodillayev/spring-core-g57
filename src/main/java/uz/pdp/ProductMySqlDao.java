package uz.pdp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductMySqlDao {

    private final DataSource dataSource;

    public ProductMySqlDao(@Qualifier(value = "dataSourceMySql") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(){

        // jdbc
        System.out.println(dataSource);

        // use jdbc
    }
}
