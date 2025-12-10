package uz.pdp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductPotgresDao {

    private final DataSource dataSource;

    public ProductPotgresDao(@Qualifier(value = "postgres") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(){

        System.out.println(dataSource);
    }
}
